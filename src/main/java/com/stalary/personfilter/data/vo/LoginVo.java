/**
 * @(#)LoginVo.java, 2018-12-29.
 *
 * Copyright 2018 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stalary.personfilter.data.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LoginVo
 * @description 登陆对象
 * @field token 登陆返回的token
 * @field role 角色
 * @field userId 用户id
 * @field companyId 关联公司id
 * @author lirongqian
 * @since 2018/12/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {

    private String token;

    private Integer role;

    private Long userId;

    private Long companyId;
}