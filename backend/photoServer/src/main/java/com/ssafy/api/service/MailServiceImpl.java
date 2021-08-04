package com.ssafy.api.service;

import com.ssafy.api.request.MailAuthPostReq;
import com.ssafy.api.request.MailPostReq;
import com.ssafy.common.model.response.BaseResponseBody;
import com.sun.mail.util.logging.MailHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{

    @Autowired
    private JavaMailSender javaMailSender;
    private static final String FROM_ADDRESS="puddding.o3o@gmail.com";

    // 이메일 인증 요청을 보냄.
    @Override
    public boolean emailAuth(MailPostReq authinfo) {
        int random_code=new Random().nextInt(100000)+10000;
        String join_code=String.valueOf(random_code);
        String subject="이메일 인증 코드 발급 안내입니다.";
        StringBuilder sb=new StringBuilder();
        sb.append("귀하의 인증 코드는 ").append(join_code).append(" 입니다.");
//        MailAuthPostReq sendMail=new MailAuthPostReq();
//        sendMail.setTitle(subject);
//        sendMail.setMsg(sb.toString());
//        sendMail.setAddress(authinfo.getId());
        System.out.println("이메일 코드 : "+join_code);
        System.out.println("수신자 : "+authinfo.getId());
    //---------------------------------------------------------------------------------------------
        MimeMessage msg=javaMailSender.createMimeMessage();
        try{
        MimeMessageHelper helper=new MimeMessageHelper(msg, true, "UTF-8");
        // 보낼 메일 설정
        helper.setSubject(subject);
        msg.setText(sb.toString());
        helper.setFrom(FROM_ADDRESS);
        helper.setTo(authinfo.getId());
        javaMailSender.send(msg);
        return true;
        }catch (MessagingException e){
            e.printStackTrace();
        }
        return false;
    }
}
