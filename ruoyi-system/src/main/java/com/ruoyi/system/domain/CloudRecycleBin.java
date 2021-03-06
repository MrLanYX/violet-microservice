package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 cloud_recycle_bin
 * 
 * @author ruoyi
 * @date 2021-07-01
 */
public class CloudRecycleBin extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String id;

    /** 文件id */
    @Excel(name = "文件id")
    private String fileName;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /** 删除标记 */
    private String delFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setFileName(String fileId)
    {
        this.fileName = fileId;
    }

    public String getFileName()
    {
        return fileName;
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
        return "CloudRecycleBin{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", userId='" + userId + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
