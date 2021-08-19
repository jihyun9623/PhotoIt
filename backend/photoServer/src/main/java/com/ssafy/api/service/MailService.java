package com.ssafy.api.service;

import com.ssafy.api.request.MailPostReq;

import javax.mail.AuthenticationFailedException;

public interface MailService {
    // 이메일 인증
    boolean emailAuth(MailPostReq authinfo);

    // 이메일 코드 확인
    void emailAuthCheck(MailPostReq authinfo) throws AuthenticationFailedException;

    // 코드 검증
    boolean isVerify(MailPostReq authInfo);
    // 이메일 중복 확인
}
