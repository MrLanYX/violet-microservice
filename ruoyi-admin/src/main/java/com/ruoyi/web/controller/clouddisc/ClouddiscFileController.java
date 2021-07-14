package com.ruoyi.web.controller.clouddisc;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.file.ServletUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.DTO.ClouddiscFileDTO;
import com.ruoyi.system.domain.vo.CommonTreeVO;
import com.ruoyi.system.service.ChunkService;
import com.ruoyi.system.service.FileInfoService;
import com.ruoyi.system.util.FileInfoUtils;
import com.ruoyi.web.controller.common.CommonController;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.IClouddiscFileService;

import io.swagger.annotations.Api;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件管理 Controller
 * 
 * @author ruoyi
 * @date 2021-05-18
 */
@Api(value = "ClouddiscFileController" ,tags = "文件管理")
@RestController
@RequestMapping("/system/file")
public class ClouddiscFileController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(ClouddiscFileController.class);
    @Autowired
    private IClouddiscFileService clouddiscFileService;

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private ChunkService chunkService;

    private final Logger logger = LoggerFactory.getLogger(ClouddiscFileController.class);
    /**
     * 查询【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:file:list')")
    @ApiOperation("文件列表")
    @GetMapping("/list")
    public AjaxResult list(ClouddiscFile clouddiscFile)
    {
        List<CommonTreeVO> list = clouddiscFileService.selectClouddiscFileList(clouddiscFile);
        return AjaxResult.success(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @ApiOperation("文件列表导出")
//    @PreAuthorize("@ss.hasPermi('system:file:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ClouddiscFile clouddiscFile)
    {
        List<CommonTreeVO> list = clouddiscFileService.selectClouddiscFileList(clouddiscFile);
        ExcelUtil<CommonTreeVO> util = new ExcelUtil<>(CommonTreeVO.class);
        return util.exportExcel(list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:file:query')")
    @ApiOperation("文件详细")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") String fileId)
    {
        return AjaxResult.success(clouddiscFileService.selectClouddiscFileById(fileId));
    }

    /**
     * 新增【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:file:add')")
    @ApiOperation("添加文件或文件夹")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(ClouddiscFile clouddiscFile, MultipartFile[] multipartFiles) throws IOException {
        return toAjax(clouddiscFileService.insertClouddiscFile(clouddiscFile, multipartFiles));
    }

    /**
     * 修改【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:file:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    @ApiOperation("文件编辑")
    public AjaxResult edit(@RequestBody ClouddiscFile clouddiscFile)
    {
        return toAjax(clouddiscFileService.updateClouddiscFile(clouddiscFile));
    }

    /**
     * 删除【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:file:remove')")
//    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{fileIds}")
//    @ApiOperation("文件删除")
//    public AjaxResult remove(@PathVariable String[] fileIds)
//    {
//        return toAjax(clouddiscFileService.deleteClouddiscFileByIds(fileIds));
//    }

    @GetMapping("/getFilesByParentId/{parentId}")
    @ApiOperation("获取文件夹下的文件")
    public AjaxResult getFilesByParentId(@PathVariable String parentId){
        return AjaxResult.success(clouddiscFileService.getFilesByParentId(parentId));
    }

    /**
     * @Description: 文件下载
     * @param
     * @author Administrator
     * @date 2021/6/9 0009 14:10
     * @return com.ruoyi.common.core.domain.AjaxResult
     */
    @PostMapping("/filedownload/{fileid}")
    @ApiOperation("文件下载")
    public void fileDownload(@PathVariable("fileid") String fileId, HttpServletResponse response){
        ClouddiscFile clouddiscFile = clouddiscFileService.selectClouddiscFileById(fileId);
        String fileName = clouddiscFile.getFilePath().replace(Constants.RESOURCE_PREFIX,"");
        try
        {
            if (!FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", clouddiscFile.getSourceName()));
            }
            String filePath = RuoYiConfig.getProfile() + fileName;
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
            FileUtils.writeBytes(filePath, response.getOutputStream());

        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * @Description: 文件收藏
     * @param clouddiscFileDTO
     * @author Administrator
     * @date 2021/6/29 0029 9:59
     * @return com.ruoyi.common.core.domain.AjaxResult
     */
    @Log(title = "文件收藏", businessType = BusinessType.INSERT)
    @PostMapping("/fileCollect")
    @ApiOperation("文件收藏")
    public AjaxResult fileCollect(@RequestBody ClouddiscFileDTO clouddiscFileDTO){

        return AjaxResult.success(clouddiscFileService.fileCollect(clouddiscFileDTO));
    }

    /**
     * @Description: 文件删除
     * @param id
     * @author Administrator
     * @date 2021/6/29 0029 9:59
     * @return com.ruoyi.common.core.domain.AjaxResult
     */
    @Log(title = "文件删除", businessType = BusinessType.DELETE)
    @PostMapping("/deleteFile")
    @ApiOperation("文件删除")
    public AjaxResult deleteFile(String id){

        return AjaxResult.success(clouddiscFileService.deleteClouddiscFileById(id));
    }

    /**
     * @Description: 文件删除
     * @param id
     * @author Administrator
     * @date 2021/6/29 0029 9:59
     * @return com.ruoyi.common.core.domain.AjaxResult
     */
    @Log(title = "批量文件删除", businessType = BusinessType.DELETE)
    @PostMapping("/deleteFiles")
    @ApiOperation("批量文件删除")
    public AjaxResult deleteFiles(String[] id){

        return AjaxResult.success(clouddiscFileService.deleteClouddiscFileByIds(id));
    }



    /**
     * 上传文件块
     * @param chunk
     * @return
     */
    @PostMapping("/chunk")
    public String uploadChunk(CloudChunkInfo chunk) {
        String apiRlt = "200";

        MultipartFile file = chunk.getUpfile();
        logger.info("file originName: {}, chunkNumber: {}", file.getOriginalFilename(), chunk.getChunkNumber());

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FileInfoUtils.generatePath(chunk));
            //文件写入指定路径
            Files.write(path, bytes);
            if(chunkService.saveChunk(chunk) < 0) {
                apiRlt = "415";
            }
        } catch (IOException e) {
            e.printStackTrace();
            apiRlt = "415";
        }
        return apiRlt;
    }

    @GetMapping("/chunk")
    public UploadResult checkChunk(CloudChunkInfo chunk, HttpServletResponse response) {
        UploadResult ur = new UploadResult();

        //默认返回其他状态码，前端不进去checkChunkUploadedByResponse函数，正常走标准上传
        response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        //先判断整个文件是否已经上传过了，如果是，则告诉前端跳过上传，实现秒传
        if(FileInfoUtils.fileExists(chunk)) {
            ur.setSkipUpload(true);
            ur.setLocation(FileInfoUtils.filePath(chunk));
            response.setStatus(HttpServletResponse.SC_OK);
            ur.setMessage("完整文件已存在，直接跳过上传，实现秒传");
            return ur;
        }
        //如果完整文件不存在，则去数据库判断当前哪些文件块已经上传过了，把结果告诉前端，跳过这些文件块的上传，实现断点续传
        ArrayList<Integer> list = chunkService.checkChunk(chunk);
        if (list !=null && list.size() > 0) {
            ur.setSkipUpload(false);
            ur.setUploadedChunks(list);
            response.setStatus(HttpServletResponse.SC_OK);
            ur.setMessage("部分文件块已存在，继续上传剩余文件块，实现断点续传");
            return ur;
        }
        return ur;
    }

    @PostMapping("/mergeFile")
    public String mergeFile(@RequestBody CloudFileInfoVO fileInfoVO){

        String rlt = "FALURE";

        //前端组件参数转换为model对象
        CloudFileInfo fileInfo = new CloudFileInfo();
        fileInfo.setFilename(fileInfoVO.getName());
        fileInfo.setIdentifier(fileInfoVO.getUniqueIdentifier());
        fileInfo.setId(fileInfoVO.getId());
        fileInfo.setTotalSize(fileInfoVO.getSize());
        fileInfo.setRefProjectId(fileInfoVO.getRefProjectId());

        //进行文件的合并操作
        String file = "/" + fileInfo.getIdentifier() + "/" + fileInfo.getFilename();
        String pathFileName = Constants.RESOURCE_PREFIX + "/" + file;
        fileInfo.setLocation(pathFileName);
        String fileSuccess = FileInfoUtils.merge(fileInfo);

        //文件合并成功后，保存记录至数据库
        if("200".equals(fileSuccess)) {
            if(fileInfoService.addFileInfo(fileInfo) > 0) {
                rlt = "SUCCESS";
            }
        }
        //如果已经存在，则判断是否同一个项目，同一个项目的不用新增记录，否则新增
        if("300".equals(fileSuccess)) {
            List<CloudFileInfo> tfList = fileInfoService.selectFileByParams(fileInfo);
            if(tfList != null) {
                if(tfList.size() == 0 || !fileInfo.getRefProjectId().equals(tfList.get(0).getRefProjectId())) {
                    if(fileInfoService.addFileInfo(fileInfo) > 0) {
                        rlt = "SUCCESS";
                    }
                }
            }
        }
        return rlt;
    }

    /**
     * 查询列表
     *
     * @return ApiResult
     */
    @RequestMapping(value = "/selectFileList", method = RequestMethod.POST)
    public ApiResult selectFileList(@RequestBody CloudQueryInfo query){
        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<CloudFileInfo> list =  fileInfoService.selectFileList(query);
        PageInfo<CloudFileInfo> pageResult = new PageInfo<>(list);
        return ApiResult.success(pageResult);
    }

    /**
     * 下载文件
     * @param req
     * @param resp
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletRequest req, HttpServletResponse resp){
        String location = req.getParameter("location");
        String fileName = req.getParameter("filename");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        OutputStream fos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(location));
            fos = resp.getOutputStream();
            bos = new BufferedOutputStream(fos);
            ServletUtils.setFileDownloadHeader(req, resp, fileName);
            int byteRead = 0;
            byte[] buffer = new byte[8192];
            while ((byteRead = bis.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, byteRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert bos != null;
                bos.flush();
                bis.close();
                fos.close();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除
     */
//    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
//    public ApiResult deleteFile(@RequestBody CloudFileInfo tFileInfo ){
//        int result = fileInfoService.deleteFile(tFileInfo);
//        return ApiResult.success(result);
//    }
}