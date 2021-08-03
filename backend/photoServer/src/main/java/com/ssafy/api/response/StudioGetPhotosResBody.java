package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudioGetPhotosResBody")
public class StudioGetPhotosResBody extends BaseResponseBody {
    @ApiModelProperty(name = "best3/전체 사진 리스트", example = "사진 id 리스트")
    String[] Photos;

    public static StudioGetPhotosResBody of(Integer statusCode, String message, String[] Photos){
        StudioGetPhotosResBody body = new StudioGetPhotosResBody();
        body.setStatusCode(statusCode);
        body.setMessage(message);
        body.setPhotos(Photos);
        return body;
    }
}
