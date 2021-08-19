package com.ssafy.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class PhotoIdThumbNickNameRes {
    @ApiModelProperty(name = "사진 Id")
    Integer photoId;

    @ApiModelProperty(name = "썸네일 경로")
    String thumb;

    @ApiModelProperty(name = "작가 닉네임")
    String nickName;

    public static PhotoIdThumbNickNameRes of(Integer photoId, String thumb, String nickName) {
        PhotoIdThumbNickNameRes body = new PhotoIdThumbNickNameRes();
        body.setPhotoId(photoId);
        body.setThumb(thumb);
        body.setNickName(nickName);
        return body;
    }

    @Override
    public String toString() {
        return "PhotoIdThumbNickNameRes{" +
                "photoId=" + photoId +
                ", thumb='" + thumb + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
