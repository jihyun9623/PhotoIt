/* 2021-07-27 스켈레톤 코드 작성	by.HeeJung,Lee */

package com.ssafy.api.controller;

import com.ssafy.api.request.StudioEditPhotoSelectReq;
import com.ssafy.api.request.StudioEditPhotoUploadReq;
import com.ssafy.api.response.StudioEditPhotoResponseBody;
import com.ssafy.api.response.StudioEditPgProfileResponseBody;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Api(value = "스튜디오 Edit API", tags = {"edit."})
@RestController
@RequestMapping("/studioedit")
public class StudioEditController {

    @Autowired
    StudioEditService studioEditService;

    @Autowired
    UserService userService;

    // 본인확인 인증
    @GetMapping("/studioauth/{nickname}")
    @ApiOperation(value = "마이스튜디오 본인확인", notes = "<strong>닉네임</strong>을 통해 확인한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인증 확인", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> studioAuth(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("nickname") String nickname) {
        String jwt = token;

        // 현재 JWT의 nickname과 가져온 닉네임 비고
        boolean result = studioEditService.studioAuth(jwt, nickname);

        if (result) {
            return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
        } else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Not Matched"));

    }

    // 프로필 받아오기
    @GetMapping("/pgprofile")
    @ApiOperation(value = "작가 프로필 받아오기", notes = "작가 프로필을 받아온다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인증 확인", response = StudioEditPgProfileResponseBody.class),
            @ApiResponse(code = 401, message = "작가 아님", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<StudioEditPgProfileResponseBody> getPgProfile(@RequestHeader(value = "Authorization") String token) {
        String jwt = token;

        // JWT -> PhotoGrapher Profile
        StudioEditPgProfileResponseBody responseBody = studioEditService.getPgProfile(jwt);

        if (responseBody != null) {
            return ResponseEntity.ok(StudioEditPgProfileResponseBody.of(200, "Success", responseBody.getIntroduce(), responseBody.getLocation()));
        }
        return ResponseEntity.status(401).body(StudioEditPgProfileResponseBody.of(401, "Not Photographer", null, null));
    }

    // 베스트 사진 받아오기
    @GetMapping("/bestphoto")
    @ApiOperation(value = "베스트3 사진 받아오기", notes = "best3사진을 원본사진과 사진ID를 받아온")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인증 확인", response = StudioEditPhotoResponseBody.class),
            @ApiResponse(code = 401, message = "베스트 사진 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<StudioEditPhotoResponseBody> getBestPhoto( @RequestHeader(value = "Authorization") String token) {
        String jwt = token;

       /* 닉네임을 통한 JWT 확인 후 현재 들어온 JWT와 비교하여 확인 후 베스트사진 1~3개를 불러옴*/
        StudioEditPhotoResponseBody responseBody = studioEditService.getBestPhoto(jwt);

        if (responseBody != null) {
            return ResponseEntity.ok(StudioEditPhotoResponseBody.of(200, "Success", responseBody.getId(), responseBody.getFiles()));
        }
        // 베스트 사진 0개
        return ResponseEntity.status(401).body(StudioEditPhotoResponseBody.of(401, "No Best Photos", null, null));

    }

    // 전체사진 받아오기
    @GetMapping("/photo")
    @ApiOperation(value = "작가 전체 사진 받아오기", notes = "작가 전체사진을 섬네일사진과 사진ID, 닉네임을 받아온다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인증 확인", response = StudioEditPhotoResponseBody.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사진 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<StudioEditPhotoResponseBody> getPgPhoto( @RequestHeader(value = "Authorization") String token) {
        String jwt = token;

       /* 닉네임을 통한 JWT 확인 후 현재 들어온 JWT와 비교하여 확인 후 전체사진(섬네일) 모두를 불러옴*/
        StudioEditPhotoResponseBody responseBody = studioEditService.getPgPhoto(jwt);

        if (responseBody != null) {
            return ResponseEntity.ok(StudioEditPhotoResponseBody.of(200, "Success", responseBody.getId(), responseBody.getFiles()));
        }
        return ResponseEntity.status(401).body(StudioEditPhotoResponseBody.of(401, "No Photos", null, null));

    }

    // 베스트 사진 추가하기
    @PostMapping("/bestphoto")
    @ApiOperation(value = "베스트3 사진 추가오기", notes = "사진ID를 받아 best사진으로 추가")
    @ApiResponses({
            @ApiResponse(code = 201, message = "추가 완료", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "추가 실패 (3개초과)", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> addBestPhoto(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody @ApiParam(value = "JWT", required = true) StudioEditPhotoSelectReq photoSelectReq) {
        String jwt = token;

         int add_id = Integer.parseInt(photoSelectReq.getAdd_id());

        /* 닉네임을 통한 JWT 확인 후 현재 들어온 JWT와 비교하여 확인 후 삭제할 사진ID보고 삭제 후 추가할 사진ID보고 추가*/
        boolean result = studioEditService.addBestPhoto(jwt, add_id);

        if (result) {
            return ResponseEntity.ok(BaseResponseBody.of(201, "Success"));
        }
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "베스트 사진 추가 실패"));

    }

    // 베스트 사진 수정하기
    @PutMapping("/bestphoto")
    @ApiOperation(value = "베스트3 사진 수정하기", notes = "사진ID를 받아 best사진에서 수정")
    @ApiResponses({
            @ApiResponse(code = 201, message = "수정 완료", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "수정 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> updateBestPhoto(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody @ApiParam(value = "JWT", required = true) StudioEditPhotoSelectReq photoSelectReq) {
        String jwt = token;
        int add_id = Integer.parseInt(photoSelectReq.getAdd_id());
        int del_id = Integer.parseInt(photoSelectReq.getDel_id());

        /* 닉네임을 통한 JWT 확인 후 현재 들어온 JWT와 비교하여 확인 후 삭제할 사진ID보고 삭제 후 추가할 사진ID보고 추가*/
        boolean result = studioEditService.updateBestPhoto(jwt, add_id, del_id);

        if (result) {
            return ResponseEntity.ok(BaseResponseBody.of(201, "Success"));
        }
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "수정 실패"));

    }

    // 베스트 사진 삭제하기
    @DeleteMapping("/bestphoto")
    @ApiOperation(value = "베스트3 사진 삭제하기", notes = "사진ID를 받아 best사진에서 삭제")
    @ApiResponses({
            @ApiResponse(code = 201, message = "삭제 완료", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "삭제 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> delBestPhoto(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody @ApiParam(value = "JWT", required = true) StudioEditPhotoSelectReq photoSelectReq) {
        String jwt = token;

        int del_id = Integer.parseInt(photoSelectReq.getDel_id());

        /* 닉네임을 통한 JWT 확인 후 현재 들어온 JWT와 비교하여 확인 후 삭제할 사진ID보고 삭제*/
        boolean result = studioEditService.delBestPhoto(jwt, del_id);

        if (result) {
            return ResponseEntity.ok(BaseResponseBody.of(201, "Success"));
        }
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "삭제 실패"));

    }

    // 전체사진 추가하기
    @PostMapping("/photo")
   @ApiOperation(value = "작가 전체 사진 추가하기", notes = "작가 전체사진을 추가한다")
    @ApiResponses({
            @ApiResponse(code = 201, message = "추가 완료", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "추가 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> addPgPhoto(
            @RequestHeader(value = "Authorization") String token,
            StudioEditPhotoUploadReq uploadReq, MultipartHttpServletRequest request) {
        String jwt = token;

        String tag[] = uploadReq.getData();

        /* 닉네임을 통한 JWT 확인 후 현재 들어온 JWT와 비교하여 확인 후 전체사진(섬네일) 업로드*/
        boolean result = studioEditService.addPgPhoto(jwt, request, tag);
        if (result) {
            return ResponseEntity.ok(BaseResponseBody.of(201, "Success"));
        }
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "추가 실패"));

    }

    // 전체사진 중 삭제하기
    @DeleteMapping("/photo")
    @ApiOperation(value = "작가 전체 사진 중 하나 삭제하기", notes = "작가 전체사진 중 하나를 삭제한다")
    @ApiResponses({
            @ApiResponse(code = 201, message = "삭제 완료", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "삭제 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> delPgPhoto(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody @ApiParam(value = "JWT", required = true) StudioEditPhotoSelectReq photoSelectReq) {
        String jwt = token;

        int del_id = Integer.parseInt(photoSelectReq.getDel_id());

        /* 닉네임을 통한 JWT 확인 후 현재 들어온 JWT와 비교하여 확인 후 삭제할 사진ID보고 삭제*/
        boolean result = studioEditService.delPgPhoto(jwt, del_id);

        if (result) {
            return ResponseEntity.ok(BaseResponseBody.of(201, "Success"));
        }
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "삭제 실패"));

    }

}
