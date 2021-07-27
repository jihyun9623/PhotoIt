/* 2021-07-27 스켈레톤 코드 작성	by.HeeJung,Lee */

package com.ssafy.api.controller;

import com.ssafy.api.response.PgProfileResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.StudioEditAuthReq;
import com.ssafy.common.model.response.BaseResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "스튜디오 Edit API", tags = {"edit."})
@RestController
@RequestMapping("/studioedit")
public class StudioEditController {
	
	
	@GetMapping("/studioauth")
	@ApiOperation(value = "마이스튜디오 본인확인", notes = "<strong>닉네임</strong>을 통해 확인한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "인증 확인", response = BaseResponseBody.class),
		@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
		@ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
		@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
	})
	public ResponseEntity<BaseResponseBody> studioAuth(@RequestBody @ApiParam(value="닉네임 정보", required = true) StudioEditAuthReq authInfo) {
		String nickname = authInfo.getNickname();
		String jwt = authInfo.getJWT();

		/* 닉네임을 통한 JWT 확인 후 현재 들어온 JWT와 비교하여 확인 */
		String jwtFromServer = "";

		if(jwt.equals(jwtFromServer)) {
			return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
		}

		return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Not Matched"));
	}

	@GetMapping("/pgprofile")
	@ApiOperation(value = "작가 프로필 받아오기", notes ="작가 프로필을 받아온다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "인증 확인", response = BaseResponseBody.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
	})
	public ResponseEntity<PgProfileResponseBody> pgProfile


}
