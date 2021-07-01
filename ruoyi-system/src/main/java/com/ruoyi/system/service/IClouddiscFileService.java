package com.ruoyi.system.service;

import java.io.IOException;
import java.util.List;
import com.ruoyi.system.domain.ClouddiscFile;
import com.ruoyi.system.domain.vo.CommonTreeVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-05-18
 */
public interface IClouddiscFileService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param fileId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public ClouddiscFile selectClouddiscFileById(String fileId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param clouddiscFile 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CommonTreeVO> selectClouddiscFileList(ClouddiscFile clouddiscFile);

    /**
     * 新增【请填写功能名称】
     * 
     * @param clouddiscFile 【请填写功能名称】
     * @return 结果
     */
    public int insertClouddiscFile(ClouddiscFile clouddiscFile, MultipartFile[] multipartFiles) throws IOException;

    /**
     * 修改【请填写功能名称】
     * 
     * @param clouddiscFile 【请填写功能名称】
     * @return 结果
     */
    public int updateClouddiscFile(ClouddiscFile clouddiscFile);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param fileIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteClouddiscFileByIds(String[] fileIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param fileId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteClouddiscFileById(String fileId);

    /**
     * @Description: 根据id集查询文件集合
     * @param
     * @author Administrator
     * @date 2021/5/21 0021 17:51
     * @return java.util.List<com.ruoyi.system.domain.ClouddiscFile>
     */
    List<ClouddiscFile> selectClouddiscFileByIds(String ids);

    /**
     * @Description:
     * @param
     * @author Administrator
     * @date 2021/6/10 0010 11:28
     * @return java.util.List<com.ruoyi.system.domain.ClouddiscFile>
     */
    List<CommonTreeVO> getFilesByParentId(String parentId);

    /**
     * @Description: 文件收藏
     * @param parentId
     * @param ids
     * @author Administrator
     * @date 2021/6/21 0021 16:01
     * @return boolean
     */
    boolean fileCollect(String parentId, String ids);
}
