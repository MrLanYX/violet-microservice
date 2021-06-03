package com.brh.utils;

import cn.hutool.core.util.ZipUtil;
import net.lingala.zip4j.exception.ZipException;
import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * RAR格式压缩文件解压工具类
 * 不支持RAR格式压缩
 * 支持中文,支持RAR压缩文件密码
 * 依赖jar包
 * commons-io.jar
 * commons-logging.jar
 * java-unrar-decryption-supported.jar
 * gnu-crypto.jar
 *
 * @author ninemax
 */

public class FileUtil {

    public static void main(String[] args) {
        String sourcePath = "F:\\文档管理\\新建文件夹\\新建文件夹.rar";

        if(isCompress(sourcePath)){

        }
        if(isPicture(sourcePath)){

        }
        realExtract(new File("F:\\文档管理\\新建文件夹\\新建文件夹.rar"), "F:\\文档管理\\新建文件夹", null);

    }

    public static final String CMD_PATH = "E:\\WinRAR\\WinRAR.exe";

    /**
     * 采用命令行方式解压RAR文件
     *
     * @param zipFile  压缩文件
     * @param destDir  解压结果路径
     * @param password 密码
     */
    public static void realExtract(File zipFile, String destDir, String password) {
        File fd = new File(destDir);
        if (!fd.exists()) {
            fd.mkdirs();
        }
        // 解决路径中存在/..格式的路径问题
        destDir = new File(destDir).getAbsoluteFile().getAbsolutePath();
        while (destDir.contains("..")) {
            String[] sepList = destDir.split("\\\\");
            StringBuilder destDirBuilder = new StringBuilder();
            for (int i = 0; i < sepList.length; i++) {
                if (!"..".equals(sepList[i]) && i < sepList.length - 1 && "..".equals(sepList[i + 1])) {
                    i++;
                } else {
                    destDirBuilder.append(sepList[i]).append(File.separator);
                }
            }
            destDir = destDirBuilder.toString();
        }
        if (!zipFile.exists()) {
            return;
        }
        // 开始调用命令行解压，参数-o+是表示覆盖的意思
        String cmd = CMD_PATH + " X -p" + password + " " + zipFile + " " + destDir;
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
//            if (proc.waitFor() != 0) {
//                if (proc.exitValue() == 0) {
//                    bool = false;
//                }
//            } else {
//                bool = true;
//            }
            System.out.println("解压" + (proc.exitValue() == 0 ? "成功" : "失败"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 采用命令行方式解压RAR文件
     *
     * @param zipFile 压缩文件
     * @param destDir 解压结果路径
     */
    public static void realExtract(File zipFile, String destDir) {
        File fd = new File(destDir);
        if (!fd.exists()) {
            fd.mkdirs();
        }
        // 解决路径中存在/..格式的路径问题
        destDir = new File(destDir).getAbsoluteFile().getAbsolutePath();
        while (destDir.contains("..")) {
            String[] sepList = destDir.split("\\\\");
            StringBuilder destDirBuilder = new StringBuilder();
            for (int i = 0; i < sepList.length; i++) {
                if (!"..".equals(sepList[i]) && i < sepList.length - 1 && "..".equals(sepList[i + 1])) {
                    i++;
                } else {
                    destDirBuilder.append(sepList[i]).append(File.separator);
                }
            }
            destDir = destDirBuilder.toString();
        }
        if (!zipFile.exists()) {
            return;
        }
        // 开始调用命令行解压，参数-o+是表示覆盖的意思
        String cmd = CMD_PATH + " X -o+ " + zipFile + " " + destDir;
        System.out.println(cmd);
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
//            if (proc.waitFor() != 0) {
//                if (proc.exitValue() == 0) {
//                    bool = false;
//                }
//            } else {
//                bool = true;
//            }
            System.out.println("解压" + (proc.exitValue() == 0 ? "成功" : "失败"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 7z文件压缩
     *
     * @param inputFile  待压缩文件夹/文件名
     * @param outputFile 生成的压缩包名字
     */
    public static void compress7z(String inputFile, String outputFile) throws Exception {
        File input = new File(inputFile);
        if (!input.exists()) {
            throw new Exception(input.getPath() + "待压缩文件不存在");
        }
        SevenZOutputFile out = new SevenZOutputFile(new File(outputFile));
        compress(out, input, null);
        out.close();
    }

    /**
     * 递归压缩
     * @param out 压缩输出
     * @param input 压缩目标
     * @param name 压缩文件名，可以写为null保持默认
     */
    public static void compress(SevenZOutputFile out, File input, String name) throws IOException {
        if (name == null) {
            name = input.getName();
        }
        SevenZArchiveEntry entry = null;
        //如果路径为目录（文件夹）
        if (input.isDirectory()) {
            //取出文件夹中的文件（或子文件夹）
            File[] fList = input.listFiles();
            assert fList != null;
            //如果文件夹为空，则只需在目的地.7z文件中写入一个目录进入
            if (fList.length == 0)
            {
                entry = out.createArchiveEntry(input, name + "/");
                out.putArchiveEntry(entry);
            } else//如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
            {
                for (File file : fList) {
                    compress(out, file, name + "/" + file.getName());
                }
            }
        } else//如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入7z文件中
        {
            FileInputStream fos = new FileInputStream(input);
            BufferedInputStream bis = new BufferedInputStream(fos);
            entry = out.createArchiveEntry(input, name);
            out.putArchiveEntry(entry);
            int len;
            //将源文件写入到7z文件中
            byte[] buf = new byte[1024];
            while ((len = bis.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            bis.close();
            fos.close();
            out.closeArchiveEntry();
        }
    }
    /**
     * zip解压
     * @param inputFile 待解压文件名
     * @param destDirPath  解压路径
     */
    public static void uncompress(String inputFile, String destDirPath) throws Exception {
        //获取当前压缩文件
        File srcFile = new File(inputFile);
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new Exception(srcFile.getPath() + "所指文件不存在");
        }
        //开始解压
        SevenZFile zIn = new SevenZFile(srcFile);
        SevenZArchiveEntry entry;
        File file;
        while ((entry = zIn.getNextEntry()) != null) {
            if (!entry.isDirectory()) {
                file = new File(destDirPath, entry.getName());
                if (!file.exists()) {
                    //创建此文件的上级目录
                    new File(file.getParent()).mkdirs();
                }
                OutputStream out = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(out);
                int len = -1;
                byte[] buf = new byte[1024];
                while ((len = zIn.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
                // 关流顺序，先打开的后关闭
                bos.close();
                out.close();
            }
        }
    }

    /**
     * @param file7zPath(7z文件路径)
     * @param outPutPath(解压路径)
     * @param passWord(文件密码.没有可随便写,或空)
     * @return
     * @throws Exception
     * @Description (解压7z)
     */
    public static int un7z(String file7zPath, final String outPutPath, String passWord) throws Exception {
        IInArchive archive;
        RandomAccessFile randomAccessFile;
        randomAccessFile = new RandomAccessFile(file7zPath, "r");
        archive = SevenZip.openInArchive(null, new RandomAccessFileInStream(randomAccessFile), passWord);
        int numberOfItems = archive.getNumberOfItems();
        ISimpleInArchive simpleInArchive = archive.getSimpleInterface();
        String unFileName = null;
        for (final ISimpleInArchiveItem item : simpleInArchive.getArchiveItems()) {
            final int[] hash = new int[]{0};
            if (!item.isFolder()) {
                ExtractOperationResult result;
                final long[] sizeArray = new long[1];
                File file = new File(outPutPath + File.separator + item.getPath());
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                unFileName = file.getAbsolutePath();
                result = item.extractSlow(data -> {
                    try {
                        IOUtils.write(data, fileOutputStream);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    hash[0] ^= Arrays.hashCode(data); // Consume data
                    sizeArray[0] += data.length;
                    return data.length; // Return amount of consumed
                }, passWord);
                if (result == ExtractOperationResult.OK) {
                    System.out.println("解压成功...." + String.format("%9X | %10s | %s", hash[0], sizeArray[0], item.getPath()));
//                    logger.error();
// LogUtil.getLog().debug(String.format("%9X | %10s | %s", hash[0], sizeArray[0], item.getPath()));
                } else {
//                    logger.error("解压失败：密码错误或者其他错误...." +result);
// LogUtil.getLog().debug("Error extracting item: " + result);
                }
            }
        }
        archive.close();
        randomAccessFile.close();
        return numberOfItems;
    }

    /**
     * @param sourcePath 源路径
     * @param outPath    输出路径
     * @param password   密码
     * @return File[]
     * @Description //TODO  解压加密ZIP，读取data.json文件
     * @Date 14:04 2020/8/24
     **/
    public File[] unZip(String sourcePath, String outPath, String password) throws ZipException {
        //读取加密zip
        File file = new File(sourcePath);
        //解压加密zip
        return ZipUtils.unzip(file, outPath, password);
    }

    /**
     * @param sourcePath 源文件
     * @param outPath    目的地址
     * @return File
     */
    public static File unzip(String sourcePath, String outPath) {
        return ZipUtil.unzip(sourcePath, outPath);
    }

    private List<String> sourcePath;

    /**
     * 初始化sourcePath
     */
    private void init() {
        this.sourcePath = new ArrayList<>();
    }

    /**
     * 销毁sourcePath
     */
    private void destroy() {
        this.sourcePath = null;
    }

    /**
     * 获取目标的所有文件路径
     *
     * @param sourcePath 目标路径
     */
    public void getFile(String sourcePath) {
        init();
        File file = new File(sourcePath);
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println(file.getAbsolutePath());
                File[] list = file.listFiles();
                assert list != null;
                for (File file1 : list) {
                    getFile(file1.getAbsolutePath());
                }
            } else {
                this.sourcePath.add(file.getAbsolutePath());
                System.out.println(file.getAbsolutePath());
            }
        }
    }

    /**
     * 判断是否是
     *
     * @param sourcePath 源路径
     * @return boolean
     */
    public static boolean isCompress(String sourcePath) {

        return sourcePath.toLowerCase().endsWith(".rar") || sourcePath.toLowerCase().endsWith("zip") || sourcePath.toLowerCase().endsWith("7z");
    }

    /**
     * 判断是否是图片
     *
     * @param sourcePath 源路径
     * @return boolean
     */
    public static boolean isPicture(String sourcePath) {
        return sourcePath.toLowerCase().endsWith(".png") || sourcePath.toLowerCase().endsWith("jpg") || sourcePath.toLowerCase().endsWith("jpeg");
    }

    /** 7z
     * 不含加密，普通解压
     **/

    public static void unZFile(String inFileName, String outFileName) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(inFileName);
//            inputStream = new UncompressInputStream(inputStream);
            File file = new File(outFileName);
            outputStream = new FileOutputStream(file);
            int bytesRead;
            byte[] buffer = new byte[100000];
            while ((bytesRead = inputStream.read(buffer, 0, 100000)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            logger.error("unZFile Exception " + e.getMessage());
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
//                    logger.error("outputStream Close Exception " + e.getMessage());
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
//                    logger.error("inputStream Close Exception "+ e.getMessage());
                }
            }
        }
    }

}


