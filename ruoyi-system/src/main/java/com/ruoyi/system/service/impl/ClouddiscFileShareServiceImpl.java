package com.ruoyi.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import com.ruoyi.common.annotation.DataUserScope;
import com.ruoyi.common.utils.CommonUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.VerifyCodeUtils;
import com.ruoyi.system.constants.ClouddiscFileShareConstants;
import com.ruoyi.system.domain.ClouddiscFile;
import com.ruoyi.system.domain.DTO.ClouddiscFileShareDTO;
import com.ruoyi.system.domain.CloudFileShare;
import com.ruoyi.system.mapper.ClouddiscFileMapper;
import com.ruoyi.system.mapper.CloudFileShareMapper;
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
    private CloudFileShareMapper cloudFileShareMapper;

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
        //判断验证码
        Assert.isTrue(clouddiscFileShare.getCheckCode().toLowerCase().equals(getClouddiscFileShare.getCheckCode().toLowerCase()),"验证码错误");
        //判断分享永久或有时限
        if(ClouddiscFileShareConstants.TIME_LIMITED.equals(getClouddiscFileShare.getShareType())){
            //判断分享是否过期
            Assert.isFalse(Integer.parseInt(getClouddiscFileShare.getEffectiveTime())
                    <=DateUtils.getdayPoor(DateUtils.getNowDate(), getClouddiscFileShare.getShareStartTime()),"分享已过期");
        }
                CloudFileShare cloudFileShare = new CloudFileShare();
        cloudFileShare.setShareId(clouddiscFileShare.getShareId());
        String[] fileIds = cloudFileShareMapper.selectFileShareList(cloudFileShare).stream().map(CloudFileShare::getFileId).toArray(String[]::new);
        ClouddiscFile  clouddiscFile = clouddiscFileMapper.selectClouddiscFileById(fileIds[0]);
        Assert.notNull(clouddiscFile, "文件已被删除");
        List<ClouddiscFile> clouddiscFileList = clouddiscFileMapper.getFilesByParentId(fileIds[0]);

        Map<String, Object> map = new HashMap<>(8);
        map.put("sourceName",clouddiscFile.getSourceName());
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
    @DataUserScope(value = "file")
    public List<ClouddiscFileShare> selectClouddiscFileShareList(ClouddiscFileShare clouddiscFileShare)
    {
        return clouddiscFileShareMapper.selectClouddiscFileShareList(clouddiscFileShare)
                .stream()
                .filter(recycleBin-> SecurityUtils.getUserId().equals(recycleBin.getUserId()))
                .collect(Collectors.toList());
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
            CloudFileShare cloudFileShare = new CloudFileShare();
            cloudFileShare.setFileShareId(CommonUtil.getUid());
            //文件id
            cloudFileShare.setFileId(fileId);
            //分享id
            cloudFileShare.setShareId(clouddiscFileShare.getShareId());
            cloudFileShareMapper.insertFileShare(cloudFileShare);
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
        cloudFileShareMapper.deleteFileShareByIds(shareIds);
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
