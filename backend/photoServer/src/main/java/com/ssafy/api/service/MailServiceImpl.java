package com.ssafy.api.service;

import com.ssafy.api.request.MailPostReq;
import com.ssafy.common.util.EmailAuthDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final EmailAuthDao emailAuthDao;
    private static final String FROM_ADDRESS = "PHOTO-IT@gmail.com";

    @Autowired
    private SpringTemplateEngine templateEngine;

    public String createKey() {
        int random_code = new Random().nextInt(100000) + 10000;
        String join_code = String.valueOf(random_code);
        return join_code;
    }

    // 이메일 인증 요청을 보냄.
    @Override
    public boolean emailAuth(MailPostReq authinfo) {
        String join_code = createKey();
        String subject = "Photo-it 이메일 인증 코드 발급 안내입니다.";
        System.out.println("이메일 코드 : " + join_code);
        System.out.println("수신자 : " + authinfo.getId());
        //---------------------------------------------------------------------------------------------
        MimeMessage msg = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
            // 보낼 메일 설정
            helper.setSubject(subject); // 제목
            helper.setFrom(FROM_ADDRESS);   // 발신자주소
            helper.setTo(authinfo.getId()); // 수신자주소

            // 템플릿에 전달할 데이터 설정
            MimeMultipart multipart=new MimeMultipart("related");
            BodyPart msgBodyPart = new MimeBodyPart();
            String htmlText="<div style=\"text-align:center\"><img src=\"cid:image\" height=\"80\" width=\"auto\" style=\"text-align: center;\"></div>";
            msgBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(msgBodyPart);

//            msgBodyPart=new MimeBodyPart();
////            DataSource fds=new FileDataSource("C:\\Users\\multicampus\\S05P12A108\\backend\\photoServer\\src\\main\\resources\\img\\logo_remove.png");
//            DataSource fds=new FileDataSource(""); //resources가 build하면 classpath로 된다!!
//            msgBodyPart.setDataHandler(new DataHandler(fds));
//            msgBodyPart.setHeader("Content-ID", "<image>");
//            multipart.addBodyPart(msgBodyPart);
//
//            msg.setContent(multipart);

            msgBodyPart=new MimeBodyPart();
            Context context = new Context();
            context.setVariable("authorization_key", join_code);
            // 메일 내용 설정 : 템플릿 프로세스
            String html=templateEngine.process("mail-template", context);
            //helper.setText(html, true);
            msgBodyPart.setContent(html, "text/html; charset=UTF-8");
            multipart.addBodyPart(msgBodyPart);
            msg.setContent(multipart);

            // 메일 전송
            javaMailSender.send(msg);

            // Redis에 저장
            emailAuthDao.createEmailAuth(authinfo.getId(), join_code);

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void emailAuthCheck(MailPostReq authinfo) throws AuthenticationFailedException {
        if(isVerify(authinfo)){
            throw new AuthenticationFailedException("인증 실패");
        }
        // 인증했으면 redis에서 해당 id 제거
        emailAuthDao.removeEmailAuth(authinfo.getId());
    }

    public boolean isVerify(MailPostReq authInfo){
        return !(emailAuthDao.hasKey(authInfo.getId()) &&   // redis에 해당 id의 키가 남아있고
                emailAuthDao.getEmailAuth(authInfo.getId()).equals(authInfo.getCode())
                    // redis에서 해당 id의 인증코드와 + 지금 들어온(=사용자가 입력한) 코드가 같은지 여부
                // 둘 다 ok라면 true, 하나라도 no면 false.
                // 즉 검증된 코드라면 false를 반환.
        );
    }
}
