package com.stalary.personfilter.controller;

import com.stalary.lightmqclient.facade.Producer;
import com.stalary.personfilter.data.entity.mysql.Company;
import com.stalary.personfilter.data.vo.ResponseMessage;
import com.stalary.personfilter.service.ClientService;
import com.stalary.personfilter.service.WebSocketService;
import com.stalary.personfilter.service.mongodb.ResumeService;
import com.stalary.personfilter.service.mysql.CompanyService;
import com.stalary.personfilter.service.outer.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import static com.stalary.personfilter.utils.Constant.NOTIFY;

/**
 * TestController
 *
 * @author lirongqian
 * @since 2018/04/09
 */
@RestController
@ApiIgnore
public class TestController {

    @Autowired
    private ClientService clientService;

    @Value("${server.user}")
    private String userCenterServer;


    @Autowired
    private StringRedisTemplate redis;


    @Autowired
    private CompanyService companyService;

    @Autowired
    private Producer producer;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private MailService mailService;

    @Autowired
    private WebSocketService webSocketService;

    @GetMapping("/hello")
    public ResponseMessage hello() {
        clientService.getProjectInfo();
        return ResponseMessage.successMessage(userCenterServer);
    }

    @GetMapping("/projectInfo")
    public ResponseMessage projectInfo() {
        return ResponseMessage.successMessage(redis.opsForValue().get("project"));
    }

    @PostMapping("/mysql")
    public ResponseMessage mysql(
            @RequestBody Company company) {
        return ResponseMessage.successMessage(companyService.save(company));
    }

    @GetMapping("/kafka")
    public ResponseMessage kafka(
            @RequestParam String message) {
        producer.send(NOTIFY, message);
        return ResponseMessage.successMessage();
    }

    @GetMapping("/websocket")
    public ResponseMessage websocket(
            @RequestParam Long userId,
            @RequestParam String message) {
        webSocketService.sendMessage(userId, message);
        return ResponseMessage.successMessage();
    }

    @GetMapping("/calculate")
    public ResponseMessage calculate(
            @RequestParam Long userId,
            @RequestParam Long recruitId) {
        return ResponseMessage.successMessage(resumeService.calculate(recruitId, userId));
    }

    @GetMapping("/mail")
    public ResponseMessage mail() {
        mailService.sendResume("stalary@163.com", "测试邮件");
        return ResponseMessage.successMessage();
    }

}