package com.stalary.personfilter.client;

import com.stalary.personfilter.data.dto.ProjectInfo;
import com.stalary.personfilter.data.dto.User;
import com.stalary.personfilter.data.vo.ResponseMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户中心调用
 * @author Stalary
 * @description
 * @date 2018/12/28
 */
@FeignClient(name = "user", url = "${server.user}")
@Component
public interface UserCenterClient {

    /**
     * getProjectInfo 获取项目信息
     * @param name 项目名称
     * @param phone 手机号
     * @return 项目信息
     **/
    @GetMapping("/facade/project")
    ResponseMessage<ProjectInfo> getProjectInfo(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "phone") String phone);

    /**
     * updateInfo 更新用户信息
     * @param key 项目的key
     * @param user 用户信息
     * @return token
     **/
    @PostMapping("/token/update")
    ResponseMessage<String> updateInfo(
            @RequestParam(name = "key") String key,
            @RequestBody User user);

    /**
     * updatePassword 修改密码
     * @param key 项目的key
     * @param user 用户信息
     * @return token
     **/
    @PostMapping("/token/update/password")
    ResponseMessage<String> updatePassword(
            @RequestParam(name = "key") String key,
            @RequestBody User user);

    /**
     * register 用户注册
     * @param key 项目的key
     * @param user 用户信息
     * @return token
     **/
    @PostMapping("/token/register")
    ResponseMessage<String> register(
            @RequestParam(name = "key") String key,
            @RequestBody User user);

    /**
     * login 用户登陆
     * @param key 项目的key
     * @param user 用户信息
     * @return token
     **/
    @PostMapping("/token/login")
    ResponseMessage<String> login(
            @RequestParam(name = "key") String key,
            @RequestBody User user);

    /**
     * getUserInfo 获取用户信息
     * @param token token
     * @param key 项目的key
     * @return 用户信息
     **/
    @GetMapping("/facade/token")
    ResponseMessage<User> getUserInfo(
            @RequestParam(name = "token") String token,
            @RequestParam(name = "key") String key);

    /**
     * getUserInfoById 通过用户id获取用户信息
     * @param userId 用户id
     * @param key 项目的key
     * @param projectId 项目id
     **/
    @GetMapping("/facade/user")
    ResponseMessage<User> getUserInfoById(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "key") String key,
            @RequestParam(name = "projectId") Long projectId);
}
