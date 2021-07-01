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
import com.ruoyi.system.domain.CloudCartoon;
import com.ruoyi.system.service.ICloudCartoonService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-06-05
 */
@RestController
@RequestMapping("/system/cartoon")
public class CloudCartoonController extends BaseController
{
    @Autowired
    private ICloudCartoonService cloudCartoonService;

    /**
     * 查询【请填写功能名称】列表
     */
    //@PreAuthorize("@ss.hasPermi('system:cartoon:list')")
    @GetMapping("/list")
    public TableDataInfo list(CloudCartoon cloudCartoon)
    {
        startPage();
        List<CloudCartoon> list = cloudCartoonService.selectCloudCartoonList(cloudCartoon);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:cartoon:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CloudCartoon cloudCartoon)
    {
        List<CloudCartoon> list = cloudCartoonService.selectCloudCartoonList(cloudCartoon);
        ExcelUtil<CloudCartoon> util = new ExcelUtil<>(CloudCartoon.class);
        return util.exportExcel(list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:cartoon:query')")
    @GetMapping(value = "/{cartoonId}")
    public AjaxResult getInfo(@PathVariable("cartoonId") String cartoonId)
    {
        return AjaxResult.success(cloudCartoonService.selectCloudCartoonById(cartoonId));
    }

    /**
     * 新增【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:cartoon:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CloudCartoon cloudCartoon)
    {
        return toAjax(cloudCartoonService.insertCloudCartoon(cloudCartoon));
    }

    /**
     * 修改【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:cartoon:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CloudCartoon cloudCartoon)
    {
        return toAjax(cloudCartoonService.updateCloudCartoon(cloudCartoon));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:cartoon:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cartoonIds}")
    public AjaxResult remove(@PathVariable String[] cartoonIds)
    {
        return toAjax(cloudCartoonService.deleteCloudCartoonByIds(cartoonIds));
    }
}
