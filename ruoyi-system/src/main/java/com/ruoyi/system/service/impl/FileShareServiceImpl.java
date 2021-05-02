package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FileShareMapper;
import com.ruoyi.system.domain.FileShare;
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
    private FileShareMapper fileShareMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param fileShareId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FileShare selectFileShareById(String fileShareId)
    {
        return fileShareMapper.selectFileShareById(fileShareId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param fileShare 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FileShare> selectFileShareList(FileShare fileShare)
    {
        return fileShareMapper.selectFileShareList(fileShare);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param fileShare 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFileShare(FileShare fileShare)
    {
        return fileShareMapper.insertFileShare(fileShare);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param fileShare 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFileShare(FileShare fileShare)
    {
        return fileShareMapper.updateFileShare(fileShare);
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
        return fileShareMapper.deleteFileShareByIds(fileShareIds);
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
        return fileShareMapper.deleteFileShareById(fileShareId);
    }
}
