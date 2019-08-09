package com.guli.oss.controller;

import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.common.vo.R;
import com.guli.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author weiyi
 * @describe
 * @since 2019/8/7 - 11:05
 */
@Api("阿里云oss文件管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/oss/file")
public class FileUploadController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     * @param file
     * @param fileHost
     * @return
     * @throws FileNotFoundException
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public R fileUpload(
            @ApiParam(name = "file", value = "文件")
            @RequestParam("")
                    MultipartFile file,
            @ApiParam(name = "fileHost", value = "上传路径")
            @RequestParam("fileHost")
                    String fileHost) throws IOException {

        try {
            String uploadPath = fileService.uploadFile(file, fileHost);
            if (!StringUtils.isEmpty(uploadPath)) {
                return R.setResult(ResultCodeEnum.FILE_UPLOAD_SUCCESS).data("files", uploadPath);
            } else {
                return R.setResult(ResultCodeEnum.FILE_UPLOAD_FAILED);
            }
        } catch (Exception e) {
           throw new GuliException(ResultCodeEnum.FILE_UPLOAD_FAILED);
        }

    }


}
