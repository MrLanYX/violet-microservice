package com.ruoyi.system.util;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.system.domain.CloudChunkInfo;
import com.ruoyi.system.domain.CloudFileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;

/**
 * 文件工具类
 * @author 洋葱骑士
 *
 */
public class FileInfoUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(FileInfoUtils.class);

    /**
     * 默认上传的地址
     */
    private static final String DEFAULT_BASE_DIR = RuoYiConfig.getProfile();

    public static String generatePath(CloudChunkInfo chunk) {
        StringBuilder sb = new StringBuilder();
        sb.append(DEFAULT_BASE_DIR).append("/").append(chunk.getIdentifier());
        //判断uploadFolder/identifier 路径是否存在，不存在则创建
        if (!Files.isWritable(Paths.get(sb.toString()))) {
        	logger.info("path not exist,create path: {}", sb.toString());
            try {
                Files.createDirectories(Paths.get(sb.toString()));
            } catch (IOException e) {
            	logger.error(e.getMessage(), e);
            }
        }

        return sb.append("/")
                .append(chunk.getFilename())
                .append("-")
                .append(chunk.getChunkNumber()).toString();
    }

    public static String filePath(CloudChunkInfo chunk){
        return DEFAULT_BASE_DIR + "/" + chunk.getIdentifier() + "/" + chunk.getFilename();
    }
    /**
     * 文件合并
     *
     * @param fileInfo
     */
    public static String merge(CloudFileInfo fileInfo){
        String file = DEFAULT_BASE_DIR + "/" + fileInfo.getIdentifier() + "/" + fileInfo.getFilename();
        String folder = DEFAULT_BASE_DIR + "/" + fileInfo.getIdentifier();
    	//默认合并成功
    	String rlt = "200";
        boolean fileExists = Files.exists(Paths.get(file), LinkOption.NOFOLLOW_LINKS);
        try {
        	//先判断文件是否存在
        	if(fileExists) {
        		//文件已存在
        		rlt = "300";
        	}else {
        		//不存在的话，进行合并
        		Files.createFile(Paths.get(file));
                Files.list(Paths.get(folder))
                        .filter(path -> !path.getFileName().toString().equals(fileInfo.getFilename()))
                        .sorted((o1, o2) -> {
                            String p1 = o1.getFileName().toString();
                            String p2 = o2.getFileName().toString();
                            int i1 = p1.lastIndexOf("-");
                            int i2 = p2.lastIndexOf("-");
                            return Integer.valueOf(p2.substring(i2)).compareTo(Integer.valueOf(p1.substring(i1)));
                        })
                        .forEach(path -> {
                            try {
                                //以追加的形式写入文件
                                Files.write(Paths.get(file), Files.readAllBytes(path), StandardOpenOption.APPEND);
                                //合并后删除该块
                                Files.delete(path);
                            } catch (IOException e) {
                            	logger.error(e.getMessage(), e);
                            }
                        });
        	}
        } catch (IOException e) {
        	logger.error(e.getMessage(), e);
        	//合并失败
        	rlt = "400";
        }
        
        return rlt;
    }
    
    /**
     * 根据文件的全路径名判断文件是否存在
     * @param chunk
     * @return
     */
    public static boolean fileExists(CloudChunkInfo chunk) {
    	boolean fileExists = false;
    	Path path = Paths.get(filePath(chunk));
    	fileExists = Files.exists(path, LinkOption.NOFOLLOW_LINKS);
    	return fileExists;
    }
}
