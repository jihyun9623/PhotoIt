package com.ssafy.api.controller;

import com.ssafy.api.request.MainPageReq;
import com.ssafy.api.response.MainPageLocationRes;
import com.ssafy.api.response.MainPageProfileRes;
import com.ssafy.api.response.MainPageTagPhotoRes;
import com.ssafy.api.service.MainPageService;
import com.ssafy.api.service.MainPageServiceImpl;
//import com.ssafy.api.service.UserServiceImpl;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.LocationRepository;
import com.ssafy.db.repository.TagRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/")
public class MainPageController {
    public static final Logger logger = LoggerFactory.getLogger(MainPageController.class);

    private final MainPageService mainPageService;

    @GetMapping("/location")
    @ApiOperation(value = "지역 불러오기", notes = "저장된 지역들을 불러온다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Success", response = MainPageLocationRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "지역 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<MainPageLocationRes> locationList () {
        String[] locationList = mainPageService.locationList();
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
        String[] tagList = mainPageService.tagList();
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
        String[] tagList = mainPageService.tagList();
        Map<String, Map<String, String>> tagPhotoList = mainPageService.getMainContents();

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
        String nickName = mainPageService.getUser(mainReq.getJWT(), mainReq.getId()).getNickname();
        String profile = mainPageService.getUser(mainReq.getJWT(), mainReq.getId()).getPhoto();
        return ResponseEntity.ok(MainPageProfileRes.of(200,"Success", profile, nickName));
    }

}
