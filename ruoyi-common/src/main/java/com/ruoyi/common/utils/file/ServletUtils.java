package com.ruoyi.common.utils.file;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Servlet工具类
 *
 * @author 洋葱骑士
 */
public class ServletUtils {

    /**
     * 文件下载时用于写文件头部信息
     * @param request http请求
     * @param response http响应
     * @param fileName 文件名
     */
    public static void setFileDownloadHeader(HttpServletRequest request,
            HttpServletResponse response, String fileName) {
        try {
            String encodedFileName = null;
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && agent.contains("MSIE")) {
                encodedFileName = URLEncoder.encode(fileName, String.valueOf(StandardCharsets.UTF_8));
            } else if (null != agent && agent.contains("Mozilla")) {
                encodedFileName = new String(fileName.getBytes(StandardCharsets.UTF_8),
                        StandardCharsets.ISO_8859_1);
            } else {
                encodedFileName = URLEncoder.encode(fileName, String.valueOf(StandardCharsets.UTF_8));
            }

            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + encodedFileName + "\"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
