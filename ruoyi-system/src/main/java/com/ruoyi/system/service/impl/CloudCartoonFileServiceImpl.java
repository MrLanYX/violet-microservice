package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CartoonFileMapper;
import com.ruoyi.system.domain.CloudCartoonFile;
import com.ruoyi.system.service.ICloudCartoonFileService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-05
 */
@Service
public class CloudCartoonFileServiceImpl implements ICloudCartoonFileService 
{
    @Autowired
    private CartoonFileMapper cartoonFileMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param cartoonFileId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public CloudCartoonFile selectCloudCartoonFileById(Long cartoonFileId)
    {
        return cartoonFileMapper.selectCloudCartoonFileById(cartoonFileId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cloudCartoonFile 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CloudCartoonFile> selectCloudCartoonFileList(CloudCartoonFile cloudCartoonFile)
    {
        return cartoonFileMapper.selectCloudCartoonFileList(cloudCartoonFile);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param cloudCartoonFile 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCloudCartoonFile(CloudCartoonFile cloudCartoonFile)
    {
        return cartoonFileMapper.insertCloudCartoonFile(cloudCartoonFile);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param cloudCartoonFile 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCloudCartoonFile(CloudCartoonFile cloudCartoonFile)
    {
        return cartoonFileMapper.updateCloudCartoonFile(cloudCartoonFile);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param cartoonFileIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteCloudCartoonFileByIds(Long[] cartoonFileIds)
    {
        return cartoonFileMapper.deleteCloudCartoonFileByIds(cartoonFileIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param cartoonFileId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteCloudCartoonFileById(Long cartoonFileId)
    {
        return cartoonFileMapper.deleteCloudCartoonFileById(cartoonFileId);
    }
}
