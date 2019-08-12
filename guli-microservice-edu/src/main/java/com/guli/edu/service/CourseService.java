package com.guli.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.entity.CourseQuery;
import com.guli.edu.form.CourseInfoForm;
import com.guli.edu.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Helen
 * @since 2019-08-02
 */
public interface CourseService extends IService<Course> {

    /**
     * 保存课程和课程详情信息
     * @param courseInfoForm
     * @return 新生成的课程id
     */
    String saveCourseInfo(CourseInfoForm courseInfoForm);

    /**
     * 根据id获取课程信息
     * @param id
     * @return
     */
    CourseInfoForm getCourseInfoFormById(String id);

    /**
     * 根据id更新课程信息
     * @param courseInfoForm
     */
    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    /**
     * 显示带分页功能的课程列表
     * @param pageParam
     * @param courseQuery
     */
    void pageQuery(Page<Course> pageParam, CourseQuery courseQuery);

    /**
     * 根据id删除课程信息
     * @param id
     */
    void removeCourseById(String id);

    CoursePublishVo getCoursePublishVoById(String id);

    void publishCourseById(String id);
}
