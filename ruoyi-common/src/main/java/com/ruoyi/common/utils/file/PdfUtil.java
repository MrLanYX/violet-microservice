package com.brh.utils;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author brh
 * @description:
 * @date 2021/3/12 0012 14:37
 */
public class PdfUtil {
    /**
     * @Description:
     * @param sourcePath 源路径
     * @param targetPath 目的路径
     * @author Administrator
     * @date 2021/3/12 0012 15:37
     * @return void
     */
    public static void pictureToPdf(String sourcePath, String targetPath){
        Document document = new Document();
        // 设置文档页边距
        document.setMargins(0, 0, 0, 0);
        File file2 = new File(targetPath);
        if (!file2.exists()){
            file2.mkdirs();
        }
        FileOutputStream fos = null;
        try {
            File file = new File(sourcePath);
            File[] files = file.listFiles();
            // 获取图片的宽高
            assert files != null;
            Image image1 = Image.getInstance(files[0].getAbsolutePath());
            float imageHeight = image1.getScaledHeight();
            float imageWidth = image1.getScaledWidth();
            fos = new FileOutputStream(file2.getAbsolutePath()+File.separator+files[0].getParentFile().getName()+".pdf");
            PdfWriter.getInstance(document, fos);
            // 打开文档
            document.open();
            Arrays.stream(files).filter(fl ->FileUtil.isPicture(fl.getAbsolutePath())).forEach(file1 -> {
                try {
                    // 获取图片的宽高
                    Image image = Image.getInstance(file1.getAbsolutePath());
                    // 设置页面宽高与图片一致
                    Rectangle rectangle = new Rectangle(imageWidth, imageHeight);
                    document.setPageSize(rectangle);
                    // 图片居中
                    image.setAlignment(Image.ALIGN_CENTER);
                    // 新建一页添加图片
                    document.newPage();
                    document.add(image);
                } catch (DocumentException | IOException e) {
                    e.printStackTrace();
                }
            });
            // 关闭文档
            document.close();
        } catch (Exception ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            try {
                assert fos != null;
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
