/* 작성자 : 김지현 */

package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("StudioEditCalendarResBody")
public class StudioCalendarResBody extends BaseResponseBody {
    @ApiModelProperty(name="일정 리스트", example = "210801")
    LocalDateTime[] calList;

    public static StudioCalendarResBody of(Integer statusCode, String message, LocalDateTime[] calList) {
        StudioCalendarResBody body = new StudioCalendarResBody();
        body.setStatusCode(statusCode);
        body.setMessage(message);
        body.setCalList(calList);
        return body;
    }
}
