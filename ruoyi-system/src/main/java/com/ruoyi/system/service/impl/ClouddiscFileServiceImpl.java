package com.ruoyi.system.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import com.ruoyi.common.core.domain.model.TreeNode;
import com.ruoyi.common.utils.CommonUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.TreeUtil;
import com.ruoyi.common.utils.file.FileTypeUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.constants.ClouddiscFileConstants;
import com.ruoyi.system.domain.CloudDiscFileTree;
import com.ruoyi.system.domain.CloudFileRecycle;
import com.ruoyi.system.domain.CloudRecycleBin;
import com.ruoyi.system.domain.DTO.ClouddiscFileDTO;
import com.ruoyi.system.domain.vo.CommonTreeVO;
import com.ruoyi.system.mapper.CloudFileRecycleMapper;
import com.ruoyi.system.mapper.CloudRecycleBinMapper;
import com.ruoyi.system.util.TreeVOUtil;
import org.springframework.beans.BeanUtils;
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
public class ClouddiscFileServiceImpl implements IClouddiscFileService {
    @Autowired
    private ClouddiscFileMapper clouddiscFileMapper;

    @Autowired
    private CloudRecycleBinMapper cloudRecycleBinMapper;

    @Autowired
    private CloudFileRecycleMapper cloudFileRecycleMapper;
    /**
     * 查询【请填写功能名称】
     *
     * @param fileId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public ClouddiscFile selectClouddiscFileById(String fileId) {
        ClouddiscFile clouddiscFile = clouddiscFileMapper.selectClouddiscFileById(fileId);
        Assert.isFalse(!SecurityUtils.getUserId().equals(clouddiscFile.getUserId()),"查不到此数据");
        return clouddiscFile;
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param clouddiscFile 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CommonTreeVO> selectClouddiscFileList(ClouddiscFile clouddiscFile) {

        List<ClouddiscFile> clouddiscFiles = clouddiscFileMapper.selectClouddiscFileList(clouddiscFile)
                .stream()
                .filter(recycleBin-> SecurityUtils.getUserId().equals(recycleBin.getUserId()))
                .collect(Collectors.toList());
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

        if (CommonUtil.isNotEmpty(multipartFiles) && CommonUtil.isNotEmpty(clouddiscFile.getParentId())) {

            for (MultipartFile multipartFile :
                    multipartFiles) {
                ClouddiscFile childClouddiscFile = new ClouddiscFile();
                childClouddiscFile.setId(CommonUtil.getUid());
                childClouddiscFile.setParentId(clouddiscFile.getParentId());
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
        } else {
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
    public int updateClouddiscFile(ClouddiscFile clouddiscFile) {
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
    public int deleteClouddiscFileByIds(String[] fileIds) {
        Assert.notNull(fileIds,"传入的数据为空");
        for (String fileId:
        fileIds) {
            deleteClouddiscFileById(fileId);
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
    public int deleteClouddiscFileById(String fileId) {
        Assert.notNull(fileId,"传入的数据为空");
        List<ClouddiscFile> clouddiscFileList = clouddiscFileMapper.getFilesByParentId(fileId);
        String[] ids = clouddiscFileList.stream().map(ClouddiscFile::getId).toArray(String[]::new);

        CloudRecycleBin cloudRecycleBin = new CloudRecycleBin();
        cloudRecycleBin.setId(CommonUtil.getUid());
        ClouddiscFile clouddiscFile = clouddiscFileMapper.selectClouddiscFileById(fileId);
        cloudRecycleBin.setFileName(clouddiscFile.getSourceName());
        cloudRecycleBin.setCreateTime(DateUtils.getNowDate());
        cloudRecycleBin.setCreateBy(SecurityUtils.getUserId());
        //删除文件
        clouddiscFileMapper.deleteClouddiscFileByIds(ids);
        //插入回收站
        cloudRecycleBinMapper.insertCloudRecycleBin(cloudRecycleBin);
        List<CloudFileRecycle> cloudFileRecycleList = new ArrayList<>();
        for (String fileId1: ids) {
            CloudFileRecycle cloudFileRecycle = new CloudFileRecycle();
            cloudFileRecycle.setId(CommonUtil.getUid());
            cloudFileRecycle.setRecycleId(cloudRecycleBin.getId());
            cloudFileRecycle.setFileId(fileId1);
            cloudFileRecycleList.add(cloudFileRecycle);
        }
        //批量插入删除关联
        cloudFileRecycleMapper.insertCloudFileRecycles(cloudFileRecycleList);
        return 1;
    }

    @Override
    public List<ClouddiscFile> selectClouddiscFileByIds(String ids) {
        return clouddiscFileMapper.selectClouddiscFileByIds(ids).stream()
                .filter(recycleBin-> SecurityUtils.getUserId().equals(recycleBin.getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommonTreeVO> getFilesByParentId(String parentId) {
        List<ClouddiscFile> list = clouddiscFileMapper.getFilesByParentId(parentId)
                .stream()
                .filter(recycleBin-> SecurityUtils.getUserId().equals(recycleBin.getUserId()))
                .collect(Collectors.toList());
        if(CommonUtil.isNotEmpty(list)){
            return TreeVOUtil.getBuildTree(list, clouddiscFileMapper.selectClouddiscFileById(parentId).getParentId());
        }else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean fileCollect(ClouddiscFileDTO clouddiscFileDTO) {
        List<ClouddiscFile> list = clouddiscFileMapper.getFilesByParentId(clouddiscFileDTO.getId());
        //获取根节点
        String rootId = list.stream()
                .filter(file -> file.getId().equals(clouddiscFileDTO.getId()))
                .collect(Collectors.toList())
                .get(0)
                .getParentId();
        //构建树
        List<CloudDiscFileTree> cloudDiscFileTreeList = getBuildTree(list, rootId);
        //重置树的各节点的id以及parentID
        List<CloudDiscFileTree> sourceTreelists = setIdParentIDAndfindChildren(cloudDiscFileTreeList, clouddiscFileDTO.getParentId());
        List<ClouddiscFile> clouddiscFileList = new ArrayList<>();
        //树转列表
        List<ClouddiscFile> lists = treeToList(sourceTreelists, clouddiscFileList);
        //保存数据
        clouddiscFileMapper.insertClouddiscFiles(lists);
        return true;
    }

    /**
     * @param sourceTreelists         源树
     * @param targetClouddiscFileList 目标列表
     * @return java.util.List<com.ruoyi.system.domain.ClouddiscFile>
     * @Description: 文件树转文件列表
     * @author Administrator
     * @date 2021/6/29 0029 9:07
     */
    private List<ClouddiscFile> treeToList(List<CloudDiscFileTree> sourceTreelists, List<ClouddiscFile> targetClouddiscFileList) {
        for (CloudDiscFileTree treelist :
                sourceTreelists) {
            ClouddiscFile clouddiscFile = getClouddiscFile();
            //复制对象
            BeanUtils.copyProperties(treelist, clouddiscFile, ClouddiscFile.class);
            targetClouddiscFileList.add(clouddiscFile);
            if (CommonUtil.isNotEmpty(treelist.getChildren())) {
                treeToList(treelist.getChildren(), targetClouddiscFileList);
            }
        }
        return targetClouddiscFileList;
    }

