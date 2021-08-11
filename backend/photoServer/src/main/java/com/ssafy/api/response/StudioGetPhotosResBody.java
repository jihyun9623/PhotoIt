/* 작성자 : 김지현 */

package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Getter
@Setter
@ApiModel("StudioGetPhotosResBody")
public class StudioGetPhotosResBody extends BaseResponseBody {
    @ApiModelProperty(name="사진 ID 배열", example = "123512")
    String[] pid;
    @ApiModelProperty(name="사진 파일", example = "multipart 형")
    String[] origin;

    public static StudioGetPhotosResBody of(Integer statusCode, String message, String[] pid, String[] origin){
        StudioGetPhotosResBody body = new StudioGetPhotosResBody();
        body.setStatusCode(statusCode);
        body.setMessage(message);
        body.setPid(pid);
        body.setOrigin(origin);
        return body;
    }
}
