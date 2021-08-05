/* 작성자 : 김지현 */

package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudioPgProfileReq")
public class StudioPgProfileReq {
    @ApiModelProperty(name = "pg_nickname", example = "GodHanQ")
    String nickname;
}
