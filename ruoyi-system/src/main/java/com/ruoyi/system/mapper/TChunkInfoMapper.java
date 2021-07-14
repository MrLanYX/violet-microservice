package com.ruoyi.system.mapper;

import java.util.ArrayList;

import com.ruoyi.system.domain.CloudChunkInfo;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface TChunkInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(CloudChunkInfo record);

    int insertSelective(CloudChunkInfo record);

    CloudChunkInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CloudChunkInfo record);

    int updateByPrimaryKey(CloudChunkInfo record);
    
    ArrayList<Integer> selectChunkNumbers(CloudChunkInfo record);
}