package com.guli.edu.service;

import com.guli.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.form.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Helen
 * @since 2019-08-02
 */
public interface VideoService extends IService<Video> {

    // 保存视频
    void saveVideoInfo(VideoInfoForm videoInfoForm);

    void removeVideoById(String id);

    void updateVideoInfoById(VideoInfoForm videoInfoForm);

    VideoInfoForm getVideoInfoFormById(String id);
}
