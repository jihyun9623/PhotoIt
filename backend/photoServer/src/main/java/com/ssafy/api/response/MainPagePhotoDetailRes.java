package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@ApiModel("MainPagePhotoDetailResponse")
public class MainPagePhotoDetailRes extends BaseResponseBody {
    @ApiModelProperty(name = "원본사진 경로", example = "      ")
    String origin;

    @ApiModelProperty(name = "사진 태그 list", example = "야외, 웨딩")
    String[] tagList;

    @ApiModelProperty(name = "찜 여부", example = "False")
    Boolean favorite;

    @ApiModelProperty(name = "Photo idx")
    int photoIdx;

    @ApiModelProperty(name = "작가 프로필사진")
    String profilePhoto;

    @ApiModelProperty(name = "썸네일 경로, 사진id N개")
    List<ThumbPhotoIdRes> thumbPhotoIds;

    public static MainPagePhotoDetailRes of(Integer statusCode, String message, String origin, String[] tagList, Boolean favorite,
                                            int photoIdx, String profilePhoto, List<ThumbPhotoIdRes> thumbPhotoIds) {
        MainPagePhotoDetailRes body = new MainPagePhotoDetailRes();
        body.setStatusCode(statusCode);
        body.setMessage(message);
        body.setOrigin(origin);
        body.setTagList(tagList);
        body.setFavorite(favorite);
        body.setPhotoIdx(photoIdx);
        body.setProfilePhoto(profilePhoto);
        body.setThumbPhotoIds(thumbPhotoIds);
        return body;
    }


}
