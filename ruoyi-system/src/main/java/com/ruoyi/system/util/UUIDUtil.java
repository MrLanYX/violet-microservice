package com.ruoyi.system.util;

import java.util.UUID;

/**
 * @author banrenhe
 * @description: UUID
 * @date 2021/5/18 0018 17:42
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString();

    }
}
