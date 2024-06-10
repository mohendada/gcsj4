package com.example.gcsj4supermarket.sys.controller;

import com.example.gcsj4supermarket.common.util.AliOssUtil;
import com.example.gcsj4supermarket.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Slf4j
public class fileController {

    @Autowired
    private AliOssUtil aliOssUtil;

    @RequestMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        log.info("上传文件:{}", file);

        try {

            String originalFilename = file.getOriginalFilename();

            String extension = null;

            if (originalFilename != null) {
                extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
            }

            String objectName = UUID.randomUUID().toString() + extension;

            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e);
        }
        return null;
    }
}