package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterPostRes extends BaseResponseBody {
    public static UserRegisterPostRes of(Integer statusCode, String message){
        // 1. ResponseBody 객체 res 하나 만들어서
        UserRegisterPostRes res=new UserRegisterPostRes();

        // 2. BaseResponseBody에서 상속받은 응답상태랑 메세지 설정해주고
        res.setStatusCode(statusCode);
        res.setMessage(message);

        return res;
    }

}
