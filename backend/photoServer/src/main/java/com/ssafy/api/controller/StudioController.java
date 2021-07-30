package com.ssafy.api.controller;

import com.ssafy.api.request.StudioEditCalendarReq;
import com.ssafy.api.request.StudioPgProfileReq;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(value = "Studio API")
@RestController
@RequestMapping("studio")
public class StudioController {

    // 작가 프로필 받아오기
    @GetMapping("pgprofile")
    @ApiOperation(value = "작가 프로필 받아오기",notes = "한줄프로필,지역을 받아온다.")
    @ApiResponses({
            @ApiResponse(code = 200,message = "조회 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> getPgProfile(@RequestBody @ApiParam(value = "닉네임 정보",required = true) StudioPgProfileReq pginfo) {
        String nickname = pginfo.getNickname();

        /* db조회 후 일치하는 닉네임이 있다면 프로필을 가져옴 */
        String dbsearch = "";

        if(true) {
            return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
        }

        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Failed"));
    }

    // 일정 보여주기
    @GetMapping("showcal")
    @ApiOperation(value = "일정 보여주기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "일정 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> showCalendar(@RequestBody @ApiParam(value = "닉네임 정보",required = true) StudioPgProfileReq pginfo){
        String nickname = pginfo.getNickname();

        /* 닉네임 조회 후, 마이스튜디오 idx 받아온 후 일정 리스트 받아옴 */
        Integer[] calendar = {};

        if(true) {
            return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
        }

        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Failed"));
    }

    // 일정 수정하기
    @PostMapping("editcal")
    @ApiOperation(value = "일정 수정하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "수정 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "수정 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "일정 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> editCalendar(@RequestBody @ApiParam(value = "JWT", required = true) StudioEditCalendarReq editcal){
        String nickname = editcal.getNickname();
        String JWT = editcal.getJWT();
        Date[] cal_time = editcal.getCal_time();

        /* 닉네임 조회 후, 마이스튜디오 idx 받아온 후 best사진 받아옴 */
        String dbsearch = "";

        if(true) {
            return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
        }

        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Failed"));
    }

    // 베스트3 사진 받아오기
    @GetMapping("bestphotos")
    @ApiOperation(value = "베스트3 사진 받아오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사진 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> getBestPhotos(@RequestBody @ApiParam(value = "닉네임 정보", required = true) StudioPgProfileReq pginfo){
        String nickname = pginfo.getNickname();

        /* 닉네임 조회 후, 마이스튜디오 idx 받아온 후 best사진 받아옴 */
        String dbsearch = "";

        if(true) {
            return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
        }

        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Failed"));
    }

    // 작가 전체사진 받아오기기
    @GetMapping("pgphoto")
    @ApiOperation(value = "작가 전체사진 받아오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사진 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> getAllPgPhotos(@RequestBody @ApiParam(value = "닉네임 정보", required = true) StudioPgProfileReq pginfo){
        String nickname = pginfo.getNickname();

        /* 닉네임 조회 후, 마이스튜디오 idx 받아온 후 작가 전체사진 받아옴 */
        String dbsearch = "";

        if(true) {
            return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
        }

        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Failed"));
    }
}
