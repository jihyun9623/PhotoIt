/* 작성자 : 김지현 */
package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ApiModel("StudioCalendarReq")
public class StudioCalendarReq {
    @ApiModelProperty(name = "pg_nickname", example = "GodHanQ")
    String nickname;
    @ApiModelProperty(name = "JWT", example = "HOTSUMMERTURNONAIRCONTROLLERTHENEARTHSICK")
    String JWT;
    @ApiModelProperty(name = "cal_time", example = "210729")
    LocalDateTime[] cal_time;
}
