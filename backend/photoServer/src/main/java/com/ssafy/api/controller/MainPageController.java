package com.ssafy.api.controller;

import com.ssafy.api.request.DetailReq;
import com.ssafy.api.response.*;
import com.ssafy.api.service.MainPageService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Api(value = "메인 페이지 api", tags = {"MainPage"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@CrossOrigin("*")
public class MainPageController {
    public static final Logger logger = LoggerFactory.getLogger(MainPageController.class);

    private final MainPageService mainPageService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;


    @GetMapping("/location")
    @ApiOperation(value = "지역 불러오기", notes = "저장된 지역들을 불러온다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Success", response = MainPageLocationRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "지역 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<MainPageLocationRes> locationList() {
        String[] locationList = mainPageService.locationList();
        return ResponseEntity.ok(MainPageLocationRes.of(200, "Success", locationList));
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
        return ResponseEntity.ok(MainPageTagPhotoRes.of(200, "Success", tagList));
    }

    @GetMapping("/contents")
    @ApiOperation(value = "메인콘텐츠 가져오기", notes = "사진태그 ,(썸네일, 닉네임)")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Success", response = MainPageTagPhotoRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사진 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<MainPageTagPhotoRes> mainPageContents() {
        String[] tagList = mainPageService.tagList();
        List<TagThumbNickNameRes> tagPhotoList = mainPageService.getMainContents();

        return ResponseEntity.ok(MainPageTagPhotoRes.of(200, "Success", tagPhotoList, tagList));
    }

    @PostMapping("/detail")
    @ApiOperation(value = "사진 디테일 콘텐츠 가져오기", notes = "(원본사진 경로, 원본사진 태그List, 찜 여부, [썸네일경로, 사진ID] N개")
    public ResponseEntity<MainPagePhotoDetailRes> photoDetail(@RequestBody @ApiParam(value = "사진작가 닉네임, 썸네일 경로, user id(로그인되어있으면", required = true) DetailReq detailReq) {
        String origin = mainPageService.photoOrigin(detailReq.getThumbnail());
        String[] tagList = mainPageService.photoTagList(detailReq.getThumbnail());
        Boolean isFavorite = mainPageService.isFavorite(detailReq.getNickName(), detailReq.getId());
        List<ThumbPhotoIdRes> thumbPhotoIds = mainPageService.thumbPhotoIds(detailReq.getNickName(), detailReq.getThumbnail());
        mainPageService.photoViewCnt(detailReq.getThumbnail());

        return ResponseEntity.ok(MainPagePhotoDetailRes.of(200, "Success", origin, tagList, isFavorite, thumbPhotoIds));
    }

    @PostMapping("/profile")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "JWT token", required = true, dataType = "string", paramType = "header")})
    @ApiOperation(value = "프로필 가져오기", notes = "프로필 사진, 닉네임을 가져온다")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Success", response = MainPageProfileRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<MainPageProfileRes> userProfile(HttpServletRequest req) {
        String JWT = req.getHeader("Authorization").split(" ")[1];
        if (userService.isValidToken(JWT)) {
            UserProfile userProfile = mainPageService.userProfile(JWT);
            return ResponseEntity.ok(MainPageProfileRes.of(200, "Success", userProfile));
        } else {
            return ResponseEntity.ok(MainPageProfileRes.of(401, "Invalid Token", null));
        }
    }

}
