package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 cloud_file_recycle
 * 
 * @author ruoyi
 * @date 2021-07-01
 */
public class CloudFileRecycle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String id;

    /** 回收站id */
    @Excel(name = "回收站id")
    private String recycleId;

    /** 文件id */
    @Excel(name = "文件id")
    private String fileId;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setRecycleId(String recycleId) 
    {
        this.recycleId = recycleId;
    }

    public String getRecycleId() 
    {
        return recycleId;
    }
    public void setFileId(String fileId) 
    {
        this.fileId = fileId;
    }

    public String getFileId() 
    {
        return fileId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("recycleId", getRecycleId())
            .append("fileId", getFileId())
            .toString();
    }
}
