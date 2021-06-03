package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 clouddisc_file
 * 
 * @author ruoyi
 * @date 2021-05-18
 */
public class ClouddiscFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */

    private String id;

    /** 父节点id */
    @Excel(name = "父节点id")
    private String parentId;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String fileType;

    /** 源文件名 */
    @Excel(name = "源文件名")
    private String sourceName;

    /** 存储的文件路径 */
    @Excel(name = "存储的文件路径")
    private String filePath;

    /** 文件后缀 */
    @Excel(name = "文件后缀")
    private String fileSuffix;

    /** 文件大小（单位：KB） */
    @Excel(name = "文件大小", readConverterExp = "单=位：KB")
    private String fileSize;

    /** 操作标志 */
    @Excel(name = "操作标志")
    private String operationFlag;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;

    /** 是否可恢复 */
    @Excel(name = "是否可恢复")
    private String recoverFlag;

    /** 删除标志 */
    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }

    public String getParentId() 
    {
        return parentId;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public String getFileType() 
    {
        return fileType;
    }
    public void setSourceName(String sourceName) 
    {
        this.sourceName = sourceName;
    }

    public String getSourceName() 
    {
        return sourceName;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setFileSuffix(String fileSuffix) 
    {
        this.fileSuffix = fileSuffix;
    }

    public String getFileSuffix() 
    {
        return fileSuffix;
    }
    public void setFileSize(String fileSize) 
    {
        this.fileSize = fileSize;
    }

    public String getFileSize() 
    {
        return fileSize;
    }
    public void setOperationFlag(String operationFlag) 
    {
        this.operationFlag = operationFlag;
    }

    public String getOperationFlag() 
    {
        return operationFlag;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }
    public void setRecoverFlag(String recoverFlag) 
    {
        this.recoverFlag = recoverFlag;
    }

    public String getRecoverFlag() 
    {
        return recoverFlag;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileId", getId())
            .append("parentId", getParentId())
            .append("userId", getUserId())
            .append("fileType", getFileType())
            .append("sourceName", getSourceName())
            .append("filePath", getFilePath())
            .append("fileSuffix", getFileSuffix())
            .append("fileSize", getFileSize())
            .append("operationFlag", getOperationFlag())
            .append("uploadTime", getUploadTime())
            .append("recoverFlag", getRecoverFlag())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
