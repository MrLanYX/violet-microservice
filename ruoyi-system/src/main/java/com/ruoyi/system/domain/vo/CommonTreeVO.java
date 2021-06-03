package com.ruoyi.system.domain.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.model.TreeNode;

/**
 * @author banrenhe
 */
public class CommonTreeVO extends TreeNode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3131377931616674582L;


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


	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;


	/** 更新时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "CommonTreeVO{" +
				", fileType='" + fileType + '\'' +
				", sourceName='" + sourceName + '\'' +
				", filePath='" + filePath + '\'' +
				", fileSuffix='" + fileSuffix + '\'' +
				", fileSize='" + fileSize + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
