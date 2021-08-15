package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel("MainPageTagPhotoResponse")
public class PhotoIdPhotoRes {
    @ApiModelProperty(name = "포토 id", example = "11")
    Integer photoId;

    @ApiModelProperty(name = "원본사진 경로", example = "src/~...")
    String photoOrigin;

    public static PhotoIdPhotoRes of(Integer photoId, String photoOrigin) {
        PhotoIdPhotoRes body = new PhotoIdPhotoRes();
        body.setPhotoId(photoId);
        body.setPhotoOrigin(photoOrigin);
        return body;
    }
}
