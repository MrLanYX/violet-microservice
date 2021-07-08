package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CloudFileShareMapper;
import com.ruoyi.system.domain.CloudFileShare;
import com.ruoyi.system.service.IFileShareService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-19
 */
@Service
public class FileShareServiceImpl implements IFileShareService 
{
    @Autowired
    private CloudFileShareMapper cloudFileShareMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param fileShareId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public CloudFileShare selectFileShareById(String fileShareId)
    {
        return cloudFileShareMapper.selectFileShareById(fileShareId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cloudFileShare 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CloudFileShare> selectFileShareList(CloudFileShare cloudFileShare)
    {
        return cloudFileShareMapper.selectFileShareList(cloudFileShare);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param cloudFileShare 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFileShare(CloudFileShare cloudFileShare)
    {
        return cloudFileShareMapper.insertFileShare(cloudFileShare);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param cloudFileShare 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFileShare(CloudFileShare cloudFileShare)
    {
        return cloudFileShareMapper.updateFileShare(cloudFileShare);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param fileShareIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFileShareByIds(String[] fileShareIds)
    {
        return cloudFileShareMapper.deleteFileShareByIds(fileShareIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param fileShareId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFileShareById(String fileShareId)
    {
        return cloudFileShareMapper.deleteFileShareById(fileShareId);
    }
}
