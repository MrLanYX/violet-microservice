package com.ruoyi.common.utils.file;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ZipUtils {

    /**
     * 默认编码为GBK
     */
    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * @param file zip压缩文件
     * @return void
     * @Author zhang
     * @brief 检测file是否为zip压缩包
     */
    public static boolean isZipFile(File file) {

        boolean flag = false;
        if (file.exists() && file.getName().endsWith(".zip")) {
            flag = true;
        }
        return flag;
    }

    /**
     * @param file     zip压缩包
     * @param destDir  解压缩目录
     * @param encoding 编码
     * @return boolean
     * @Author zhang
     * @brief 解压缩zip
     */
    public static boolean unzip(File file, String destDir, String... encoding) throws Exception {
        if (!file.exists()) {
            throw new IllegalArgumentException("请检查文件" + file.getName() + "是否存在");
        }

        File destFile = new File(destDir);
        if (!destFile.exists()) {
            destFile.mkdir();
        }
        ZipFile zipFile;
        try {
            zipFile = new ZipFile(file);
            String encodingStr = DEFAULT_ENCODING;
            if (encoding.length > 0) {
                encodingStr = encoding[0];
            }
            zipFile.setFileNameCharset(encodingStr);
            zipFile.extractAll(destDir);
        } catch (Exception e) {
            throw new Exception("文件解压缩失败!");
        }
        return true;
    }

    /**
     * 使用给定密码解压指定的ZIP压缩文件到当前目录
     *
     * @param zip    指定的ZIP压缩文件
     * @param passwd ZIP文件的密码
     * @return 解压后文件数组
     * @throws ZipException 压缩文件有损坏或者解压缩失败抛出
     * @Author zhang
     */
    public static File[] unzip(String zip, String passwd) throws ZipException {
        File zipFile = new File(zip);
        File parentDir = zipFile.getParentFile();
        return unzip(zipFile, parentDir.getAbsolutePath(), passwd);
    }

    /**
     * 使用给定密码解压指定的ZIP压缩文件到指定目录
     * <p>
     * 如果指定目录不存在,可以自动创建,不合法的路径将导致异常被抛出
     *
     * @param zip    指定的ZIP压缩文件
     * @param dest   解压目录
     * @param passwd ZIP文件的密码
     * @return 解压后文件数组
     * @throws ZipException 压缩文件有损坏或者解压缩失败抛出
     * @Author zhang
     */
    public static File[] unzip(String zip, String dest, String passwd) throws ZipException {
        File zipFile = new File(zip);
        return unzip(zipFile, dest, passwd);
    }

    /**
     * 使用给定密码解压指定的ZIP压缩文件到指定目录
     * <p>
     * 如果指定目录不存在,可以自动创建,不合法的路径将导致异常被抛出
     *
     * @param zipFile 指定的ZIP压缩文件
     * @param dest    解压目录
     * @param passwd  ZIP文件的密码
     * @return 解压后文件数组
     * @throws ZipException 压缩文件有损坏或者解压缩失败抛出
     * @Author zhang
     */
    public static File[] unzip(File zipFile, String dest, String passwd) throws ZipException {
        ZipFile zFile = new ZipFile(zipFile);
        zFile.setFileNameCharset(DEFAULT_ENCODING);
        return getFiles(dest, passwd, zFile);
    }

    /**
     * 使用给定密码解压指定的ZIP压缩文件到指定目录
     * <p>
     * 如果指定目录不存在,可以自动创建,不合法的路径将导致异常被抛出
     *
     * @param zipFile 指定的ZIP压缩文件
     * @param dest    解压目录
     * @param passwd  ZIP文件的密码
     * @return 解压后文件数组
     * @throws ZipException 压缩文件有损坏或者解压缩失败抛出
     * @Author zhang
     */
    public static File[] unzip(File zipFile, String dest, String passwd,
                               String... encoding) throws ZipException {
        ZipFile zFile = new ZipFile(zipFile);
        String encodingStr = DEFAULT_ENCODING;
        if (encoding.length > 0) {
            encodingStr = encoding[0];
        }
        zFile.setFileNameCharset(encodingStr);
        return getFiles(dest, passwd, zFile);
    }

    public static File[] getFiles(String dest, String passwd, ZipFile zFile) throws ZipException {
        if (!zFile.isValidZipFile()) {
            throw new ZipException("压缩文件不合法,可能被损坏.");
        }
        File destDir = new File(dest);
        if (destDir.isDirectory() && !destDir.exists()) {
            destDir.mkdirs();
        }
        if (zFile.isEncrypted()) {
            zFile.setPassword(passwd.toCharArray());
        }
        zFile.extractAll(dest);

        List<FileHeader> headerList = zFile.getFileHeaders();
        List<File> extractedFileList = new ArrayList<>();
        headerList.stream().filter(fileHeader -> !fileHeader.isDirectory()).forEach(fileHeader ->
                extractedFileList.add(new File(destDir, fileHeader
                        .getFileName())));
        for (FileHeader fileHeader : headerList) {
            if (!fileHeader.isDirectory()) {
                extractedFileList.add(new File(destDir, fileHeader
                        .getFileName()));
            }
        }
        File[] extractedFiles = new File[extractedFileList.size()];
        extractedFileList.toArray(extractedFiles);
        return extractedFiles;
    }

    /**
     * 压缩指定文件到当前文件夹
     *
     * @param src 要压缩的指定文件
     * @return 最终的压缩文件存放的绝对路径, 如果为null则说明压缩失败.
     * @Author zhang
     */
    public static String zip(String src) throws Exception {
        return zip(src, null);
    }

    /**
     * 使用给定密码压缩指定文件或文件夹到当前目录
     *
     * @param src    要压缩的文件
     * @param passwd 压缩使用的密码
     * @return 最终的压缩文件存放的绝对路径, 如果为null则说明压缩失败.
     * @Author zhang
     */
    public static String zip(String src, String passwd) throws Exception {
        return zip(src, null, passwd);
    }

    /**
     * 使用给定密码压缩指定文件或文件夹到当前目录
     *
     * @param src    要压缩的文件
     * @param dest   压缩文件存放路径
     * @param passwd 压缩使用的密码
     * @return 最终的压缩文件存放的绝对路径, 如果为null则说明压缩失败.
     * @Author zhang
     */
    public static String zip(String src, String dest, String passwd) throws Exception {
        return zip(src, dest, true, passwd);
    }

    /**
     * 构建压缩文件存放路径,如果不存在将会创建
     * 传入的可能是文件名或者目录,也可能不传,此方法用以转换最终压缩文件的存放路径
     *
     * @param srcFile   源文件
     * @param destParam 压缩目标路径
     * @return 正确的压缩文件存放路径
     * @Author zhang
     */
    private static String buildDestinationZipFilePath(File srcFile, String destParam) {
        if (StringUtils.isEmpty(destParam)) {
            if (srcFile.isDirectory()) {
                destParam = srcFile.getParent() + File.separator + srcFile.getName() + ".zip";
            } else {
                String fileName = srcFile.getName().substring(0, srcFile.getName().lastIndexOf("."));
                destParam = srcFile.getParent() + File.separator + fileName + ".zip";
            }
        } else {
            // 在指定路径不存在的情况下将其创建出来
            createDestDirectoryIfNecessary(destParam);
            if (destParam.endsWith(File.separator)) {
                String fileName;
                if (srcFile.isDirectory()) {
                    fileName = srcFile.getName();
                } else {
                    fileName = srcFile.getName().substring(0, srcFile.getName().lastIndexOf("."));
                }
                destParam += fileName + ".zip";
            }
        }
        return destParam;
    }

    /**
     * 使用给定密码压缩指定文件或文件夹到指定位置.
     * <p>
     * dest可传最终压缩文件存放的绝对路径,也可以传存放目录,也可以传null或者"".<br />
     * 如果传null或者""则将压缩文件存放在当前目录,即跟源文件同目录,压缩文件名取源文件名,以.zip为后缀;<br />
     * 如果以路径分隔符(File.separator)结尾,则视为目录,压缩文件名取源文件名,以.zip为后缀,否则视为文件名.
     *
     * @param src         要压缩的文件或文件夹路径
     * @param dest        压缩文件存放路径
     * @param isCreateDir 是否在压缩文件里创建目录,仅在压缩文件为目录时有效.<br />
     *                    如果为false,将直接压缩目录下文件到压缩文件.
     * @param passwd      压缩使用的密码
     * @return 最终的压缩文件存放的绝对路径, 如果为null则说明压缩失败.
     * @Author zhang
     */
    public static String zip(String src, String dest, boolean isCreateDir, String passwd) throws Exception {
        File srcFile = new File(src);
        dest = buildDestinationZipFilePath(srcFile, dest);
        ZipParameters parameters = new ZipParameters();
        // 压缩方式
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        // 压缩级别
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        if (!StringUtils.isEmpty(passwd)) {
            parameters.setEncryptFiles(true);
            // 加密方式
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
            parameters.setPassword(passwd.toCharArray());
        }
        ZipFile zipFile = new ZipFile(dest);
        if (srcFile.isDirectory()) {
            // 如果不创建目录的话,将直接把给定目录下的文件压缩到压缩文件,即没有目录结构
            if (!isCreateDir) {
                File[] subFiles = srcFile.listFiles();
                if (subFiles != null && subFiles.length > 0) {
                    for (File f : subFiles) {
                        if (f.isDirectory()) {
                            zipFile.addFolder(f, parameters);
                        } else {
                            zipFile.addFile(f, parameters);
                        }
                    }
                }
                return dest;
            }
            zipFile.addFolder(srcFile, parameters);
        } else {
            zipFile.addFile(srcFile, parameters);
        }
        return dest;
    }

    /**
     * 在必要的情况下创建压缩文件存放目录,比如指定的存放路径并没有被创建
     *
     * @param destParam 指定的存放路径,有可能该路径并没有被创建
     * @Author zhang
     */
    private static void createDestDirectoryIfNecessary(String destParam) {
        File destDir;
        if (destParam.endsWith(File.separator)) {
            destDir = new File(destParam);
        } else {
            destDir = new File(destParam.substring(0, destParam.lastIndexOf(File.separator)));
        }
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
    }

}
