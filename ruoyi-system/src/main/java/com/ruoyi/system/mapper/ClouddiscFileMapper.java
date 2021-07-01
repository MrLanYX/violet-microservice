package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ClouddiscFile;
import org.apache.ibatis.annotations.Param;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-18
 */
public interface ClouddiscFileMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public ClouddiscFile selectClouddiscFileById(String id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param clouddiscFile 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ClouddiscFile> selectClouddiscFileList(ClouddiscFile clouddiscFile);

    /**
     * 新增【请填写功能名称】
     * 
     * @param clouddiscFile 【请填写功能名称】
     * @return 结果
     */
    public int insertClouddiscFile(ClouddiscFile clouddiscFile);

    int insertClouddiscFiles(List<ClouddiscFile> clouddiscFile);
    /**
     * 修改【请填写功能名称】
     * 
     * @param clouddiscFile 【请填写功能名称】
     * @return 结果
     */
    public int updateClouddiscFile(ClouddiscFile clouddiscFile);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteClouddiscFileById(String id, @Param("operationFlag")String operationFlag);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteClouddiscFileByIds(String[] ids, @Param("operationFlag")String operationFlag);

    /**
     * @Description: 根据id集查询文件集合
     * @param ids
     * @author Administrator
     * @date 2021/5/21 0021 17:51
     * @return java.util.List<com.ruoyi.system.domain.ClouddiscFile>
     */
    List<ClouddiscFile> selectClouddiscFileByIds(@Param("id")String ids);

    /**
     * @Description: 根据父id获取文件
     * @param
     * @author Administrator
     * @date 2021/6/10 0010 11:29
     * @return java.util.List<com.ruoyi.system.domain.ClouddiscFile>
     */
    List<ClouddiscFile> getFilesByParentId(@Param("parentId")String parentId);
}
