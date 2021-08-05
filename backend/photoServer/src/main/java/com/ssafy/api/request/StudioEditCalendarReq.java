/* 2021-07-27 스켈레톤 코드 작성	by.HeeJung,Lee */

package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel("StudioEditCalendarReq")
public class StudioEditCalendarReq {
    @ApiModelProperty(name = "pg_nickname", example = "GodHanQ")
    String nickname;
    @ApiModelProperty(name = "JWT", example = "HOTSUMMERTURNONAIRCONTROLLERTHENEARTHSICK")
    String JWT;
    @ApiModelProperty(name = "cal_time", example = "210729")
    Date[] cal_time;
}
