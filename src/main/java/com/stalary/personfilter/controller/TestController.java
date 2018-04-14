package com.stalary.personfilter.controller;

import com.stalary.personfilter.data.dto.ResponseMessage;
import com.stalary.personfilter.data.entity.Resume;
import com.stalary.personfilter.service.ResumeService;
import com.stalary.personfilter.service.SkillService;
import com.stalary.personfilter.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * TestController
 *
 * @author lirongqian
 * @since 2018/04/09
 */
@RestController
public class TestController {

    @Autowired
    private WebClientService webClientService;

    @Value("${server.user}")
    private String userCenterServer;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private StringRedisTemplate redis;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/hello")
    public ResponseMessage hello() {
        webClientService.getProjectInfo();
        return ResponseMessage.successMessage(userCenterServer);
    }

    @GetMapping("/projectInfo")
    public ResponseMessage projectInfo() {
        return ResponseMessage.successMessage(redis.opsForValue().get("project"));
    }

    @RequestMapping("/mongodb")
    public ResponseMessage mongodb(
            @RequestParam String name) {
        return ResponseMessage.successMessage(skillService.findResumeByName(name));
    }
}