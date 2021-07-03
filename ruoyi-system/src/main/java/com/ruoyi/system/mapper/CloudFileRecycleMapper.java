package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CloudFileRecycle;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-07-01
 */
public interface CloudFileRecycleMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public CloudFileRecycle selectCloudFileRecycleById(String id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cloudFileRecycle 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CloudFileRecycle> selectCloudFileRecycleList(CloudFileRecycle cloudFileRecycle);

    /**
     * 新增【请填写功能名称】
     * 
     * @param cloudFileRecycle 【请填写功能名称】
     * @return 结果
     */
    public int insertCloudFileRecycle(CloudFileRecycle cloudFileRecycle);

    /**
     * 批量插入
     *
     * @param cloudFileRecycleList 【请填写功能名称】
     * @return 结果
     */
    int insertCloudFileRecycles(List<CloudFileRecycle> cloudFileRecycleList);

    /**
     * 修改【请填写功能名称】
     * 
     * @param cloudFileRecycle 【请填写功能名称】
     * @return 结果
     */
    public int updateCloudFileRecycle(CloudFileRecycle cloudFileRecycle);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteCloudFileRecycleById(String id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCloudFileRecycleByIds(String[] ids);
}
