package com.licheng.dchy.common.core.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 *- 通用工具类
 * @author shuyi
 *
 */
public class CommonUtil
{
    private CommonUtil()
    {

    }

    /**
     * 获取唯一id
     * @return
     */
    public static String getUUID()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取当前时间
     * @return
     */
    public static Date getCurrentDate()
    {
        return new Date();
    }

    /**
     * 获取当前时间毫秒值
     * @return
     */
    public static long getCurrentTime()
    {
        return new Date().getTime();
    }

    /**
     * uuid去除横杠
     * @return
     */
    public static String getUid()
    {
        return getUUID().replaceAll("-", "");
    }

    /**
     * 编码下载文件的文件名称
     * @param fileName
     * @param agent
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeDownFileName(String fileName, String agent) throws UnsupportedEncodingException
    {
        String codedfilename = null;
        if (null != agent && -1 != agent.indexOf("MSIE"))
        {
            String prefix = fileName.lastIndexOf(".") != -1 ? fileName.substring(0, fileName.lastIndexOf("."))
                    : fileName;
            String extension = fileName.lastIndexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".")) : "";
            String name = URLEncoder.encode(prefix, "UTF8");
            if (name.lastIndexOf("%0A") != -1)
            {
                name = name.substring(0, name.length() - 3);
            }
            codedfilename = name + extension;
        } else if (null != agent && -1 != agent.indexOf("Chrome"))
        {
            //Chrome
            codedfilename = URLEncoder.encode(fileName, "UTF-8");
            codedfilename = StringUtil.replace(codedfilename, "%28", "(").replace("%29", ")");//替换空格
            codedfilename = StringUtil.replace(codedfilename, "%7B", "{").replace("%7D", "}");//替换{}
        } else if (null != agent && -1 != agent.indexOf("Mozilla"))
        {
            //IE7+
            codedfilename = URLEncoder.encode(fileName, "UTF-8");
            codedfilename = StringUtil.replace(codedfilename, "+", "%20");//替换空格
        } else
        {
            codedfilename = fileName;
        }
        return codedfilename;
    }

    /**
     * 是否相等
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isEquals(String str1, String str2)
    {
        if (isEmpty(str1) || isEmpty(str2))
        {
            return false;
        }
        return str1.equals(str2);
    }

    /**
     * 判断是否为空
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj)
    {
        if (null == obj)
        {
            return true;
        } else if (obj instanceof String)
        {
            return obj.toString().trim().length() == 0;
        } else if (obj instanceof Collection)
        {
            Collection<?> coll = (Collection<?>) obj;
            return coll.size() == 0;
        } else if (obj instanceof Map)
        {
            Map<?, ?> map = (Map<?, ?>) obj;
            return map.size() == 0;
        } else if (obj.getClass().isArray())
        {
            return Array.getLength(obj) == 0;
        }
        return false;
    }

    /**
     * 判断非空
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj)
    {
        return !isEmpty(obj);
    }

    /**
     * 判断两个对象是否相等
     * @param o1
     * @param o2
     * @return
     */
    public static boolean isEq(Object o1, Object o2)
    {
        if (o1 == null && o2 == null)
        {
            return true;
        }
        return (o1 != null && o1.equals(o2)) || (o2 != null && o2.equals(o1));
    }

    /**
     * 用于路径的跨系统处理
     * @param realPath  待处理路径
     * @return
     */
    public static String eraseOsSeparator(String realPath)
    {
        if (CommonUtil.isEmpty(realPath))
        {
            return realPath;
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < realPath.length(); i++)
        {
            String ch = realPath.charAt(i) + "";
            if (ch.equals("/") || ch.equals("\\"))
            {
                ch = File.separator;
            }
            buf.append(ch);
        }
        return buf.toString();
    }

    public static final String SEED0 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static final String SEED1 = "0123456789";

    /**
     * -随机字符串
     * @param capacity          字符个数
     * @param includeLetter     是否包含字母
     * @return
     */
    public static String randomStr(int capacity, boolean includeLetter)
    {
        Random random = new Random();
        //指定字符串长度，拼接字符并toString
        StringBuffer sb = new StringBuffer();
        String str = includeLetter ? SEED0 : SEED1;
        for (int i = 0; i < capacity; i++)
        {
            //获取指定长度的字符串中任意一个字符的索引值
            int number = random.nextInt(str.length());
            //根据索引值获取对应的字符
            char charAt = str.charAt(number);
            sb.append(charAt);
        }
        return sb.toString();
    }

}
