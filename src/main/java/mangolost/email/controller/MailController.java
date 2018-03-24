package mangolost.email.controller;

import mangolost.email.common.CommonResult;
import mangolost.email.common.utils.JsonPUtils;
import mangolost.email.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    /**
     *
     * @param request
     * @param commonResult
     * @param callback
     * @return
     */
    @RequestMapping("sendsimple")
    public Object sendSimple(HttpServletRequest request, CommonResult commonResult, String callback) {
        mailService.sendSimpleMail();
        commonResult.setData("Good");
        return JsonPUtils.doJsonP(commonResult, callback);
    }

    /**
     *
     * @param request
     * @param commonResult
     * @param callback
     * @return
     */
    @RequestMapping("sendhtml")
    public Object sendHtml(HttpServletRequest request, CommonResult commonResult, String callback) {
        mailService.sendHtmlMail();
        commonResult.setData("Good");
        return JsonPUtils.doJsonP(commonResult, callback);
    }

    /**
     *
     * @param request
     * @param commonResult
     * @param callback
     * @return
     */
    @RequestMapping("sendattach")
    public Object sendAttach(HttpServletRequest request, CommonResult commonResult, String callback) {
        mailService.sendAttachmentMail();
        commonResult.setData("Good");
        return JsonPUtils.doJsonP(commonResult, callback);
    }

    /**
     *
     * @param request
     * @param commonResult
     * @param callback
     * @return
     */
    @RequestMapping("sendinline")
    public Object sendInner(HttpServletRequest request, CommonResult commonResult, String callback) {
        mailService.sendInlineMediaMail();
        commonResult.setData("Good");
        return JsonPUtils.doJsonP(commonResult, callback);
    }
}
