package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.*;
import com.guli.edu.form.CourseInfoForm;
import com.guli.edu.mapper.ChapterMapper;
import com.guli.edu.mapper.CourseDescriptionMapper;
import com.guli.edu.mapper.CourseMapper;
import com.guli.edu.mapper.VideoMapper;
import com.guli.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.vo.CoursePublishVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.plugin.dom.core.CoreConstants;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-08-02
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private ChapterMapper chapterMapper;


    @Transactional
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {

        // 保存课程基本信息
        Course course = new Course();
        course.setStatus(Course.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoForm, course);
        baseMapper.insert(course);

        // 保存课程详情信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionMapper.insert(courseDescription);

        return course.getId();
    }

    @Override
    public CourseInfoForm getCourseInfoFormById(String id) {

        //从course表中取数据
        Course course = baseMapper.selectById(id);

        //从course_description表中取数据
        CourseDescription courseDescription = courseDescriptionMapper.selectById(id);

        //创建courseInfoForm对象
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course, courseInfoForm);
        BeanUtils.copyProperties(courseDescription, courseInfoForm);

        return courseInfoForm;
    }

    @Override
    public void updateCourseInfoById(CourseInfoForm courseInfoForm) {

        // String id = courseInfo|Form.getId();
        Course course = new Course();
        // 封装Course对象根据id更新
        BeanUtils.copyProperties(courseInfoForm, course);
        baseMapper.updateById(course);

        // 封装CourseDescription对象根据id更新
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionMapper.updateById(courseDescription);
    }

    @Override
    public void pageQuery(Page<Course> pageParam, CourseQuery courseQuery) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");

        if (courseQuery == null) {
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(teacherId)) {
            queryWrapper.eq("teacher_id", teacherId);
        }

        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.ge("subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.ge("subject_id", subjectId);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public void removeCourseById(String id) {

        //根据id删除所有视频
        QueryWrapper<Video> queryWrapperVideo = new QueryWrapper<>();
        queryWrapperVideo.eq("course_id", id);
        videoMapper.delete(queryWrapperVideo);

        //根据id删除所有章节
        QueryWrapper<Chapter> queryWrapperChapter = new QueryWrapper<>();
        queryWrapperChapter.eq("course_id", id);
        chapterMapper.delete(queryWrapperChapter);

        //删除课程详情
        courseDescriptionMapper.deleteById(id);

        //根据id删除课程
        baseMapper.deleteById(id);

    }

    /**
     * 根据id获取课程信息
     * @param id
     * @return
     */
    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        return baseMapper.selectCoursePublishVoById(id);
    }

    /**
     * 根据课程id发布课程
     * @param id
     */
    @Override
    public void publishCourseById(String id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(Course.COURSE_NORMAL);
        baseMapper.updateById(course);
    }
}
