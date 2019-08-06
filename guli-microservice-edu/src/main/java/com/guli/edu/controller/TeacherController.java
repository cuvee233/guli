package com.guli.edu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.constants.ResultMessage;
import com.guli.common.vo.R;
import com.guli.edu.entity.Teacher;
import com.guli.edu.query.TeacherQuery;
import com.guli.edu.service.TeacherQueryService;
import com.guli.edu.service.TeacherService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2019-08-02
 */
@Slf4j
@RestController
@RequestMapping("/admin/edu/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherQueryService teacherQueryService;

    /**
     * 查询所有讲师列表
     *
     * @return
     */
    @ApiOperation("查询所有讲师")
    @GetMapping
    public R teacherList() {
        List<Teacher> list = teacherService.list(null);
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (list != null && list.size() != 0) {
            return R.ok().data("teacherList", list);
        }
        return R.error().message(ResultMessage.NO_DATA_QUERIED);
    }

    /**
     * 根据id删除讲师
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id删除讲师")
    @DeleteMapping("{id}")
    public R removeById(@PathVariable("id") String id) {
        boolean b = teacherService.removeById(id);
        if (b) {
            return R.ok().message(ResultMessage.DELETE_SECCESS);
        } else {
            return R.error().message(ResultMessage.DELETE_FAILED);
        }
    }

    /**
     * 分页查询
     *
     * @param page
     * @param limit
     * @param teacherQuery
     * @return
     */
    @ApiOperation("根据条件分页查询")
    @GetMapping("{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "pageNo")
            @PathVariable("page") Long page,
            @ApiParam(name = "limit", value = "size")
            @PathVariable("limit") Long limit,
            @ApiParam(name = "查询对象", value = "teacherQuery", required = false)
                    TeacherQuery teacherQuery) {

        Page<Teacher> pageParam = new Page<>(page, limit);
        teacherService.page(pageParam, null);
        teacherQueryService.pageQuery(pageParam, teacherQuery);

        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        log.debug(records.toString(), total);
        if (records != null && records.size() != 0) {
            return R.ok().data("rows", records).data("total", total);
        } else {
            return R.error().message(ResultMessage.NO_DATA_QUERIED);
        }
    }

    /**
     * 新增讲师
     *
     * @param teacher
     * @return
     */
    @ApiOperation(value = "新增讲师")
    @PutMapping
    public R save(
            @ApiParam(name = "讲师对象", value = "teacher")
            @RequestBody Teacher teacher) {
        boolean b = teacherService.save(teacher);
        if (b) {
            return R.ok().message(ResultMessage.SAVE_SUCCESS);
        } else {
            return R.error().message(ResultMessage.SAVE_FAILED);
        }
    }

    /**
     * 根据id修改讲师信息
     *
     * @param teacher
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID修改讲师")
    @GetMapping("{id}")
    public R update(@ApiParam(name = "讲师对象", value = "teacher")
                    @RequestBody Teacher teacher,
                    @ApiParam(name = "讲师id", value = "id")
                    @PathVariable("id") String id) {
        teacher.setId(id);
        boolean b = teacherService.updateById(teacher);
        if (b) {
            return R.ok().message(ResultMessage.UPDATE_SUCCESS);
        } else {
            return R.error().message(ResultMessage.UPDATE_FAILED);
        }
    }

}

