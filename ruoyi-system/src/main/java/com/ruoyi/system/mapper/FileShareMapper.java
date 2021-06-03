package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FileShare;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-19
 */
public interface FileShareMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param fileShareId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FileShare selectFileShareById(String fileShareId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param fileShare 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FileShare> selectFileShareList(FileShare fileShare);

    /**
     * 新增【请填写功能名称】
     * 
     * @param fileShare 【请填写功能名称】
     * @return 结果
     */
    public int insertFileShare(FileShare fileShare);

    /**
     * 修改【请填写功能名称】
     * 
     * @param fileShare 【请填写功能名称】
     * @return 结果
     */
    public int updateFileShare(FileShare fileShare);

    /**
     * 删除【请填写功能名称】
     * 
     * @param fileShareId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFileShareById(String fileShareId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param fileShareIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFileShareByIds(String[] fileShareIds);


}
