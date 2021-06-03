package com.ruoyi.system.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import com.ruoyi.common.utils.CommonUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.file.FileTypeUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.constants.ClouddiscFileConstants;
import com.ruoyi.system.domain.vo.CommonTreeVO;
import com.ruoyi.system.util.TreeVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ClouddiscFileMapper;
import com.ruoyi.system.domain.ClouddiscFile;
import com.ruoyi.system.service.IClouddiscFileService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-18
 */
@Service
public class ClouddiscFileServiceImpl implements IClouddiscFileService 
{
    @Autowired
    private ClouddiscFileMapper clouddiscFileMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param fileId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public ClouddiscFile selectClouddiscFileById(String fileId)
    {
        return clouddiscFileMapper.selectClouddiscFileById(fileId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param clouddiscFile 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CommonTreeVO> selectClouddiscFileList(ClouddiscFile clouddiscFile)
    {

        List<ClouddiscFile> clouddiscFiles = clouddiscFileMapper.selectClouddiscFileList(clouddiscFile);
        List<CommonTreeVO> lists = TreeVOUtil.getBuildTree(clouddiscFiles, "-1");
        return lists;
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param clouddiscFile 【请填写功能名称】
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertClouddiscFile(ClouddiscFile clouddiscFile, MultipartFile[] multipartFiles) throws IOException {

        if(CommonUtil.isNotEmpty(multipartFiles)&&CommonUtil.isNotEmpty(clouddiscFile.getParentId())){

            for (MultipartFile multipartFile:
            multipartFiles) {
                ClouddiscFile childClouddiscFile = new ClouddiscFile();
                childClouddiscFile.setId(CommonUtil.getUid());
                childClouddiscFile.setParentId(clouddiscFile.getId());
                childClouddiscFile.setSourceName(multipartFile.getOriginalFilename());
                childClouddiscFile.setFileType(ClouddiscFileConstants.Document);
                childClouddiscFile.setFileSize(String.valueOf(multipartFile.getSize()));
                childClouddiscFile.setFileSuffix(FileTypeUtils.getFileType(Objects.requireNonNull(multipartFile.getOriginalFilename())));
                String filePath = FileUploadUtils.upload(multipartFile);
                childClouddiscFile.setFilePath(filePath);
                childClouddiscFile.setUserId(SecurityUtils.getUserId());
                childClouddiscFile.setCreateTime(DateUtils.getNowDate());
                childClouddiscFile.setCreateBy(SecurityUtils.getUsername());
                childClouddiscFile.setUploadTime(DateUtils.getNowDate());
                clouddiscFileMapper.insertClouddiscFile(childClouddiscFile);
            }
        }else{
            clouddiscFile.setId(CommonUtil.getUid());
            clouddiscFile.setFileType(ClouddiscFileConstants.DIRECTORY);
            clouddiscFile.setUserId(SecurityUtils.getUserId());
            clouddiscFile.setCreateTime(DateUtils.getNowDate());
            clouddiscFile.setCreateBy(SecurityUtils.getUsername());
            clouddiscFile.setUploadTime(DateUtils.getNowDate());
            clouddiscFileMapper.insertClouddiscFile(clouddiscFile);
        }
        return 1;
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param clouddiscFile 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateClouddiscFile(ClouddiscFile clouddiscFile)
    {
        String operationFlag = CommonUtil.getUUID();
        clouddiscFile.setOperationFlag(operationFlag);
        clouddiscFile.setUpdateTime(DateUtils.getNowDate());
        return clouddiscFileMapper.updateClouddiscFile(clouddiscFile);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param fileIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteClouddiscFileByIds(String[] fileIds)
    {
        String operationFlag = CommonUtil.getUUID();
        //clouddiscFileMapper.selectClouddiscFileById()
        for (String fileId:
        fileIds) {
            ClouddiscFile clouddiscFile = clouddiscFileMapper.selectClouddiscFileById(fileId);
            if(ClouddiscFileConstants.DIRECTORY.equals(clouddiscFileMapper.selectClouddiscFileById(fileId).getFileType())){
                ClouddiscFile chlidClouddiscFile = new ClouddiscFile();
                chlidClouddiscFile.setParentId(clouddiscFile.getId());
                String[] ids = clouddiscFileMapper.selectClouddiscFileList(chlidClouddiscFile).stream().map(ClouddiscFile::getId).toArray(String[]::new);;
                clouddiscFileMapper.deleteClouddiscFileByIds(ids,operationFlag);
            }else{
                clouddiscFileMapper.deleteClouddiscFileById(fileId,operationFlag);
            }

        }
        return 1;
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param fileId 【请填写功能名称】ID
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteClouddiscFileById(String fileId)
    {
        String operationFlag = CommonUtil.getUUID();
        ClouddiscFile clouddiscFile = clouddiscFileMapper.selectClouddiscFileById(fileId);
        if(ClouddiscFileConstants.DIRECTORY.equals(clouddiscFileMapper.selectClouddiscFileById(fileId).getFileType())){
            ClouddiscFile chlidClouddiscFile = new ClouddiscFile();
            chlidClouddiscFile.setParentId(clouddiscFile.getId());
            String[] ids = clouddiscFileMapper.selectClouddiscFileList(chlidClouddiscFile).stream().map(ClouddiscFile::getId).toArray(String[]::new);;
            clouddiscFileMapper.deleteClouddiscFileByIds(ids,operationFlag);
        }else{
            clouddiscFileMapper.deleteClouddiscFileById(fileId,operationFlag);
        }
        return 1;
    }

    @Override
    public List<ClouddiscFile> selectClouddiscFileByIds(String[] ids) {
        return clouddiscFileMapper.selectClouddiscFileByIds(ids);
    }
}
