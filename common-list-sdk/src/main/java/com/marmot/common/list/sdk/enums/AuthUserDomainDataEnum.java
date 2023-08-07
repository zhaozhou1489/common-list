package com.marmot.common.list.sdk.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author:zhaozhou
 * @Date: 2023/08/02
 * @Desc: 用户权限相关的领域数据
 */
public enum AuthUserDomainDataEnum {
    USER("user"),
    ROLES("roles"),
    USER_RESOURCES("user_resources"),
    USER_ROLES_RESOURCES("user_roles_resources"),
    ;
    private String domain;

    public String getDomain() {
        return domain;
    }

    AuthUserDomainDataEnum(String domain) {
        this.domain = domain;
    }

    public static AuthUserDomainDataEnum of(String domain) {
        return StringUtils.isBlank(domain) ? null : Arrays.stream(AuthUserDomainDataEnum.values()).filter(d -> d.getDomain().equals(domain)).findFirst().orElse(null);
    }

    public static Set<String> allDomains(){
        return Arrays.stream(AuthUserDomainDataEnum.values()).map(d -> d.getDomain()).collect(Collectors.toSet());
    }
}
