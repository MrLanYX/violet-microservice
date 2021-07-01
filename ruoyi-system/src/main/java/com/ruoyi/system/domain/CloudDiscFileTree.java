package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @description: TODO
 * @date 2021/6/28 0028 9:26
 */
public class CloudDiscFileTree {
    protected String id;
    protected String parentId;
    protected List<CloudDiscFileTree> children = new ArrayList<>();
    public void add(CloudDiscFileTree node) {
        children.add(node);
    }
    /** 文件类型 */
    private String fileType;

    /** 源文件名 */
    private String sourceName;

    /** 存储的文件路径 */
    private String filePath;

    /** 文件后缀 */
    private String fileSuffix;

    /** 文件大小（单位：KB） */
    private String fileSize;

    public String getId() {
        return id;
    }

    public String getParentId() {
        return parentId;
    }

    public List<CloudDiscFileTree> getChildren() {
        return children;
    }

    public String getFileType() {
        return fileType;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setChildren(List<CloudDiscFileTree> children) {
        this.children = children;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "CloudDiscFileTree{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", children=" + children +
                ", fileType='" + fileType + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileSuffix='" + fileSuffix + '\'' +
                ", fileSize='" + fileSize + '\'' +
                '}';
    }
}
