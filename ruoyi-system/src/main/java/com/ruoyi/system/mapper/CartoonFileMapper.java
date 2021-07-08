package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CloudCartoonFile;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-05
 */
public interface CartoonFileMapper
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param cartoonFileId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public CloudCartoonFile selectCloudCartoonFileById(Long cartoonFileId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cloudCartoonFile 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CloudCartoonFile> selectCloudCartoonFileList(CloudCartoonFile cloudCartoonFile);

    /**
     * 新增【请填写功能名称】
     * 
     * @param cloudCartoonFile 【请填写功能名称】
     * @return 结果
     */
    public int insertCloudCartoonFile(CloudCartoonFile cloudCartoonFile);

    /**
     * 修改【请填写功能名称】
     * 
     * @param cloudCartoonFile 【请填写功能名称】
     * @return 结果
     */
    public int updateCloudCartoonFile(CloudCartoonFile cloudCartoonFile);

    /**
     * 删除【请填写功能名称】
     * 
     * @param cartoonFileId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteCloudCartoonFileById(Long cartoonFileId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param cartoonFileIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCloudCartoonFileByIds(Long[] cartoonFileIds);
}
