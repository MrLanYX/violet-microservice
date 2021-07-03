package com.ruoyi.web.controller.clouddisc;

import java.util.ArrayList;
import java.util.List;

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
import com.ruoyi.system.domain.CloudRecycleBin;
import com.ruoyi.system.service.ICloudRecycleBinService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-07-01
 */
@RestController
@RequestMapping("/system/bin")
public class CloudRecycleBinController extends BaseController
{
    @Autowired
    private ICloudRecycleBinService cloudRecycleBinService;

    /**
     * 查询【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:bin:list')")
    @GetMapping("/list")
    public TableDataInfo list(CloudRecycleBin cloudRecycleBin)
    {
        startPage();
        List<CloudRecycleBin> list = cloudRecycleBinService.selectCloudRecycleBinList(cloudRecycleBin);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:bin:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CloudRecycleBin cloudRecycleBin)
    {
        List<CloudRecycleBin> list = cloudRecycleBinService.selectCloudRecycleBinList(cloudRecycleBin);
        ExcelUtil<CloudRecycleBin> util = new ExcelUtil<CloudRecycleBin>(CloudRecycleBin.class);
        return util.exportExcel(list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:bin:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cloudRecycleBinService.selectCloudRecycleBinById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:bin:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CloudRecycleBin cloudRecycleBin)
    {
        return toAjax(cloudRecycleBinService.insertCloudRecycleBin(cloudRecycleBin));
    }

    /**
     * 修改【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:bin:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CloudRecycleBin cloudRecycleBin)
    {
        return toAjax(cloudRecycleBinService.updateCloudRecycleBin(cloudRecycleBin));
    }

    /**
     * 删除【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:bin:remove')")
    @Log(title = "批量完全删除文件", businessType = BusinessType.UPDATE)
	@PostMapping("/deleteCloudRecycleBins")
    @ApiOperation("批量完全删除文件")
    public AjaxResult remove(@RequestBody String[] ids)
    {
        List<CloudRecycleBin> cloudRecycleBinList = new ArrayList<>();
        for (String id:
                ids) {
            CloudRecycleBin cloudRecycleBin = new CloudRecycleBin();
            cloudRecycleBin.setId(id);
            cloudRecycleBinList.add(cloudRecycleBin);
        }
        return toAjax(cloudRecycleBinService.deleteCloudRecycleBins(cloudRecycleBinList));
    }

    /**
     * 删除【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:bin:remove')")
    @Log(title = "批量恢复文件", businessType = BusinessType.UPDATE)
    @PostMapping("/recoverCloudRecycleBins")
    @ApiOperation("批量恢复文件")
    public AjaxResult recoverCloudRecycleBins(String[] ids)
    {
        List<CloudRecycleBin> cloudRecycleBinList = new ArrayList<>();
        for (String id:
             ids) {
            CloudRecycleBin cloudRecycleBin = new CloudRecycleBin();
            cloudRecycleBin.setId(id);
            cloudRecycleBinList.add(cloudRecycleBin);
        }
        return toAjax(cloudRecycleBinService.recoverCloudRecycleBins(cloudRecycleBinList));
    }

    /**
     * 恢复文件【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:bin:remove')")
    @Log(title = "恢复文件", businessType = BusinessType.UPDATE)
    @PostMapping("/recoverCloudRecycleBin")
    @ApiOperation("恢复文件")
    public AjaxResult recoverCloudRecycleBin(String id)
    {
        return toAjax(cloudRecycleBinService.recoverCloudRecycleBin(id));
    }
}
