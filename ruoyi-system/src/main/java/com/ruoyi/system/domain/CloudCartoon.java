package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 cloud_cartoon
 * 
 * @author ruoyi
 * @date 2021-06-05
 */
public class CloudCartoon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 漫画id */
    private String cartoonId;

    /** 父id */
    @Excel(name = "父id")
    private String parentId;

    /** 漫画名 */
    @Excel(name = "漫画名")
    private String cartoonName;

    /** 漫画类型：1 漫画名，2章节名 */
    @Excel(name = "漫画类型：1 漫画名，2章节名")
    private String cartoonType;

    public void setCartoonId(String cartoonId) 
    {
        this.cartoonId = cartoonId;
    }

    public String getCartoonId() 
    {
        return cartoonId;
    }
    public void setParentId(String parentId) 
    {
        this.parentId = parentId;
    }

    public String getParentId() 
    {
        return parentId;
    }
    public void setCartoonName(String cartoonName) 
    {
        this.cartoonName = cartoonName;
    }

    public String getCartoonName() 
    {
        return cartoonName;
    }
    public void setCartoonType(String cartoonType) 
    {
        this.cartoonType = cartoonType;
    }

    public String getCartoonType() 
    {
        return cartoonType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cartoonId", getCartoonId())
            .append("parentId", getParentId())
            .append("cartoonName", getCartoonName())
            .append("cartoonType", getCartoonType())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
