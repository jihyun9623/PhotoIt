package com.ssafy.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class TagThumbNickNameRes {
    @ApiModelProperty(name = "태그", example = "야외")
    String tag;

    @ApiModelProperty(name = "썸네일, 사진 ID ,사진작가 닉네임 리스트", example = "thumbnail : \"src/~\", nickName : \"HanQ\"")
    List<ThumbNickNameRes> thumbNickNameList;

    public static TagThumbNickNameRes of(String tag, List<ThumbNickNameRes> thumbNickNameList) {
            TagThumbNickNameRes body = new TagThumbNickNameRes();
            body.setTag(tag);
            body.setThumbNickNameList(thumbNickNameList);
            return body;
    }

    @Override
    public String toString() {
        return "TagThumbNickNameRes{" +
                "tag='" + tag + '\'' +
                ", thumbNickNameList=" + thumbNickNameList +
                '}';
    }
}
