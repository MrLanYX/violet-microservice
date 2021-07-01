package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 cloud_cartoon_file
 * 
 * @author ruoyi
 * @date 2021-06-05
 */
public class CloudCartoonFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long cartoonFileId;

    /** 漫画id */
    @Excel(name = "漫画id")
    private String cartoonId;

    /** 文件id */
    @Excel(name = "文件id")
    private String fileId;

    public void setCartoonFileId(Long cartoonFileId) 
    {
        this.cartoonFileId = cartoonFileId;
    }

    public Long getCartoonFileId() 
    {
        return cartoonFileId;
    }
    public void setCartoonId(String cartoonId) 
    {
        this.cartoonId = cartoonId;
    }

    public String getCartoonId() 
    {
        return cartoonId;
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
            .append("cartoonFileId", getCartoonFileId())
            .append("cartoonId", getCartoonId())
            .append("fileId", getFileId())
            .toString();
    }
}
