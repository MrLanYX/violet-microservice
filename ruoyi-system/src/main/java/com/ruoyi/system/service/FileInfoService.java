package com.ruoyi.system.service;

import com.ruoyi.system.domain.CloudFileInfo;
import com.ruoyi.system.domain.CloudQueryInfo;

import java.util.List;

public interface FileInfoService {
	
	public int addFileInfo(CloudFileInfo fileInfo);
	
	public List<CloudFileInfo> selectFileByParams(CloudFileInfo fileInfo);
	
	 /**
     * 查询
     *
     * @param query 查询条件
     * @return List
     */
    List<CloudFileInfo> selectFileList(CloudQueryInfo query);
                    
    
    /**
     * 删除
     * @param tFileInfo
     * @return
     */
    int deleteFile(CloudFileInfo tFileInfo);
}
