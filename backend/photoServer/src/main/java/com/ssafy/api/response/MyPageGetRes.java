package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Favorite;
import com.ssafy.db.entity.MyStudio;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 마이페이지 API ([GET] /mypage) 요청에 대한 응답값 정의.
 */

@Data
@ApiModel("UserLoginPostResponse")
public class MyPageGetRes extends  BaseResponseBody{
	// {
	//    "id": "A@B.C",
	//    "passwd": "whoareyou123!",
	//    "nickname": "ImBabyShark",
	//    "pg": "true",
	//    "location": ["인천", "서울"],
	//    "introduce": "JYP! Yeah!",
	// }
	@ApiModelProperty(name="유저 아이디", example = "sr5871@naver.com")
	String id;
	@ApiModelProperty(name="유저 비밀번호", example = "1234")
	String passwd;	//암호화해서 보여줘야함
	@ApiModelProperty(name="유저 닉네임", example = "김작가")
	String nickname;
	@ApiModelProperty(name="작가 여부", example = "true")
	Boolean pg;
	@ApiModelProperty(name="프로필사진", example = "-")
	String photo;
	@ApiModelProperty(name="작가 활동 지역", example = "[서울, 경기]")
	List location;
	@ApiModelProperty(name="작가 한 줄 소개", example = "안녕하세요 김작가입니다.")
	String introduce;

	@Builder
	public MyPageGetRes(String id, String nickname, String passwd, Boolean pg, String photo, List Location, String introduce) {
		this.id = id;
		this.nickname = nickname;
		this.passwd = passwd;
		this.pg = pg;
		this.photo = photo;
		this.location=Location;
		this.introduce=introduce;
	}

//	public static MyPageGetRes of(Integer statusCode, String msg, String id, String passwd, String nickname, Boolean pg, List location, String introduce){
//		// 1. ResponseBody 객체 res 하나 만들어서
//		MyPageGetRes res=MyPageGetRes.builder().
//				id(
//
//		// 2. BaseResponseBody에서 상속받은 응답상태랑 메세지 설정해주고
//		res.setStatusCode(statusCode);
//		res.setMessage(msg);
//
//		res.setId(id);
//		res.setPasswd(passwd);
//		res.setNickname(nickname);
//		res.setPg(pg);
//		res.setLocation(location);
//		res.setIntroduce(introduce);
//		return res;
//	}
	@Override
	public String toString(){
		return id+" / "+passwd+" / "+nickname+" / "+pg;
	}
}