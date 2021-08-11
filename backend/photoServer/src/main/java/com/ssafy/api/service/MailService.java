package com.ssafy.api.service;

import com.ssafy.api.request.MailAuthPostReq;
import com.ssafy.api.request.MailPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.common.model.response.BaseResponseBody;

public interface MailService {
    boolean emailAuth(MailPostReq authinfo);

    boolean emailAuthCheck(MailAuthPostReq authinfo);
    // 이메일 인증
    // 이메일 중복 확인
    // 이메일 코드 확인
}
