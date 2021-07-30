package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel("SearchPageRequest")
public class SearchPageReq {
    @ApiModelProperty(name = "JWT", example = "NEMOBAJISPONGEBOBILOVEMONDAY")
    String JWT;

    @ApiModelProperty(name = "userId", example = "ha@n.q")
    String id;

}
