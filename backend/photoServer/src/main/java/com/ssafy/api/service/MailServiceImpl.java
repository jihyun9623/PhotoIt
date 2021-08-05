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

    public String emailForm(String join_code){
        StringBuilder msg=new StringBuilder();
        msg.append("<img width=\"120\" height=\"36\" style=\"margin-top: 0; margin-right: 0; margin-bottom: 32px; margin-left: 0px; padding-right: 30px; padding-left: 30px;\" src=\"https://slack.com/x-a1607371436052/img/slack_logo_240.png\" alt=\"\" loading=\"lazy\">");
        msg.append("<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 인증 코드</h1>");
        msg.append("<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 인증 코드를 회원가입 이메일 인증 코드 입력란에 입력하세요.</p>");
        msg.append("<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">");
        msg.append(join_code);
        msg.append("</td></tr></tbody></table></div>");
        return msg.toString();
    }

    public String createKey(){
        int random_code=new Random().nextInt(100000)+10000;
        String join_code=String.valueOf(random_code);
        return join_code;
    }
    // 이메일 인증 요청을 보냄.
    @Override
    public boolean emailAuth(MailPostReq authinfo) {
        String join_code=createKey();
        String subject="이메일 인증 코드 발급 안내입니다.";
        System.out.println("이메일 코드 : "+join_code);
        System.out.println("수신자 : "+authinfo.getId());
    //---------------------------------------------------------------------------------------------
        MimeMessage msg=javaMailSender.createMimeMessage();
        try{
        MimeMessageHelper helper=new MimeMessageHelper(msg, true, "UTF-8");
        // 보낼 메일 설정
        helper.setSubject(subject); // 제목
        msg.setText(emailForm(join_code));  // 인증코드
        helper.setFrom(FROM_ADDRESS);   // 발신자주소
        helper.setTo(authinfo.getId()); // 수신자주소
        javaMailSender.send(msg);
        return true;
        }catch (MessagingException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean emailAuthCheck(MailAuthPostReq authinfo) {
        return false;
    }
}
