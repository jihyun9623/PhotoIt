/* 2021-07-27 스켈레톤 코드 작성	by.HeeJung,Lee */
/* 2021-08-03 Feat : Service 구현에 따른 컨트롤러 수정	by.HeeJung,Lee */

package com.ssafy.api.controller;

import com.ssafy.api.request.StudioEditAuthReq;
import com.ssafy.api.request.StudioEditPhotoSelectReq;
import com.ssafy.api.request.StudioEditPhotoUploadReq;
import com.ssafy.api.response.StudioEditPgProfileResponseBody;
import com.ssafy.api.response.StudioEditPhotoResponseBody;
import com.ssafy.api.service.StudioEditService;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Api(value = "스튜디오 Edit API", tags = {"edit."})
@RestController
@RequestMapping("/studioedit")
public class StudioEditController {

	@Autowired
	StudioEditService studioEditService;

	// 본인확인 인증
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

		// 현재 JWT의 nickname과 가져온 닉네임 비고
		boolean result = studioEditService.studioAuth(jwt, nickname);

		if(result) { return ResponseEntity.ok(BaseResponseBody.of(200, "Success")); }
		else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Not Matched"));
	}

	// 프로필 받아오기
	@GetMapping("/pgprofile")
	@ApiOperation(value = "작가 프로필 받아오기", notes ="작가 프로필을 받아온다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "인증 확인", response = StudioEditPgProfileResponseBody.class),
			@ApiResponse(code = 401, message = "작가 아님", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "프로필 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
	})
	public ResponseEntity<StudioEditPgProfileResponseBody> getPgProfile(@RequestBody @ApiParam(value = "JWT", required = true) StudioEditAuthReq authInfo) {
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

	// 베스트 사진 받아오기
	@GetMapping("/bestphoto")
	@ApiOperation(value = "베스트3 사진 받아오기", notes ="best3사진을 원본사진과 사진ID를 받아온")
	@ApiResponses({
			@ApiResponse(code = 200, message = "인증 확인", response = StudioEditPhotoResponseBody.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
	})
	public ResponseEntity<StudioEditPhotoResponseBody> getBestPhoto(@RequestBody @ApiParam(value = "JWT", required = true) StudioEditAuthReq authInfo) {
		String jwt = authInfo.getJWT();

		/* 닉네임을 통한 JWT 확인 후 현재 들어온 JWT와 비교하여 확인 후 베스트사진 3개를 불러옴*/
		String jwtFromServer = "";
		String[] id = {"","",""};

		MultipartFile resource1 = null;
		MultipartFile resource2 = null;
		MultipartFile resource3 = null;

		// 파일 불러오기 필요
		List<MultipartFile> fileList = new ArrayList<>();
		fileList.add(resource1); fileList.add(resource2); fileList.add(resource3);

		String[] location = {"", ""};

		if(jwt.equals(jwtFromServer)) {
			return ResponseEntity.ok(StudioEditPhotoResponseBody.of(200, "Success", id, fileList));
		}

		return ResponseEntity.status(401).body(StudioEditPhotoResponseBody.of(401,"Not Photographer", null, null));
	}

	// 전체사진 받아오기
	@GetMapping("/photo")
	@ApiOperation(value = "작가 전체 사진 받아오기", notes ="작가 전체사진을 섬네일사진과 사진ID, 닉네임을 받아온다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "인증 확인", response = StudioEditPhotoResponseBody.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사진 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
	})
	public ResponseEntity<StudioEditPhotoResponseBody> getPgPhoto(@RequestBody @ApiParam(value = "JWT", required = true) StudioEditAuthReq authInfo) {
		String jwt = authInfo.getJWT();

		/* 닉네임을 통한 JWT 확인 후 현재 들어온 JWT와 비교하여 확인 후 전체사진(섬네일) 모두를 불러옴*/
		String jwtFromServer = "";
		String[] id = {"","",""};

		MultipartFile resource1 = null;
		MultipartFile resource3 = null;
		MultipartFile resource2 = null;

		// 파일 불러오기 필요
		List<MultipartFile> fileList = new ArrayList<>();
		fileList.add(resource1); fileList.add(resource2); fileList.add(resource3);

		if(jwt.equals(jwtFromServer)) {
			return ResponseEntity.ok(StudioEditPhotoResponseBody.of(200, "Success", id, fileList));
		}

		return ResponseEntity.status(401).body(StudioEditPhotoResponseBody.of(401,"Not Photographer", null, null));
	}

	// 베스트 사진 추가하기
	@PostMapping("/bestphoto")
	@ApiOperation(value = "베스트3 사진 추가오기", notes ="사진ID를 받아 best사진으로 추가")
	@ApiResponses({
			@ApiResponse(code = 201, message = "추가 완료", response = BaseResponseBody.class),
			@ApiResponse(code = 401, message = "추가 실패 (3개초과)", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사진 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
	})
	public ResponseEntity<BaseResponseBody> addBestPhoto(@RequestBody @ApiParam(value = "JWT", required = true) StudioEditPhotoSelectReq photoSelectReq) {
		String jwt = photoSelectReq.getJWT();
		String add_id = photoSelectReq.getAdd_id();

		/* 닉네임을 통한 JWT 확인 후 현재 들어온 JWT와 비교하여 확인 후 삭제할 사진ID보고 삭제 후 추가할 사진ID보고 추가*/
		String jwtFromServer = "";

		if(jwt.equals(jwtFromServer)) {
			return ResponseEntity.ok(BaseResponseBody.of(201, "Success"));
		}

		return ResponseEntity.status(401).body(BaseResponseBody.of(401,"exceeded 3"));
	}

	// 베스트 사진 수정하기
	@PutMapping("/bestphoto")
	@ApiOperation(value = "베스트3 사진 수정하기", notes ="사진ID를 받아 best사진에서 수정")
	@ApiResponses({
			@ApiResponse(code = 201, message = "수정 완료", response = BaseResponseBody.class),
			@ApiResponse(code = 401, message = "수정 실패 (이미 베스트 사진이 아님)", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사진 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
	})
	public ResponseEntity<BaseResponseBody> updateBestPhoto(@RequestBody @ApiParam(value = "JWT", required = true) StudioEditPhotoSelectReq photoSelectReq) {
		String jwt = photoSelectReq.getJWT();
		String add_id = photoSelectReq.getDel_id();
		String del_id = photoSelectReq.getDel_id();

		/* 닉네임을 통한 JWT 확인 후 현재 들어온 JWT와 비교하여 확인 후 삭제할 사진ID보고 삭제 후 추가할 사진ID보고 추가*/
		String jwtFromServer = "";

		if(jwt.equals(jwtFromServer)) {
			return ResponseEntity.ok(BaseResponseBody.of(201, "Success"));
		}

		return ResponseEntity.status(401).body(BaseResponseBody.of(401,"Not Best Photo"));
	}

	// 베스트 사진 삭제하기
	@DeleteMapping("/bestphoto")
	@ApiOperation(value = "베스트3 사진 삭제하기", notes ="사진ID를 받아 best사진에서 삭제")
	@ApiResponses({
			@ApiResponse(code = 201, message = "삭제 완료", response = BaseResponseBody.class),
			@ApiResponse(code = 401, message = "삭제 실패 (이미 베스트 사진이 아님)", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사진 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
	})
	public ResponseEntity<BaseResponseBody> delBestPhoto(@RequestBody @ApiParam(value = "JWT", required = true) StudioEditPhotoSelectReq photoSelectReq) {
		String jwt = photoSelectReq.getJWT();
		String del_id = photoSelectReq.getDel_id();

		/* 닉네임을 통한 JWT 확인 후 현재 들어온 JWT와 비교하여 확인 후 삭제할 사진ID보고 삭제*/
		String jwtFromServer = "";

		if(jwt.equals(jwtFromServer)) {
			return ResponseEntity.ok(BaseResponseBody.of(201, "Success"));
		}

		return ResponseEntity.status(401).body(BaseResponseBody.of(401,"Not Best Photo"));
	}

	// 전체사진 추가하기
	@PostMapping("/photo")
	@ApiOperation(value = "작가 전체 사진 추가하기", notes ="작가 전체사진을 추가한다")
	@ApiResponses({
			@ApiResponse(code = 201, message = "추가 완료", response = BaseResponseBody.class),
			@ApiResponse(code = 401, message = "추가 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "용량 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
	})
	public ResponseEntity<BaseResponseBody> addPgPhoto(@RequestBody @ApiParam(value = "JWT", required = true) StudioEditPhotoUploadReq uploadReq) {
		String jwt = uploadReq.getJWT();
		String tag[][] = uploadReq.getTag();
		MultipartFile[] files = uploadReq.getFiles();

		/* 닉네임을 통한 JWT 확인 후 현재 들어온 JWT와 비교하여 확인 후 전체사진(섬네일) 업로드*/
		String jwtFromServer = "";

		if(jwt.equals(jwtFromServer)) {
			return ResponseEntity.ok(BaseResponseBody.of(201, "Success"));
		}

		return ResponseEntity.status(401).body(BaseResponseBody.of(401,"Fail"));
	}

	// 전체사진 중 삭제하기
	@DeleteMapping("/photo")
	@ApiOperation(value = "작가 전체 사진 중 하나 삭제하기", notes ="작가 전체사진 중 하나를 삭제한다")
	@ApiResponses({
			@ApiResponse(code = 201, message = "삭제 완료", response = BaseResponseBody.class),
			@ApiResponse(code = 401, message = "삭제 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사진 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
	})
	public ResponseEntity<BaseResponseBody> delPgPhoto(@RequestBody @ApiParam(value = "JWT", required = true) StudioEditPhotoSelectReq photoSelectReq) {
		String jwt = photoSelectReq.getJWT();
		String del_id = photoSelectReq.getDel_id();

		/* 닉네임을 통한 JWT 확인 후 현재 들어온 JWT와 비교하여 확인 후 삭제할 사진ID보고 삭제*/
		String jwtFromServer = "";

		if(jwt.equals(jwtFromServer)) {
			return ResponseEntity.ok(BaseResponseBody.of(201, "Success"));
		}

		return ResponseEntity.status(401).body(BaseResponseBody.of(401,"Fail"));
	}

}
