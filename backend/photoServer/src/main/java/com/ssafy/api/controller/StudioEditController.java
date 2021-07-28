/* 2021-07-27 스켈레톤 코드 작성	by.HeeJung,Lee */

package com.ssafy.api.controller;

import com.ssafy.api.request.StudioEditAuthReq;
import com.ssafy.api.response.StudioEditBestPhotoResponseBody;
import com.ssafy.api.response.StudioEditPgProfileResponseBody;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
	public ResponseEntity<BaseResponseBody> studioAuth(@RequestBody @ApiParam(value="닉네임, JWT", required = true) StudioEditAuthReq authInfo) {
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
			@ApiResponse(code = 200, message = "인증 확인", response = StudioEditPgProfileResponseBody.class),
			@ApiResponse(code = 401, message = "작가 아님", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
	})
	public ResponseEntity<StudioEditPgProfileResponseBody> pgProfi님le(@RequestBody @ApiParam(value = "JWT", required = true) StudioEditAuthReq authInfo) {
		String jwt = authInfo.getJWT();

		/* 닉네임을 통한 JWT 확인 후 현재 들어온 JWT와 비교하여 확인 후 프로필과 지역을 불러옴*/
		String jwtFromServer = "";
		String introduce = "";
		String[] location = {"", ""};

		if(jwt.equals(jwtFromServer)) {
			return ResponseEntity.ok(StudioEditPgProfileResponseBody.of(200, "Success", introduce, location));
		}

		return ResponseEntity.status(401).body(StudioEditPgProfileResponseBody.of(401,"Not Photographer", null, null));
	}

	@GetMapping("/bestphoto")
	@ApiOperation(value = "베스트3 사진 받아오기", notes ="best3사진을 원본사진과 사진ID를 받아온")
	@ApiResponses({
			@ApiResponse(code = 200, message = "인증 확인", response = StudioEditPgProfileResponseBody.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
	})
	public ResponseEntity<StudioEditBestPhotoResponseBody> bestPhoto(@RequestBody @ApiParam(value = "JWT", required = true) StudioEditAuthReq authInfo) {
		String jwt = authInfo.getJWT();

		/* 닉네임을 통한 JWT 확인 후 현재 들어온 JWT와 비교하여 확인 후 베스트사진 3개를 불러옴*/
		String jwtFromServer = "";
		String[] id = {"","",""};

		File resource1 = new File("/com/ssafy/image/image.jpg");
		File resource2 = null;
		File resource3 = null;

		// 파일 불러오기 필요
		List<File> fileList = new ArrayList<File>();
		fileList.add(resource1); fileList.add(resource2); fileList.add(resource3);

		String[] location = {"", ""};

		if(jwt.equals(jwtFromServer)) {
			return ResponseEntity.ok(StudioEditBestPhotoResponseBody.of(200, "Success", id, fileList));
		}

		return ResponseEntity.status(401).body(StudioEditBestPhotoResponseBody.of(401,"Not Photographer", null, null));
	}

}
