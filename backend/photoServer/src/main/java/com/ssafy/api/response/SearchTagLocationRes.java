package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;
import java.util.Map;

@Getter @Setter
@ApiModel("SearchTagLocationResponse")
public class SearchTagLocationRes extends BaseResponseBody {
    @ApiModelProperty(name = "사진 썸네일 목록", notes = "사진ID ,[썸네일과 그 사진의 작가의 닉네임] 리스트")
    List<PhotoIdThumbNickNameRes> photoList;

    @ApiModelProperty(name = "태그", notes = "태그이름")
    String tag;

    public static SearchTagLocationRes of(Integer statusCode, String message, List<PhotoIdThumbNickNameRes> photoList, String tag) {
        SearchTagLocationRes body = new SearchTagLocationRes();
        body.setStatusCode(statusCode);
        body.setMessage(message);
        body.setPhotoList(photoList);
        body.setTag(tag);
        return body;
    }

}
