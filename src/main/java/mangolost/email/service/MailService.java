package mangolost.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender; //读取配置文件中的参数

    private static final String RECEIVER = "376740391@qq.com";

    /**
     *
     */
    public void sendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(RECEIVER); //自己给自己发送邮件
        message.setSubject("主题：简单邮件");
        message.setText("Hello World");
        mailSender.send(message);
    }

    /**
     *
     */
    public void sendHtmlMail() {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(RECEIVER);
            helper.setSubject("标题：发送Html内容");

            String sb = "<h1>大标题-h1</h1>" +
                    "<p style='color:#F00'>红色字</p>" +
                    "<p style='text-align:right'>右对齐</p>";
            helper.setText(sb, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (message != null) {
            mailSender.send(message);
        }
    }

    /**
     *
     */
    public void sendAttachmentMail() {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(RECEIVER);
            helper.setSubject("主题：带附件的邮件");
            helper.setText("带附件的邮件内容");
            //注意项目路径问题，自动补用项目路径
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/earth.jpg"));
            //加入邮件
            helper.addAttachment("pic.jpg", file);
        } catch (Exception e){
            e.printStackTrace();
        }
        if (message != null) {
            mailSender.send(message);
        }
    }

    /**
     *
     */
    public void sendInlineMediaMail() {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(RECEIVER);
            helper.setSubject("主题：带静态资源的邮件");
            //第二个参数指定发送的是HTML格式,同时cid:是固定的写法
            helper.setText("<html><body>带静态资源的邮件内容 图片:<img src='cid:photo1' /></body></html>", true);

            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/moon.jpg"));
            helper.addInline("photo1",file);
        } catch (Exception e){
            e.printStackTrace();
        }
        if (message != null) {
            mailSender.send(message);
        }
    }
}
