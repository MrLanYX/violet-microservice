package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CartoonchapterMapper;
import com.ruoyi.system.domain.CloudCartoon;
import com.ruoyi.system.service.ICloudCartoonService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-05
 */
@Service
public class CloudCartoonServiceImpl implements ICloudCartoonService 
{
    @Autowired
    private CartoonchapterMapper cartoonchapterMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param cartoonId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public CloudCartoon selectCloudCartoonById(String cartoonId)
    {
        return cartoonchapterMapper.selectCloudCartoonById(cartoonId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cloudCartoon 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CloudCartoon> selectCloudCartoonList(CloudCartoon cloudCartoon)
    {
        return cartoonchapterMapper.selectCloudCartoonList(cloudCartoon);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param cloudCartoon 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCloudCartoon(CloudCartoon cloudCartoon)
    {
        cloudCartoon.setCreateTime(DateUtils.getNowDate());
        return cartoonchapterMapper.insertCloudCartoon(cloudCartoon);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param cloudCartoon 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCloudCartoon(CloudCartoon cloudCartoon)
    {
        cloudCartoon.setUpdateTime(DateUtils.getNowDate());
        return cartoonchapterMapper.updateCloudCartoon(cloudCartoon);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param cartoonIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteCloudCartoonByIds(String[] cartoonIds)
    {
        return cartoonchapterMapper.deleteCloudCartoonByIds(cartoonIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param cartoonId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteCloudCartoonById(String cartoonId)
    {
        return cartoonchapterMapper.deleteCloudCartoonById(cartoonId);
    }
}
