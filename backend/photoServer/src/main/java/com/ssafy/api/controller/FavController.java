package com.ssafy.api.controller;

import com.ssafy.api.request.FavReq;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Fav API")
@RestController
@RequestMapping("fav")
public class FavController {

    //토글로 찜하기/찜 해제하기
    @PostMapping("edit")
    @ApiOperation(value = "찜 토글")
    @ApiResponses({
            @ApiResponse(code = 200,message = "수정 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "수정 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> toggleFav(@RequestBody @ApiParam(value = "닉네임", required = true) FavReq fav){
        String nickname = fav.getNickname();

        /* 닉네임으로 찜목록 검색 후 */
        String dbsearch = "";

        if(true) {
            return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
        }

        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Failed"));
    }

    //사진 확대했을 때 작가 이름 옆의 찜 확인
    @GetMapping("check")
    @ApiOperation(value = "찜 확인")
    @ApiResponses({
            @ApiResponse(code = 200,message = "조회 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> checkFav(@RequestBody @ApiParam(value = "닉네임", required = true) FavReq fav){
        String nickname = fav.getNickname();

        /* 찜목록 조회 후 작가 확인 */
        String dbsearch = "";

        if(true) {
            return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
        }

        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Failed"));
    }

    //찜목록 불러오기
    @GetMapping("list")
    @ApiOperation(value = "찜목록 불러오기")
    @ApiResponses({
            @ApiResponse(code = 200,message = "조회 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> getFavList(@RequestBody @ApiParam(value = "닉네임", required = true) FavReq fav){
        String nickname = fav.getNickname();

        /* 찜목록 조회 */
        String dbsearch = "";

        if(true) {
            return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
        }

        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Failed"));
    }
}
