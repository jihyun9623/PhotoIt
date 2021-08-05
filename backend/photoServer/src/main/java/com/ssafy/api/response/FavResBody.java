/* 작성자 : 김지현 */

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
    @ApiModelProperty(name = "찜목록", example = "123")
    Integer[] favlist;

    public static FavResBody of(Integer statusCode, String message, Integer[] favlist){
        FavResBody body = new FavResBody();
        body.setStatusCode(statusCode);
        body.setMessage(message);
        body.setFavlist(favlist);
        return body;
    }
}
