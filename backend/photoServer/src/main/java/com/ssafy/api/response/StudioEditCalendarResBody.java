/* 2021-07-27 스켈레톤 코드 작성	by.HeeJung,Lee */

package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BaseResponseBody")
public class StudioEditCalendarResBody extends BaseResponseBody {
    @ApiModelProperty(name="일정 추가/삭제 리스트", example = "210801")
    String[] schedule;

    public static StudioEditCalendarResBody of(Integer statusCode, String message, String[] schedule) {
        StudioEditCalendarResBody body = new StudioEditCalendarResBody();
        body.setStatusCode(statusCode);
        body.setMessage(message);
        body.setSchedule(schedule);
        return body;
    }
}
