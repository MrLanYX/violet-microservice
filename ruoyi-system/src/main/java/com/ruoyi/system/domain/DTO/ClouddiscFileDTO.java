package com.ruoyi.system.domain.DTO;

/**
 * @author brh
 * @description: TODO
 * @date 2021/7/5 0005 15:40
 */
public class ClouddiscFileDTO {
    private String parentId;
    private String id;
    private String shareId;
    private String shareCode;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }

    @Override
    public String toString() {
        return "ClouddiscFileDTO{" +
                "parentId='" + parentId + '\'' +
                ", id='" + id + '\'' +
                ", shareId='" + shareId + '\'' +
                ", shareCode='" + shareCode + '\'' +
                '}';
    }
}
