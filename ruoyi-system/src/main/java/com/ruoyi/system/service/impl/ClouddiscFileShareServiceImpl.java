package com.ruoyi.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.lang.Assert;
import com.ruoyi.common.utils.CommonUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.VerifyCodeUtils;
import com.ruoyi.system.constants.ClouddiscFileShareConstants;
import com.ruoyi.system.domain.ClouddiscFile;
import com.ruoyi.system.domain.DTO.ClouddiscFileShareDTO;
import com.ruoyi.system.domain.FileShare;
import com.ruoyi.system.mapper.ClouddiscFileMapper;
import com.ruoyi.system.mapper.FileShareMapper;
import com.ruoyi.system.util.TreeVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ClouddiscFileShareMapper;
import com.ruoyi.system.domain.ClouddiscFileShare;
import com.ruoyi.system.service.IClouddiscFileShareService;
import org.springframework.transaction.annotation.Transactional;

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
     * @param clouddiscFileShare 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public Map<String, Object> selectClouddiscFileShareById(ClouddiscFileShare clouddiscFileShare)
    {
        ClouddiscFileShare getClouddiscFileShare = clouddiscFileShareMapper.selectClouddiscFileShareById(clouddiscFileShare.getShareId());
        Assert.notNull(getClouddiscFileShare,"分享已被取消");
        if(CommonUtil.isEmpty(getClouddiscFileShare)){
            throw new RuntimeException("分享已被取消！");
        }
        //判断分享永久或有时限
        if(ClouddiscFileShareConstants.TIME_LIMITED.equals(getClouddiscFileShare.getShareType())){
            //判断分享是否过期
            if(Integer.parseInt(getClouddiscFileShare.getEffectiveTime())<=DateUtils.getdayPoor(DateUtils.getNowDate(), getClouddiscFileShare.getShareStartTime())){
                throw new RuntimeException("分享已过期！");
            }
        }
        //判断验证码
        if(!clouddiscFileShare.getCheckCode().toLowerCase().equals(getClouddiscFileShare.getCheckCode().toLowerCase())){
                throw new RuntimeException("验证码错误！");
        }
        FileShare fileShare = new FileShare();
        fileShare.setShareId(clouddiscFileShare.getShareId());
        String[] fileIds = fileShareMapper.selectFileShareList(fileShare).stream().map(FileShare::getFileId).toArray(String[]::new);
        List<ClouddiscFile> clouddiscFileList = clouddiscFileMapper.getFilesByParentId(fileIds[0]);
        Map<String, Object> map = new HashMap<>(8);
        map.put("sourceName",clouddiscFileMapper.selectClouddiscFileById(fileIds[0]).getSourceName());
        map.put("clouddiscFileList", TreeVOUtil.getBuildTree(clouddiscFileList, clouddiscFileMapper.selectClouddiscFileById(fileIds[0]).getParentId()));
        map.put("clouddiscFileShare", clouddiscFileShareMapper.selectClouddiscFileShareById(clouddiscFileShare.getShareId()));
        return map;
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
     * @param clouddiscFileShareDTO 【请填写功能名称】
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ClouddiscFileShare insertClouddiscFileShare(ClouddiscFileShareDTO clouddiscFileShareDTO)
    {
        String id = CommonUtil.getUid();
        ClouddiscFileShare clouddiscFileShare = new ClouddiscFileShare();
        //生成地址
        String shareUrl = "192.168.0.119:7957"+"/ParentView/cloud?id="+id;


        //验证码
        clouddiscFileShare.setCheckCode(VerifyCodeUtils.generateVerifyCode(4));
        clouddiscFileShare.setShareId(id);
        clouddiscFileShare.setShareType(clouddiscFileShareDTO.getShareType());
        clouddiscFileShare.setEffectiveTime(clouddiscFileShareDTO.getEffectiveTime());
        clouddiscFileShare.setCreateTime(DateUtils.getNowDate());
        clouddiscFileShare.setCreateBy(SecurityUtils.getUsername());
        clouddiscFileShare.setShareStartTime(DateUtils.getNowDate());
        clouddiscFileShare.setUserId(SecurityUtils.getUserId());
        clouddiscFileShare.setShareUrl(shareUrl);
        clouddiscFileShare.setFileName(clouddiscFileMapper.selectClouddiscFileById(clouddiscFileShareDTO.getFileIds()[0]).getSourceName());
        clouddiscFileShareMapper.insertClouddiscFileShare(clouddiscFileShare);
        for (String fileId:
                clouddiscFileShareDTO.getFileIds()) {
            FileShare fileShare = new FileShare();
            fileShare.setFileShareId(CommonUtil.getUid());
            //文件id
            fileShare.setFileId(fileId);
            //分享id
            fileShare.setShareId(clouddiscFileShare.getShareId());
            fileShareMapper.insertFileShare(fileShare);
        }
        return clouddiscFileShare;
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
