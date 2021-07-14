package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import com.ruoyi.common.utils.CommonUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.file.FileTypeUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.SnowflakeIdWorker;
import com.ruoyi.system.constants.ClouddiscFileConstants;
import com.ruoyi.system.domain.CloudFileInfo;
import com.ruoyi.system.domain.CloudQueryInfo;
import com.ruoyi.system.domain.ClouddiscFile;
import com.ruoyi.system.mapper.ClouddiscFileMapper;
import com.ruoyi.system.mapper.TFileInfoMapper;
import com.ruoyi.system.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 文件处理类
 * @author 洋葱骑士
 *
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {

	@Autowired
	TFileInfoMapper tFileInfoMapper;

	@Autowired
	private ClouddiscFileMapper clouddiscFileMapper;
    @Override
	@Transactional(rollbackFor = Exception.class)
    public int addFileInfo(CloudFileInfo fileInfo) {
    	fileInfo.setId(SnowflakeIdWorker.getUUID()+SnowflakeIdWorker.getUUID());
		ClouddiscFile childClouddiscFile = new ClouddiscFile();
		childClouddiscFile.setId(CommonUtil.getUid());
		childClouddiscFile.setParentId(fileInfo.getParentId());
		childClouddiscFile.setSourceName(fileInfo.getFilename());
		childClouddiscFile.setFileType(ClouddiscFileConstants.Document);
		childClouddiscFile.setFileSize(String.valueOf(fileInfo.getTotalSize()));
		childClouddiscFile.setFileSuffix(FileTypeUtils.getFileType(Objects.requireNonNull(fileInfo.getFilename())));
		childClouddiscFile.setFilePath(fileInfo.getLocation());
		childClouddiscFile.setUserId(SecurityUtils.getUserId());
		childClouddiscFile.setCreateTime(DateUtils.getNowDate());
		childClouddiscFile.setCreateBy(SecurityUtils.getUsername());
		childClouddiscFile.setUploadTime(DateUtils.getNowDate());
		clouddiscFileMapper.insertClouddiscFile(childClouddiscFile);
        return tFileInfoMapper.insertSelective(fileInfo);
    }

    @Override
    public List<CloudFileInfo> selectFileByParams(CloudFileInfo fileInfo) {
        return tFileInfoMapper.selectFileByParams(fileInfo);
    }

    @Override
	public List<CloudFileInfo> selectFileList(CloudQueryInfo query) {
		return tFileInfoMapper.selectFileList(query);
	}

	@Override
	public int deleteFile(CloudFileInfo tFileInfo) {
		CloudFileInfo t = new CloudFileInfo();
		t.setId(tFileInfo.getId());
		t.setDelFlag("1");
		return tFileInfoMapper.updateByPrimaryKeySelective(t);
	}
}
