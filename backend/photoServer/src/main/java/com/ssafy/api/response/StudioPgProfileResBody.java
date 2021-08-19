/* 작성자 : 김지현 */

package com.ssafy.api.response;


import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@ApiModel("StudioPgProfileResBody")
public class StudioPgProfileResBody extends BaseResponseBody{
    @ApiModelProperty(name="한줄 프로필", example = "안녕하세요 저는")
    String introduce;
    @ApiModelProperty(name="지역 목록", example = "서울,인천")
    String[] location;
    @ApiModelProperty(name="프로필 사진", example = "사진 string")
    String profPhoto;

    public static StudioPgProfileResBody of(Integer statusCode, String message, String introduce, String[] location, String profPhoto) {
        StudioPgProfileResBody body = new StudioPgProfileResBody();
        body.setStatusCode(statusCode);
        body.setMessage(message);
        body.setIntroduce(introduce);
        body.setLocation(location);
        body.setProfPhoto(profPhoto);
        return body;
    }

    @Override
    public String toString() {
        return "StudioPgProfileResBody{" +
                "introduce='" + introduce + '\'' +
                ", location=" + Arrays.toString(location) +
                ", profPhoto='" + profPhoto + '\'' +
                '}';
    }
}

