package com.ruoyi.system.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import com.ruoyi.common.utils.file.SnowflakeIdWorker;
import com.ruoyi.system.domain.CloudChunkInfo;
import com.ruoyi.system.mapper.TChunkInfoMapper;
import com.ruoyi.system.service.ChunkService;
import org.springframework.stereotype.Service;


@Service
public class ChunkServiceImpl implements ChunkService {

	@Resource
	TChunkInfoMapper tChunkInfoMapper;
	
    @Override
    public int saveChunk(CloudChunkInfo chunk) {
    	chunk.setId(SnowflakeIdWorker.getUUID()+SnowflakeIdWorker.getUUID());
    	return tChunkInfoMapper.insertSelective(chunk);
    }

    @Override
    public ArrayList<Integer> checkChunk(CloudChunkInfo chunk) {
    	return tChunkInfoMapper.selectChunkNumbers(chunk);
    }

	@Override
	public boolean checkChunk(String identifier, Integer chunkNumber) {
		return false;
	}

}
