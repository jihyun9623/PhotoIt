package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 지역목록 API ([GET] /user/location) 요청에 대한 응답값 정의.
 */

@Data
@ApiModel("LoginGetResponse")
public class LocationGetRes extends  BaseResponseBody{
	@ApiModelProperty(name="지역리스트", example = "['서울', '인천']")
	List<String> location_list;

	public static LocationGetRes of(Integer statusCode, String message, List<String> location_list){
		// 1. ResponseBody 객체 res 하나 만들어서
		LocationGetRes res=new LocationGetRes();

		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setLocation_list(location_list);
		return res;
	}

}