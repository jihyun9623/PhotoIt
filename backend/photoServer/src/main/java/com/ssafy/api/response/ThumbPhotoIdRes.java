package com.ssafy.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ThumbPhotoIdRes {
    @ApiModelProperty(name = "썸네일 경로")
    String thumbnail;

    @ApiModelProperty(name = "사진 ID")
    Integer photoId;

    public static ThumbPhotoIdRes of(String thumbnail, Integer photoId) {
        ThumbPhotoIdRes body = new ThumbPhotoIdRes();
        body.setPhotoId(photoId);
        body.setThumbnail(thumbnail);
        return body;
    }

    @Override
    public String toString() {
        return "ThumbPhotoIdRes{" +
                "thumbnail='" + thumbnail + '\'' +
                ", photoId=" + photoId +
                '}';
    }
}
