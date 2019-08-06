package com.guli.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.Teacher;
import com.guli.edu.query.TeacherQuery;

/**
 * @author weiyi
 * @describe
 * @since 2019/8/2 - 21:06
 */
public interface TeacherQueryService {

    /**
     * 根据条件分页查询
     * @param pageParam
     * @param teacherQuery
     */
    void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery);
}
