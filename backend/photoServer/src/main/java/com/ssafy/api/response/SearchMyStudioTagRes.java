package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Map;

@Getter @Setter
@ApiModel("SearchMyStudioTagResponse")
public class SearchMyStudioTagRes extends BaseResponseBody {
    @ApiModelProperty(name = "사진, 사진id", notes = "사진과 사진ID List")
    Map<String, Integer> photoList;

    public static SearchMyStudioTagRes of(Integer statusCode, String message, Map<String, Integer> photoList) {
        SearchMyStudioTagRes body = new SearchMyStudioTagRes();
        body.setStatusCode(statusCode);
        body.setMessage(message);
        body.setPhotoList(photoList);
        return body;
    }

    @Override
    public String toString() {
        return "SearchMyStudioTagRes{" +
                "photoList=" + photoList +
                '}';
    }
}
