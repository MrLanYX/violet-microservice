package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.hutool.core.lang.Assert;
import com.ruoyi.common.utils.CommonUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.CloudFileRecycle;
import com.ruoyi.system.domain.ClouddiscFile;
import com.ruoyi.system.mapper.CloudFileRecycleMapper;
import com.ruoyi.system.mapper.ClouddiscFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CloudRecycleBinMapper;
import com.ruoyi.system.domain.CloudRecycleBin;
import com.ruoyi.system.service.ICloudRecycleBinService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-07-01
 */
@Service
public class CloudRecycleBinServiceImpl implements ICloudRecycleBinService 
{
    @Autowired
    private CloudRecycleBinMapper cloudRecycleBinMapper;

    @Autowired
    private ClouddiscFileMapper clouddiscFileMapper;
    @Autowired
    private CloudFileRecycleMapper cloudFileRecycleMapper;
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public CloudRecycleBin selectCloudRecycleBinById(String id)
    {
        return cloudRecycleBinMapper.selectCloudRecycleBinById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cloudRecycleBin 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CloudRecycleBin> selectCloudRecycleBinList(CloudRecycleBin cloudRecycleBin)
    {
        return cloudRecycleBinMapper.selectCloudRecycleBinList(cloudRecycleBin);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param cloudRecycleBin 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCloudRecycleBin(CloudRecycleBin cloudRecycleBin)
    {
        cloudRecycleBin.setCreateTime(DateUtils.getNowDate());
        return cloudRecycleBinMapper.insertCloudRecycleBin(cloudRecycleBin);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertCloudRecycleBins(List<CloudRecycleBin> cloudRecycleBinList) {

        return cloudRecycleBinMapper.insertCloudRecycleBins(cloudRecycleBinList) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean recoverCloudRecycleBins(List<CloudRecycleBin> cloudRecycleBinList) {
        Assert.notNull(cloudRecycleBinList,"传入的数据为空！");
        for (CloudRecycleBin cloudRecycleBin :
             cloudRecycleBinList) {
            CloudFileRecycle cloudFileRecycle = new CloudFileRecycle();
            cloudFileRecycle.setRecycleId(cloudRecycleBin.getId());
            //查找关联文件
            List<CloudFileRecycle> cloudFileRecycleList = cloudFileRecycleMapper.selectCloudFileRecycleList(cloudFileRecycle);
            //恢复文件
            clouddiscFileMapper.recoverClouddiscFiles(cloudFileRecycleList.stream().map(CloudFileRecycle::getFileId).toArray(String[]::new));
            //删除关联
            cloudFileRecycleMapper.deleteCloudFileRecycleByIds(cloudFileRecycleList.stream().map(CloudFileRecycle::getId).toArray(String[]::new));
        }
        //删除回收站
        return cloudRecycleBinMapper.recoverCloudRecycleBins(cloudRecycleBinList.stream().map(CloudRecycleBin::getId).toArray(String[]::new))>0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean recoverCloudRecycleBin(String id) {
        CloudFileRecycle cloudFileRecycle = new CloudFileRecycle();
        cloudFileRecycle.setRecycleId(id);
        //查找关联文件
        List<CloudFileRecycle> cloudFileRecycleList = cloudFileRecycleMapper.selectCloudFileRecycleList(cloudFileRecycle);
        Set<String> parentIds = new HashSet<>();
        for (CloudFileRecycle cloudFileRecycle1:
             cloudFileRecycleList) {
            Set<String> tempParentIds = new HashSet<>();
            parentIds.addAll(getParentIds(tempParentIds, cloudFileRecycle1.getFileId()));
        }
        //恢复文件
        clouddiscFileMapper.recoverClouddiscFiles(parentIds.toArray(new String[0]));
        //删除关联
        cloudFileRecycleMapper.deleteCloudFileRecycleByIds(cloudFileRecycleList.stream().map(CloudFileRecycle::getId).toArray(String[]::new));
        return cloudRecycleBinMapper.deleteCloudRecycleBinById(id)>0;
    }

    private Set<String> getParentIds(Set<String> ids, String id){
        ClouddiscFile clouddiscFile = clouddiscFileMapper.selectDeleteClouddiscFileById(id);
        if(CommonUtil.isNotEmpty(clouddiscFile)){
            ids.add(clouddiscFile.getId());
            if(!"-1".equals(clouddiscFile.getParentId())){
                getParentIds(ids, clouddiscFile.getParentId());
            }
        }
        return ids;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCloudRecycleBins(List<CloudRecycleBin> cloudRecycleBinList) {
        Assert.notNull(cloudRecycleBinList,"传入的数据为空！");
//        for (CloudRecycleBin cloudRecycleBin :
//                cloudRecycleBinList) {
//            CloudFileRecycle cloudFileRecycle = new CloudFileRecycle();
//            cloudFileRecycle.setRecycleId(cloudRecycleBin.getId());
//            //查找关联文件
//            List<CloudFileRecycle> cloudFileRecycleList = cloudFileRecycleMapper.selectCloudFileRecycleList(cloudFileRecycle);
//            //删除关联
//            cloudFileRecycleMapper.deleteCloudFileRecycleByIds(cloudFileRecycleList.stream().map(CloudFileRecycle::getId).toArray(String[]::new));
//        }
        //删除回收站
        return cloudRecycleBinMapper.recoverCloudRecycleBins(cloudRecycleBinList.stream().map(CloudRecycleBin::getId).toArray(String[]::new))>0;
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param cloudRecycleBin 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCloudRecycleBin(CloudRecycleBin cloudRecycleBin)
    {
        cloudRecycleBin.setUpdateTime(DateUtils.getNowDate());
        return cloudRecycleBinMapper.updateCloudRecycleBin(cloudRecycleBin);
    }


    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteCloudRecycleBinById(String id)
    {
        return cloudRecycleBinMapper.deleteCloudRecycleBinById(id);
    }
}
