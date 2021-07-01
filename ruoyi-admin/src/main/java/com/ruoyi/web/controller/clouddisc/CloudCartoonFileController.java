package com.ruoyi.web.controller.clouddisc;

import java.util.List;
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
import com.ruoyi.system.domain.CloudCartoonFile;
import com.ruoyi.system.service.ICloudCartoonFileService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-06-05
 */
@RestController
@RequestMapping("/system/cartoonfile")
public class CloudCartoonFileController extends BaseController
{
    @Autowired
    private ICloudCartoonFileService cloudCartoonFileService;

    /**
     * 查询【请填写功能名称】列表
     */
    //@PreAuthorize("@ss.hasPermi('system:file:list')")
    @GetMapping("/list")
    public TableDataInfo list(CloudCartoonFile cloudCartoonFile)
    {
        startPage();
        List<CloudCartoonFile> list = cloudCartoonFileService.selectCloudCartoonFileList(cloudCartoonFile);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    //@PreAuthorize("@ss.hasPermi('system:file:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CloudCartoonFile cloudCartoonFile)
    {
        List<CloudCartoonFile> list = cloudCartoonFileService.selectCloudCartoonFileList(cloudCartoonFile);
        ExcelUtil<CloudCartoonFile> util = new ExcelUtil<CloudCartoonFile>(CloudCartoonFile.class);
        return util.exportExcel(list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:file:query')")
    @GetMapping(value = "/{cartoonFileId}")
    public AjaxResult getInfo(@PathVariable("cartoonFileId") Long cartoonFileId)
    {
        return AjaxResult.success(cloudCartoonFileService.selectCloudCartoonFileById(cartoonFileId));
    }

    /**
     * 新增【请填写功能名称】
     */
    //@PreAuthorize("@ss.hasPermi('system:file:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CloudCartoonFile cloudCartoonFile)
    {
        return toAjax(cloudCartoonFileService.insertCloudCartoonFile(cloudCartoonFile));
    }

    /**
     * 修改【请填写功能名称】
     */
    //@PreAuthorize("@ss.hasPermi('system:file:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CloudCartoonFile cloudCartoonFile)
    {
        return toAjax(cloudCartoonFileService.updateCloudCartoonFile(cloudCartoonFile));
    }

    /**
     * 删除【请填写功能名称】
     */
    //@PreAuthorize("@ss.hasPermi('system:file:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cartoonFileIds}")
    public AjaxResult remove(@PathVariable Long[] cartoonFileIds)
    {
        return toAjax(cloudCartoonFileService.deleteCloudCartoonFileByIds(cartoonFileIds));
    }
}
