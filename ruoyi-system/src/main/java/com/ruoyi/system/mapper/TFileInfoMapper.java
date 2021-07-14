package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.CloudFileInfo;
import com.ruoyi.system.domain.CloudQueryInfo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TFileInfoMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(CloudFileInfo record);

    int insertSelective(CloudFileInfo record);

    CloudFileInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CloudFileInfo record);

    int updateByPrimaryKey(CloudFileInfo record);

	List<CloudFileInfo> selectFileByParams(CloudFileInfo fileInfo);
	
	List<CloudFileInfo> selectFileList(CloudQueryInfo query);

}