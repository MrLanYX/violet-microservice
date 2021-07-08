package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CloudFileShare;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-19
 */
public interface CloudFileShareMapper
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param fileShareId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public CloudFileShare selectFileShareById(String fileShareId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cloudFileShare 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CloudFileShare> selectFileShareList(CloudFileShare cloudFileShare);

    /**
     * 新增【请填写功能名称】
     * 
     * @param cloudFileShare 【请填写功能名称】
     * @return 结果
     */
    public int insertFileShare(CloudFileShare cloudFileShare);

    /**
     * 修改【请填写功能名称】
     * 
     * @param cloudFileShare 【请填写功能名称】
     * @return 结果
     */
    public int updateFileShare(CloudFileShare cloudFileShare);

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
