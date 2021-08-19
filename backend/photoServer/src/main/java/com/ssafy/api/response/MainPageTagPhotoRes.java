package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter @Setter
@ApiModel("MainPageTagPhotoResponse")
public class MainPageTagPhotoRes extends BaseResponseBody {
    @ApiModelProperty(name = "사진 목록", notes = "태그(파일, 사진작가)")
    Map<String, Map<String, String>> tagPhotoList;

    @ApiModelProperty(name = "태그 목록", example = "웨딩, 야외")
    String[] tagList;

    public static MainPageTagPhotoRes of(Integer statusCode, String message, Map<String, Map<String, String>> tagPhotoList, String[] tagList) {
        MainPageTagPhotoRes body = new MainPageTagPhotoRes();
        body.setStatusCode(statusCode);
        body.setMessage(message);
        body.setTagList(tagList);
        body.setTagPhotoList(tagPhotoList);
        return body;
    }

    public static MainPageTagPhotoRes of(Integer statusCode, String message, String[] tagList) {
        MainPageTagPhotoRes body = new MainPageTagPhotoRes();
        body.setStatusCode(statusCode);
        body.setMessage(message);
        body.setTagList(tagList);
        return body;
    }

    @Override
    public String toString() {
        return "MainPageTagPhotoRes{" +
                "tagPhotoList=" + tagPhotoList +
                ", tagList=" + Arrays.toString(tagList) +
                '}';
    }
}
