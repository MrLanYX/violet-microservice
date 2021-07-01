package com.ruoyi.system.domain.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Administrator
 * @description: TODO
 * @date 2021/6/29 0029 15:12
 */
public class ClouddiscFileShareDTO {

    /** 有效时间（单位：天） */
    private String effectiveTime;

    /** 分享类型：永久，有时间效 */
    private String shareType;

    private String[] fileIds;

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public String getShareType() {
        return shareType;
    }

    public String[] getFileIds() {
        return fileIds;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public void setFileIds(String[] fileIds) {
        this.fileIds = fileIds;
    }

    @Override
    public String toString() {
        return "ClouddiscFileShareDTO{" +
                "effectiveTime='" + effectiveTime + '\'' +
                ", shareType='" + shareType + '\'' +
                ", fileIds=" + Arrays.toString(fileIds) +
                '}';
    }
}
