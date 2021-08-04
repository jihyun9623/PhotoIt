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
@ApiModel("SearchPgLocationResponse")
public class SearchPgLocationRes extends BaseResponseBody {
    @ApiModelProperty(name = "프로필사진, 닉네임", notes = "프로필 사진과 작가 닉네임")
    List<ProfileNickNameRes> profileList;

    public static SearchPgLocationRes of(Integer statusCode, String message, List<ProfileNickNameRes> profileList) {
        SearchPgLocationRes body = new SearchPgLocationRes();
        body.setStatusCode(statusCode);
        body.setMessage(message);
        body.setProfileList(profileList);
        return body;
    }
}
