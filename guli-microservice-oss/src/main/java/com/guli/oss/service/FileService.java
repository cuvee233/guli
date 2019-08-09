package com.guli.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author weiyi
 * @describe
 * @since 2019/8/7 - 8:39
 */
public interface FileService {

    /**
     * 文件上传
     * @return
     */
    public String uploadFile(MultipartFile file, String fileHost) throws IOException;

}
