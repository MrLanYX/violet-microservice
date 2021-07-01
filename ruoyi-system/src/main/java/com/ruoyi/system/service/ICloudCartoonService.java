package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CloudCartoon;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-06-05
 */
public interface ICloudCartoonService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param cartoonId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public CloudCartoon selectCloudCartoonById(String cartoonId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cloudCartoon 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CloudCartoon> selectCloudCartoonList(CloudCartoon cloudCartoon);

    /**
     * 新增【请填写功能名称】
     * 
     * @param cloudCartoon 【请填写功能名称】
     * @return 结果
     */
    public int insertCloudCartoon(CloudCartoon cloudCartoon);

    /**
     * 修改【请填写功能名称】
     * 
     * @param cloudCartoon 【请填写功能名称】
     * @return 结果
     */
    public int updateCloudCartoon(CloudCartoon cloudCartoon);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param cartoonIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteCloudCartoonByIds(String[] cartoonIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param cartoonId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteCloudCartoonById(String cartoonId);
}
