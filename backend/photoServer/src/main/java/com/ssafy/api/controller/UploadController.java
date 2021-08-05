package com.ssafy.api.controller;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UploadController {

    private final Uploader uploader;

    @PostMapping("photo-it/upload")
    public String upload(@RequestParam("data") MultipartFile file) throws IOException {
        log.info("-------------------" + file.toString());
        return uploader.uploadS3Instance(file, "static");
    }

    @PostMapping("photo-it/delete")
    public ResponseEntity<BaseResponseBody> delete(@RequestParam("url") String url) {
        try {
            uploader.deleteS3Instance(url);
            return ResponseEntity.ok(BaseResponseBody.of(200, "파일 삭제 완료"));
        }catch(Exception e) {
            return ResponseEntity.ok(BaseResponseBody.of(400, "파일을 찾을 수 없습니다"));
        }
        
    }
}
