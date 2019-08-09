package com.guli.oss.service.serviceImpl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.guli.oss.constants.OssConstantsUtil;
import com.guli.oss.service.FileService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.UUID;

/**
 * @author weiyi
 * @describe
 * @since 2019/8/7 - 8:40
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(MultipartFile multipartFile, String fileHost) throws IOException {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = OssConstantsUtil.END_POINT;
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = OssConstantsUtil.ACCESS_KEY_ID;
        String accessKeySecret = OssConstantsUtil.ACCESS_KEY_SECRET;
        String bucketName = OssConstantsUtil.BUCKET_NAME;

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName); // 如果bucketName不存在就新建一个bucket
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead); // 设置访问权限
        }

        String originalFilename = multipartFile.getOriginalFilename();

        // 文件上传路径
        String filePath = new DateTime().toString("yyyy/MM/dd");
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 文件名
        String fileName = UUID.randomUUID().toString() + fileType;
        // 文件上传的路径
        String fileUrl =  fileHost + "/" + filePath + "/" + fileName;

        // 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。

        ossClient.putObject(OssConstantsUtil.BUCKET_NAME, fileUrl, multipartFile.getInputStream());

        //关闭OSSClient。
        ossClient.shutdown();

        String uploadPath = "http://" + bucketName + "." + endpoint + "/" + fileUrl;

        return uploadPath;
    }
}
