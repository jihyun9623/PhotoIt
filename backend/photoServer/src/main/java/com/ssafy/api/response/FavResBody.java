/* 작성자 : 김지현 */

package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Photo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ApiModel("FavResBody")
public class FavResBody extends BaseResponseBody {
//    @ApiModelProperty(name = "작가 닉네임", example = "[한뀨,]")
//    Map<String, List<String>> favNicklist;
//    @ApiModelProperty(name = "작가 베스트사진", example = "[thumbnail,]")
//    Map<String,List<List<String>>> favBestlist;
    @ApiModelProperty(name = "작가 베스트사진", example = "[thumbnail,]")
    Map<String,Map<String,List<String>>> favList;

    public static FavResBody of(Integer statusCode, String message, Map<String,Map<String,List<String>>> favList){
                //Map<String, List<String>> favNicklist, Map<String,List<List<String>>> favBestlist){
        FavResBody body = new FavResBody();
        body.setStatusCode(statusCode);
        body.setMessage(message);
        body.setFavList(favList);
//        body.setFavNicklist(favNicklist);
//        body.setFavBestlist(favBestlist);
        return body;
    }

    @Override
    public String toString() {
        return "FavResBody{" +
                "favList=" + favList +
                '}';
    }
}
