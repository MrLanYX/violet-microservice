package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CloudFileRecycleMapper;
import com.ruoyi.system.domain.CloudFileRecycle;
import com.ruoyi.system.service.ICloudFileRecycleService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-07-01
 */
@Service
public class CloudFileRecycleServiceImpl implements ICloudFileRecycleService 
{
    @Autowired
    private CloudFileRecycleMapper cloudFileRecycleMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public CloudFileRecycle selectCloudFileRecycleById(String id)
    {
        return cloudFileRecycleMapper.selectCloudFileRecycleById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cloudFileRecycle 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CloudFileRecycle> selectCloudFileRecycleList(CloudFileRecycle cloudFileRecycle)
    {
        return cloudFileRecycleMapper.selectCloudFileRecycleList(cloudFileRecycle);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param cloudFileRecycle 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCloudFileRecycle(CloudFileRecycle cloudFileRecycle)
    {
        return cloudFileRecycleMapper.insertCloudFileRecycle(cloudFileRecycle);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param cloudFileRecycle 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCloudFileRecycle(CloudFileRecycle cloudFileRecycle)
    {
        return cloudFileRecycleMapper.updateCloudFileRecycle(cloudFileRecycle);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteCloudFileRecycleByIds(String[] ids)
    {
        return cloudFileRecycleMapper.deleteCloudFileRecycleByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteCloudFileRecycleById(String id)
    {
        return cloudFileRecycleMapper.deleteCloudFileRecycleById(id);
    }
}
