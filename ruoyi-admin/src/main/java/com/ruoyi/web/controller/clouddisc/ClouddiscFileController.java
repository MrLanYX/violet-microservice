package com.ruoyi.web.controller.clouddisc;

import java.io.IOException;
import java.util.List;

import com.ruoyi.system.domain.vo.CommonTreeVO;
import io.swagger.annotations.ApiOperation;
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
    @PostMapping
    public AjaxResult add(ClouddiscFile clouddiscFile, MultipartFile[] multipartFiles) throws IOException {
        return toAjax(clouddiscFileService.insertClouddiscFile(clouddiscFile, multipartFiles));
    }

    /**
     * 修改【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:file:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
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
}
