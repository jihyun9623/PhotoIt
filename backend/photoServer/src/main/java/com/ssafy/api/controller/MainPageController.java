package com.ssafy.api.controller;

import com.ssafy.api.request.MainPageReq;
import com.ssafy.api.response.MainPageLocationRes;
import com.ssafy.api.response.MainPageProfileRes;
import com.ssafy.api.response.MainPageTagPhotoRes;
import com.ssafy.api.service.MainPageService;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;


@Api(value = "메인 페이지 api", tags = {"MainPage"})
@RestController
@RequestMapping("/")
public class MainPageController {
    public static final Logger logger = LoggerFactory.getLogger(MainPageController.class);

    @GetMapping("/location")
    @ApiOperation(value = "지역 불러오기", notes = "저장된 지역들을 불러온다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Success", response = MainPageLocationRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "지역 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<MainPageLocationRes> locationList () {
        String[] locationList = new String[2];
        return ResponseEntity.ok(MainPageLocationRes.of(200,"Success", locationList));

    }

    @GetMapping("/tag")
    @ApiOperation(value = "사진 태그 가져오기", notes = "사진 태그들을 가져온다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Success", response = MainPageTagPhotoRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "태그 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<MainPageTagPhotoRes> tagList() {
        String[] tagList = new String[2];
        return ResponseEntity.ok(MainPageTagPhotoRes.of(200,"Success", tagList));
    }

    @GetMapping("/contents")
    @ApiOperation(value = "메인콘텐츠 가져오기", notes = "사진 태그별 사진, 사진작가 닉네임들을 가져온다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Success", response = MainPageTagPhotoRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사진 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<MainPageTagPhotoRes> mainPageContents() {
        String[] tagList = new String[2];
        Map<String, Map<String, File>[]> tagPhotoList = new TreeMap<>();
        return ResponseEntity.ok(MainPageTagPhotoRes.of(200,"Success", tagPhotoList, tagList));
    }

    @GetMapping("/profile")
    @ApiOperation(value = "프로필 가져오기", notes = "프로필 사진, 닉네임을 가져온다")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Success", response = MainPageProfileRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<MainPageProfileRes> userProfile(@RequestBody @ApiParam(value="JWT, user Id", required = true) MainPageReq mainReq) {
        String jwt = mainReq.getJWT();
        String nickName = "";
        File profile = null;
        return ResponseEntity.ok(MainPageProfileRes.of(200,"Success", profile, nickName));
    }

}
