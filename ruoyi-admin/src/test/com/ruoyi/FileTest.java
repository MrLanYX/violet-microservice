package com.ruoyi;

import com.ruoyi.system.mapper.ClouddiscFileMapper;
import com.ruoyi.system.service.IClouddiscFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Administrator
 * @description: TODO
 * @date 2021/6/28 0028 10:58
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FileTest {

    @Autowired
    private IClouddiscFileService clouddiscFileService;

    @Test
    public void fileCollect(){
        clouddiscFileService.fileCollect("a6d2388dc487421ebbf04f196b3e2bc9","73fc9814b4d84e3fbb09a2e0cd5354ef");
        System.out.println("------");

    }
}
