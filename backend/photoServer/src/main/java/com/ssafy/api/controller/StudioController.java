/* 작성자 : 김지현 */

package com.ssafy.api.controller;

import com.ssafy.api.request.StudioCalendarReq;
import com.ssafy.api.response.StudioCalendarResBody;
import com.ssafy.api.response.StudioGetPhotosResBody;
import com.ssafy.api.response.StudioPgProfileResBody;
import com.ssafy.api.service.StudioService;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Api(value = "Studio API")
@RestController
@RequestMapping("/studio")
public class StudioController {

    @Autowired
    StudioService studioService;

    // 작가 프로필 받아오기
    @GetMapping("/pgprofile/{nickname}")
    @ApiOperation(value = "작가 프로필 받아오기",notes = "한줄프로필,지역을 받아온다.")
    @ApiResponses({
            @ApiResponse(code = 201,message = "조회 성공", response = StudioPgProfileResBody.class),
            @ApiResponse(code = 401, message = "조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<StudioPgProfileResBody> getPgProfile(@RequestBody @PathVariable("nickname") String nickname) {
        /* db조회 후 일치하는 닉네임이 있다면 스튜디오 idx를 이용해 프로필을 가져옴 */
        StudioPgProfileResBody pgProfResBody = studioService.getPgProfile(nickname);

        if(pgProfResBody!=null) {
            return ResponseEntity.ok(StudioPgProfileResBody.of(200, "Success", pgProfResBody.getIntroduce(), pgProfResBody.getLocation(), pgProfResBody.getProfPhoto()));
        }

        return ResponseEntity.status(401).body(StudioPgProfileResBody.of(401, "작가가 아닙니다.",null,null,null));
    }

    // 일정 보여주기
    @GetMapping("/showcal/{nickname}")
    @ApiOperation(value = "일정 보여주기")
    @ApiResponses({
            @ApiResponse(code = 201, message = "조회 성공", response = StudioCalendarResBody.class),
            @ApiResponse(code = 401, message = "조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "일정 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<StudioCalendarResBody> showCalendar(@RequestBody @PathVariable("nickname") String nickname){
        /* 닉네임 조회 후, 마이스튜디오 idx 받아온 후 일정 리스트 받아옴 */
        String[] cal = studioService.showCalendar(nickname);

        if(cal!=null) return ResponseEntity.ok(StudioCalendarResBody.of(200, "Success",cal));

        return ResponseEntity.ok(StudioCalendarResBody.of(401, "일정이 없습니다.",null));
    }

    // 일정 추가하기 //프론트에서 중복추가 안막아줄거면 백에서 해야함!
    @PostMapping("/addcal")
    @ApiOperation(value = "일정 추가하기")
    @ApiResponses({
            @ApiResponse(code = 201, message = "추가 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "추가 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "일정 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> addCalendar(@RequestBody @ApiParam(value = "JWT", required = true) StudioCalendarReq editcal){
        String nickname = editcal.getNickname();
        String JWT = editcal.getJWT();
        String[] cal_time = editcal.getCal_time();

        /* 닉네임 조회 -> JWT로 본인 확인 -> 마이스튜디오 idx 받아옴 -> 일정 추가 */
        boolean resbody = studioService.addCalendar(nickname,JWT,cal_time);

        if(resbody) {
            return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
        }

        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Failed"));
    }

    // 일정 삭제하기
    @PostMapping("/deletecal")
    @ApiOperation(value = "일정 삭제하기")
    @ApiResponses({
            @ApiResponse(code = 201, message = "삭제 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "삭제 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "일정 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> deleteCalendar(@RequestBody @ApiParam(value = "JWT", required = true) StudioCalendarReq editcal){
        String nickname = editcal.getNickname();
        String JWT = editcal.getJWT();
        String[] cal_time = editcal.getCal_time();

        /* 닉네임 조회 -> JWT로 본인 확인 -> 마이스튜디오 idx 받아옴 -> 일정 삭제 */
        boolean resbody = studioService.deleteCalendar(nickname,JWT,cal_time);

        if(resbody) {
            return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
        }

        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Failed"));
    }

    // 베스트3 사진 받아오기
    @GetMapping("/bestphotos/{nickname}")
    @ApiOperation(value = "베스트3 사진 받아오기")
    @ApiResponses({
            @ApiResponse(code = 201, message = "조회 성공", response = StudioGetPhotosResBody.class),
            @ApiResponse(code = 401, message = "조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사진 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<StudioGetPhotosResBody> getBestPhotos(@RequestBody @PathVariable("nickname") String nickname){
        /* 닉네임 조회 후, 마이스튜디오 idx 받아온 후 best사진 받아옴 */
        StudioGetPhotosResBody studioGetPhotosResBody = studioService.getBestPhotos(nickname);

        if(studioGetPhotosResBody!=null) {
            return ResponseEntity.ok(StudioGetPhotosResBody.of(200, "Success",studioGetPhotosResBody.getId(),studioGetPhotosResBody.getFile()));
        }

        return ResponseEntity.status(401).body(StudioGetPhotosResBody.of(401, "베스트 사진이 없습니다.",null,null));
    }

    // 작가 전체사진 받아오기기
    @GetMapping("/pgphoto/{nickname}")
    @ApiOperation(value = "작가 전체사진 받아오기")
    @ApiResponses({
            @ApiResponse(code = 201, message = "조회 성공", response = StudioGetPhotosResBody.class),
            @ApiResponse(code = 401, message = "조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사진 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<StudioGetPhotosResBody> getAllPgPhotos(@RequestBody @PathVariable("nickname") String nickname){
        /* 닉네임 조회 후, 마이스튜디오 idx 받아온 후 작가 전체사진 받아옴 */
        StudioGetPhotosResBody studioGetPhotosResBody = studioService.getAllPgPhotos(nickname);

        if(studioGetPhotosResBody!=null) {
            return ResponseEntity.ok(StudioGetPhotosResBody.of(200, "Success",studioGetPhotosResBody.getId(),studioGetPhotosResBody.getFile()));
        }

        return ResponseEntity.status(401).body(StudioGetPhotosResBody.of(401, "사진이 없습니다.",null,null));
    }
}
