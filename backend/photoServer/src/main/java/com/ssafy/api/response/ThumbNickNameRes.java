package com.ssafy.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ThumbNickNameRes {
    @ApiModelProperty(name = "썸네일 경로", example = "src/~...")
    String thumbnail;

    @ApiModelProperty(name = "사진작가 닉네임", example = "HanQ")
    String nickName;

    public static ThumbNickNameRes of(String thumbnail, String nickName) {
        ThumbNickNameRes body = new ThumbNickNameRes();
        body.setNickName(nickName);
        body.setThumbnail(thumbnail);
        return body;
    }
}
