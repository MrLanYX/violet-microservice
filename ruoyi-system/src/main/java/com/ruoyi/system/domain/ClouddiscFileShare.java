package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 clouddisc_file_share
 * 
 * @author ruoyi
 * @date 2021-05-18
 */
public class ClouddiscFileShare extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @Excel(name = "主键ID")
    private String shareId;

    /** 文件id */
    @Excel(name = "文件id")
    private String fileName;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 分享地址 */
    @Excel(name = "分享地址")
    private String shareUrl;

    /** 有效时间（单位：天） */
    @Excel(name = "有效时间", readConverterExp = "单=位：天")
    private String effectiveTime;

    /** 开始分享时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始分享时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date shareStartTime;

    /** 分享类型：永久，有时间效 */
    @Excel(name = "分享类型：永久，有时效")
    private String shareType;

    /** 验证码 */
    @Excel(name = "验证码")
    private String checkCode;

    /** 删除标志 */
    private String delFlag;

    public void setShareId(String shareId) 
    {
        this.shareId = shareId;
    }

    public String getShareId() 
    {
        return shareId;
    }
    public void setFileName(String fileId)
    {
        this.fileName = fileId;
    }

    public String getFileName()
    {
        return fileName;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setShareUrl(String shareUrl) 
    {
        this.shareUrl = shareUrl;
    }

    public String getShareUrl() 
    {
        return shareUrl;
    }
    public void setEffectiveTime(String effectiveTime) 
    {
        this.effectiveTime = effectiveTime;
    }

    public String getEffectiveTime() 
    {
        return effectiveTime;
    }
    public void setShareStartTime(Date shareStartTime) 
    {
        this.shareStartTime = shareStartTime;
    }

    public Date getShareStartTime() 
    {
        return shareStartTime;
    }
    public void setShareType(String shareType) 
    {
        this.shareType = shareType;
    }

    public String getShareType() 
    {
        return shareType;
    }
    public void setCheckCode(String checkCode) 
    {
        this.checkCode = checkCode;
    }

    public String getCheckCode() 
    {
        return checkCode;
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
            .append("shareId", getShareId())
            .append("fileName", getFileName())
            .append("userId", getUserId())
            .append("shareUrl", getShareUrl())
            .append("effectiveTime", getEffectiveTime())
            .append("shareStartTime", getShareStartTime())
            .append("shareType", getShareType())
            .append("checkCode", getCheckCode())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
