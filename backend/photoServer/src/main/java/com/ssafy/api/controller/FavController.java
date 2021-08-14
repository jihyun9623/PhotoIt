package com.ssafy.api.controller;

import com.ssafy.api.request.FavReq;
import com.ssafy.api.response.FavResBody;
import com.ssafy.api.service.FavService;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Fav API")
@RestController
@RequestMapping("/fav")
public class FavController {

    @Autowired
    FavService favService;

    // 찜 추가하기
    @PostMapping("/add")
    @ApiOperation(value = "찜 추가")
    @ApiResponses({
            @ApiResponse(code = 201,message = "추가 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "추가 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> addFav(@RequestBody @ApiParam(value = "유저닉네임,작가닉네임", required = true) FavReq fav){
        String userNick = fav.getUserNick();
        String pgNick = fav.getPgNick();

        /* 닉네임으로 스튜디오 idx -> 없다면 추가 */
        boolean resbody = favService.addFav(userNick,pgNick);

        if(resbody) {
            return ResponseEntity.ok(BaseResponseBody.of(200, "찜목록에 추가되었습니다."));
        }

        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "이미 찜한 작가입니다."));
    }

    // 찜 해제하기
    @PostMapping("/delete")
    @ApiOperation(value = "찜 해제")
    @ApiResponses({
            @ApiResponse(code = 201,message = "해제 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "해제 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> deleteFav(@RequestBody @ApiParam(value = "유저닉네임,작가닉네임", required = true) FavReq fav){
        String userNick = fav.getUserNick();
        String pgNick = fav.getPgNick();

        /* 닉네임으로 스튜디오 idx -> 있다면 삭제 */
        boolean resbody = favService.deleteFav(userNick,pgNick);

        if(resbody) {
            return ResponseEntity.ok(BaseResponseBody.of(200, "찜 해제 완료되었습니다."));
        }

        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "이미 찜 해제한 작가입니다."));
    }

    //사진 확대했을 때 작가 이름 옆의 찜 확인
    @GetMapping("/check/{userNick}/{pgNick}")
    @ApiOperation(value = "찜 확인")
    @ApiResponses({
            @ApiResponse(code = 201,message = "확인 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "확인 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> checkFav(@RequestBody @PathVariable("userNick") String userNick, @PathVariable("pgNick") String pgNick){
        /* 찜목록 조회 후 작가 확인 */
        boolean resbody = favService.checkFav(userNick,pgNick);

        if(resbody) {
            return ResponseEntity.ok(BaseResponseBody.of(200, "찜한 작가임"));
        } else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "찜한 작가가 아님"));

        //return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Failed"));
    }

    //찜목록 불러오기
    @GetMapping("/list/{userNick}")
    @ApiOperation(value = "찜 목록 불러오기")
    @ApiResponses({
            @ApiResponse(code = 201,message = "조회 성공", response = FavResBody.class),
            @ApiResponse(code = 401, message = "조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
    })
    public ResponseEntity<FavResBody> getFavList(@RequestBody @PathVariable("userNick") String userNick){
        /* 찜목록 조회 */
        Integer[] favList = favService.getFavList(userNick);

        if(true) {
            return ResponseEntity.ok(FavResBody.of(200, "Success",favList));
        }

        return ResponseEntity.status(401).body(FavResBody.of(401, "찜한 작가가 없습니다.",null));
    }
}
