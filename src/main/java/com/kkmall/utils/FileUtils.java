package com.kkmall.utils;

import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileUtils {
    public static String convertMultipartFileToBase64String(MultipartFile file) throws IOException {
        // 检查文件是否为空
        if (file.isEmpty()) {
            return "";
        }

        // 将文件内容进行 Base64 编码
        byte[] fileBytes = file.getBytes();
        return Base64Utils.encodeToString(fileBytes);
    }
}
