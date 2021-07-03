package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CloudRecycleBin;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-07-01
 */
public interface CloudRecycleBinMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public CloudRecycleBin selectCloudRecycleBinById(String id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cloudRecycleBin 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CloudRecycleBin> selectCloudRecycleBinList(CloudRecycleBin cloudRecycleBin);

    /**
     * 新增【请填写功能名称】
     * 
     * @param cloudRecycleBin 【请填写功能名称】
     * @return 结果
     */
    public int insertCloudRecycleBin(CloudRecycleBin cloudRecycleBin);

    /**
     * @Description: 批量插入
     * @param cloudRecycleBinList
     * @author Administrator
     * @date 2021/7/1 0001 16:43
     * @return boolean
     */
    int insertCloudRecycleBins(List<CloudRecycleBin> cloudRecycleBinList);

    /**
     * @Description: 恢复删除的文件
     * @param ids
     * @author Administrator
     * @date 2021/7/1 0001 17:03
     * @return boolean
     */
    int recoverCloudRecycleBins(String[] ids);

    /**
     * 修改【请填写功能名称】
     * 
     * @param cloudRecycleBin 【请填写功能名称】
     * @return 结果
     */
    public int updateCloudRecycleBin(CloudRecycleBin cloudRecycleBin);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteCloudRecycleBinById(String id);

}
