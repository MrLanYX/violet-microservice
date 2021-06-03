package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ClouddiscFileShare;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-05-18
 */
public interface IClouddiscFileShareService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param shareId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public ClouddiscFileShare selectClouddiscFileShareById(String shareId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param clouddiscFileShare 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ClouddiscFileShare> selectClouddiscFileShareList(ClouddiscFileShare clouddiscFileShare);

    /**
     * 新增【请填写功能名称】
     * 
     * @param clouddiscFileShare 【请填写功能名称】
     * @param fileIds
     * @return 结果
     */
    public int insertClouddiscFileShare(ClouddiscFileShare clouddiscFileShare, String[] fileIds);

    /**
     * 修改【请填写功能名称】
     * 
     * @param clouddiscFileShare 【请填写功能名称】
     * @return 结果
     */
    public int updateClouddiscFileShare(ClouddiscFileShare clouddiscFileShare);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param shareIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteClouddiscFileShareByIds(String[] shareIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param shareId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteClouddiscFileShareById(String shareId);
}
