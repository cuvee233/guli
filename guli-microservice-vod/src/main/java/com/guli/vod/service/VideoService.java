package com.guli.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author weiyi
 * @describe
 * @since 2019/8/12 - 9:20
 */
public interface VideoService {

    /**
     * 上传阿里云视频
     * @param file
     * @return
     */
    String uploadVideo(MultipartFile file);

}
