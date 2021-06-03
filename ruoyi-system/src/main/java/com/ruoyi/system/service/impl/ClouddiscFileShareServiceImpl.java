package com.ruoyi.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.CommonUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.constants.ClouddiscFileShareConstants;
import com.ruoyi.system.domain.ClouddiscFile;
import com.ruoyi.system.domain.FileShare;
import com.ruoyi.system.mapper.ClouddiscFileMapper;
import com.ruoyi.system.mapper.FileShareMapper;
import com.ruoyi.system.service.IFileShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ClouddiscFileShareMapper;
import com.ruoyi.system.domain.ClouddiscFileShare;
import com.ruoyi.system.service.IClouddiscFileShareService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-18
 */
@Service
public class ClouddiscFileShareServiceImpl implements IClouddiscFileShareService 
{
    @Autowired
    private ClouddiscFileShareMapper clouddiscFileShareMapper;

    @Autowired
    private FileShareMapper fileShareMapper;

    @Autowired
    private ClouddiscFileMapper clouddiscFileMapper;
    /**
     * 查询【请填写功能名称】
     * 
     * @param shareId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public ClouddiscFileShare selectClouddiscFileShareById(String shareId)
    {
        ClouddiscFileShare clouddiscFileShare = clouddiscFileShareMapper.selectClouddiscFileShareById(shareId);
        if(ClouddiscFileShareConstants.TIME_LIMITED.equals(clouddiscFileShare.getShareType())){
            DateUtils.getdayPoor(DateUtils.getNowDate(), clouddiscFileShare.getShareStartTime());
            //判断分享是否过期
            if(Integer.parseInt(clouddiscFileShare.getEffectiveTime())<=DateUtils.getdayPoor(DateUtils.getNowDate(), clouddiscFileShare.getShareStartTime())){
                throw new RuntimeException("分享已过期！");
            }
        }
        FileShare fileShare = new FileShare();
        fileShare.setShareId(clouddiscFileShare.getShareId());
        String[] fileIds = fileShareMapper.selectFileShareList(fileShare).stream().map(FileShare::getFileId).toArray(String[]::new);
        List<ClouddiscFile> clouddiscFileList = clouddiscFileMapper.selectClouddiscFileByIds(fileIds);
        Map<String, Object> map = new HashMap<>(8);
        map.put("", clouddiscFileShareMapper.selectClouddiscFileShareById(shareId));
        map.put("clouddiscFileList", clouddiscFileList);
        return clouddiscFileShareMapper.selectClouddiscFileShareById(shareId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param clouddiscFileShare 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ClouddiscFileShare> selectClouddiscFileShareList(ClouddiscFileShare clouddiscFileShare)
    {
        return clouddiscFileShareMapper.selectClouddiscFileShareList(clouddiscFileShare);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param clouddiscFileShare 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertClouddiscFileShare(ClouddiscFileShare clouddiscFileShare, String[] fileIds)
    {
        clouddiscFileShare.setCreateTime(DateUtils.getNowDate());
        clouddiscFileShare.setCreateBy(SecurityUtils.getUsername());
        clouddiscFileShare.setShareStartTime(DateUtils.getNowDate());
        clouddiscFileShare.setUserId(SecurityUtils.getUserId());
        clouddiscFileShareMapper.insertClouddiscFileShare(clouddiscFileShare);
        for (String fileId:
             fileIds) {
            FileShare fileShare = new FileShare();
            fileShare.setFileShareId(CommonUtil.getUid());
            //文件id
            fileShare.setFileId(fileId);
            //分享id
            fileShare.setShareId(clouddiscFileShare.getShareId());
            fileShareMapper.insertFileShare(fileShare);
        }
        return 1;
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param clouddiscFileShare 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateClouddiscFileShare(ClouddiscFileShare clouddiscFileShare)
    {
        clouddiscFileShare.setUpdateTime(DateUtils.getNowDate());
        return clouddiscFileShareMapper.updateClouddiscFileShare(clouddiscFileShare);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param shareIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteClouddiscFileShareByIds(String[] shareIds)
    {
        clouddiscFileShareMapper.deleteClouddiscFileShareByIds(shareIds);
        fileShareMapper.deleteFileShareByIds(shareIds);
        return clouddiscFileShareMapper.deleteClouddiscFileShareByIds(shareIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param shareId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteClouddiscFileShareById(String shareId)
    {
        return clouddiscFileShareMapper.deleteClouddiscFileShareById(shareId);
    }
}