    private ClouddiscFile getClouddiscFile() {
        ClouddiscFile clouddiscFile = new ClouddiscFile();
        clouddiscFile.setUserId(SecurityUtils.getUserId());
        clouddiscFile.setCreateTime(DateUtils.getNowDate());
        clouddiscFile.setCreateBy(SecurityUtils.getUsername());
        clouddiscFile.setUploadTime(DateUtils.getNowDate());
        clouddiscFile.setDelFlag(ClouddiscFileConstants.NOT_DEL_FLAG);
        return clouddiscFile;
    }

    /**
     * @param list
     * @param parentId
     * @return java.util.List<com.ruoyi.system.domain.CloudDiscFileTree>
     * @Description: 重置节点的id ，以及parentId，并递归下级节点做此操作
     * @author Administrator
     * @date 2021/6/29 0029 9:23
     */
    private List<CloudDiscFileTree> setIdParentIDAndfindChildren(List<CloudDiscFileTree> list, String parentId) {

        for (CloudDiscFileTree cloudDiscFileTree : list) {
            String id = CommonUtil.getUid();
            cloudDiscFileTree.setId(id);
            cloudDiscFileTree.setParentId(parentId);
            if (!CommonUtil.isEmpty(cloudDiscFileTree.getChildren())) {
                setIdParentIDAndfindChildren(cloudDiscFileTree.getChildren(), id);
            }
        }
        return list;
    }

    /**
     * @param cloudDiscFileTree 文件列表
     * @param root              根节点
     * @return java.util.List<com.ruoyi.system.domain.CloudDiscFileTree>
     * @Description: 文件列表构建树
     * @author Administrator
     * @date 2021/6/29 0029 9:13
     */
    private static List<CloudDiscFileTree> getBuildTree(List<ClouddiscFile> cloudDiscFileTree, String root) {

        List<CloudDiscFileTree> treeList = cloudDiscFileTree.stream()
                .map(commonTree -> {
                    CloudDiscFileTree node = new CloudDiscFileTree();
                    //复制对象
                    BeanUtils.copyProperties(commonTree, node, CloudDiscFileTree.class);
                    return node;
                }).collect(Collectors.toList());
        return buildByRecursive(treeList, root);
    }

    /**
     * @param cloudDiscFileTree 文件树列表
     * @param root              根节点
     * @return java.util.List<com.ruoyi.system.domain.CloudDiscFileTree>
     * @Description: 文件树列表转文件树
     * @author Administrator
     * @date 2021/6/29 0029 9:15
     */
    private static List<CloudDiscFileTree> buildByRecursive(List<CloudDiscFileTree> cloudDiscFileTree, Object root) {
        List<CloudDiscFileTree> trees = new ArrayList<>();
        for (CloudDiscFileTree treeNode : cloudDiscFileTree) {
            if (root.equals(treeNode.getParentId())) {
                trees.add(findChildren(treeNode, cloudDiscFileTree));
            }
        }
        return trees;
    }

    /**
     * @param treeNode
     * @param treeNodes
     * @return com.ruoyi.system.domain.CloudDiscFileTree
     * @Description: 寻找文件子节点
     * @author Administrator
     * @date 2021/6/29 0029 9:21
     */
    private static CloudDiscFileTree findChildren(CloudDiscFileTree treeNode, List<CloudDiscFileTree> treeNodes) {
        for (CloudDiscFileTree it : treeNodes) {
            if (treeNode.getId().equals(it.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                } else {
                    treeNode.add(findChildren(it, treeNodes));
                }

            }
        }
        return treeNode;
    }
}
