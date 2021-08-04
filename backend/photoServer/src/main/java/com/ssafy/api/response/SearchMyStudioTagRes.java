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
@ApiModel("SearchMyStudioTagResponse")
public class SearchMyStudioTagRes extends BaseResponseBody {
    @ApiModelProperty(name = "원본사진, 사진id", notes = "원본사진과 사진ID List")
    List<PhotoIdPhotoRes> photoList;

    public static SearchMyStudioTagRes of(Integer statusCode, String message, List<PhotoIdPhotoRes> photoList) {
        SearchMyStudioTagRes body = new SearchMyStudioTagRes();
        body.setStatusCode(statusCode);
        body.setMessage(message);
        body.setPhotoList(photoList);
        return body;
    }
}
