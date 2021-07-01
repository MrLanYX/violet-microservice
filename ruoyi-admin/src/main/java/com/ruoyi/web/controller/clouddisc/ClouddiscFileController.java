package com.ruoyi.web.controller.clouddisc;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.system.domain.vo.CommonTreeVO;
import com.ruoyi.web.controller.common.CommonController;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ClouddiscFile;
import com.ruoyi.system.service.IClouddiscFileService;

import io.swagger.annotations.Api;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

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
        ExcelUtil<ClouddiscFile> util = new ExcelUtil<ClouddiscFile>(ClouddiscFile.class);
        return util.exportExcel(null, "【请填写功能名称】数据");
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
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    @ApiOperation("文件删除")
    public AjaxResult remove(@PathVariable String[] fileIds)
    {
        return toAjax(clouddiscFileService.deleteClouddiscFileByIds(fileIds));
    }

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
     * @param parentId
     * @param id
     * @author Administrator
     * @date 2021/6/29 0029 9:59
     * @return com.ruoyi.common.core.domain.AjaxResult
     */
    @PostMapping("/fileCollect")
    public AjaxResult fileCollect(String parentId ,String id){

        return AjaxResult.success(clouddiscFileService.fileCollect(parentId, id));
    }
}