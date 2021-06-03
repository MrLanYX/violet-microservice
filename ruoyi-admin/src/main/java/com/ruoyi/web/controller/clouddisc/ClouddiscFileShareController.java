package com.ruoyi.web.controller.clouddisc;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ClouddiscFileShare;
import com.ruoyi.system.service.IClouddiscFileShareService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-05-18
 */
@Api(value = "ClouddiscFileController" ,tags = "文件分享管理")
@RestController
@RequestMapping("/system/share")
public class ClouddiscFileShareController extends BaseController
{
    @Autowired
    private IClouddiscFileShareService clouddiscFileShareService;

    /**
     * 查询【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:share:list')")
    @GetMapping("/list")
    @ApiOperation("文件分享列表")
    public TableDataInfo list(ClouddiscFileShare clouddiscFileShare)
    {
        startPage();
        List<ClouddiscFileShare> list = clouddiscFileShareService.selectClouddiscFileShareList(clouddiscFileShare);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:share:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @ApiOperation("文件分享导出")
    public AjaxResult export(ClouddiscFileShare clouddiscFileShare)
    {
        List<ClouddiscFileShare> list = clouddiscFileShareService.selectClouddiscFileShareList(clouddiscFileShare);
        ExcelUtil<ClouddiscFileShare> util = new ExcelUtil<ClouddiscFileShare>(ClouddiscFileShare.class);
        return util.exportExcel(list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:share:query')")
    @GetMapping(value = "/{shareId}")
    @ApiOperation("文件分享详细")
    public AjaxResult getInfo(@PathVariable("shareId") String shareId)
    {
        return AjaxResult.success(clouddiscFileShareService.selectClouddiscFileShareById(shareId));
    }

    /**
     * 新增【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:share:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("文件分享添加")
    public AjaxResult add(@RequestBody ClouddiscFileShare clouddiscFileShare, String[] fileIds)
    {
        return toAjax(clouddiscFileShareService.insertClouddiscFileShare(clouddiscFileShare, fileIds));
    }

    /**
     * 修改【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:share:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("文件分享编辑")
    public AjaxResult edit(@RequestBody ClouddiscFileShare clouddiscFileShare)
    {
        return toAjax(clouddiscFileShareService.updateClouddiscFileShare(clouddiscFileShare));
    }

    /**
     * 删除【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:share:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{shareIds}")
    @ApiOperation("文件分享删除")
    public AjaxResult remove(@PathVariable String[] shareIds)
    {
        return toAjax(clouddiscFileShareService.deleteClouddiscFileShareByIds(shareIds));
    }
}
