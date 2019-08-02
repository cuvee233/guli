package com.guli.edu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.constants.ResultMessage;
import com.guli.common.vo.R;
import com.guli.edu.entity.Teacher;
import com.guli.edu.service.TeacherService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public R teacherList() {
        List<Teacher> list = teacherService.list(null);
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (list != null && list.size() != 0) {
            return R.ok().data("teacherList", list);
        }
        return R.error().message(ResultMessage.NO_DATA_QUERIED);
    }

    @DeleteMapping("{id}")
    public R removeById(@PathVariable("id")String id) {
        boolean b = teacherService.removeById(id);
        if (b) {
            return R.ok().message(ResultMessage.DELETE_SECCESS);
        } else {
            return R.error().message(ResultMessage.DELETE_FAILED);
        }
    }

    @GetMapping("{page}/{limit}")
    public R pageList(
                    @ApiParam(name = "page", value = "pageNo")
                    @PathVariable("page")Long page,
                    @ApiParam(name = "limit", value = "size")
                    @PathVariable("limit")Long limit) {

        Page<Teacher> pageParam = new Page<>(page, limit);
        IPage<Teacher> teacherIPage = teacherService.page(pageParam, null);
        List<Teacher> records = teacherIPage.getRecords();
        long total = teacherIPage.getTotal();
        log.debug(records.toString(), total);
        if (records != null && records.size() != 0) {
            return R.ok().data("rows", records).data("total", total);
        } else {
            return R.error().message(ResultMessage.NO_DATA_QUERIED);
        }

    }
}

