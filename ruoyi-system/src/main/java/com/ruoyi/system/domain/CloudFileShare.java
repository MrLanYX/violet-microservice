package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 file_share
 * 
 * @author ruoyi
 * @date 2021-05-19
 */
public class CloudFileShare extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String fileShareId;

    /** 文件id */
    @Excel(name = "文件id")
    private String fileId;

    /** 分享id */
    @Excel(name = "分享id")
    private String shareId;

    public void setFileShareId(String fileShareId) 
    {
        this.fileShareId = fileShareId;
    }

    public String getFileShareId() 
    {
        return fileShareId;
    }
    public void setFileId(String fileId) 
    {
        this.fileId = fileId;
    }

    public String getFileId() 
    {
        return fileId;
    }
    public void setShareId(String shareId) 
    {
        this.shareId = shareId;
    }

    public String getShareId() 
    {
        return shareId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileShareId", getFileShareId())
            .append("fileId", getFileId())
            .append("shareId", getShareId())
            .toString();
    }
}
