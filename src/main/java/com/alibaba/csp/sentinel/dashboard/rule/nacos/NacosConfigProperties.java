package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName: NacosConfigProperties
 * Description:
 * date: 2022/7/22 11:22
 *
 * @author shudong.zhang<br />
 * @version * @since JDK 8
 */

@Component
@ConfigurationProperties(prefix = "nacos.server")
@Data
public class NacosConfigProperties {

    private String ip;

    private String port;

    private String namespace;

    private String groupId;

    private String username;

    private String password;


}
