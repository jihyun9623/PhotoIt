package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter @Setter
@ApiModel("MainPageLocationResponse")
public class MainPageLocationRes extends BaseResponseBody {
    @ApiModelProperty(name = "지역 목록", example = "서울, 인천")
    String[] locationList;

    public static MainPageLocationRes of(Integer statusCode, String message, String[] locationList) {
        MainPageLocationRes body = new MainPageLocationRes();
        body.setStatusCode(statusCode);
        body.setMessage(message);
        body.setLocationList(locationList);
        return body;
    }

    @Override
    public String toString() {
        return "MainPageLocationRes{" +
                "locationList=" + Arrays.toString(locationList) +
                '}';
    }
}
