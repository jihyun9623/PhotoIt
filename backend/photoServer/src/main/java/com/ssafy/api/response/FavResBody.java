package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FavResBody")
public class FavResBody extends BaseResponseBody {
    @ApiModelProperty(name = "찜목록", example = "pghanQ")
    String[] favlist;

    public static FavResBody of(Integer statusCode, String message, String[] favlist){
        FavResBody body = new FavResBody();
        body.getStatusCode();
        body.getMessage();
        body.getFavlist();
        return body;
    }
}
