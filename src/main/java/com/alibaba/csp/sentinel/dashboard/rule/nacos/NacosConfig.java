package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * ClassName: NacosConfig
 * Description:
 * date: 2022/7/21 17:36
 *
 * @author shudong.zhang<br />
 * @version * @since JDK 8
 */

@Configuration
@Slf4j
public class NacosConfig {

    @Autowired
    private NacosConfigProperties nacosConfigProperties;

    /**
     * 非常关键 这里将FlowRuleEntity转换成FlowRule才会对客户端生效
     *
     * @return FlowRule
     */
    @Bean
    public Converter<List<FlowRuleEntity>, String> flowRuleEntityEncoder() {
        return rules -> JSON.toJSONString(rules.stream().map(FlowRuleEntity::toRule).collect(Collectors.toList()), true);
    }

    @Bean
    public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, FlowRuleEntity.class);
    }

    /**
     * 非常关键 这里将DegradeRuleEntity转换成DegradeRule才会对客户端生效
     *
     * @return Json后的DegradeRule集合
     */
    @Bean
    public Converter<List<DegradeRuleEntity>, String> degradeRuleEntityEncoder() {
        return rules -> JSON.toJSONString(rules.stream().map(DegradeRuleEntity::toRule).collect(Collectors.toList()), true);
    }

    @Bean
    public Converter<String, List<DegradeRuleEntity>> degradeRuleEntityDecoder() {
        return s -> JSON.parseArray(s, DegradeRuleEntity.class);
    }

    @Bean
    public ConfigService nacosConfigService() throws Exception {
        Properties properties = new Properties();

        properties.put(PropertyKeyConst.SERVER_ADDR, nacosConfigProperties.getIp() + ":" + nacosConfigProperties.getPort());
        properties.put(PropertyKeyConst.NAMESPACE, nacosConfigProperties.getNamespace());
        properties.put(PropertyKeyConst.USERNAME, nacosConfigProperties.getUsername());
        properties.put(PropertyKeyConst.PASSWORD, nacosConfigProperties.getPassword());
        log.info("SERVER_ADDR:{}, NAMESPACE:{},USERNAME:{},PASSWORD:{}",
                nacosConfigProperties.getIp() + ":" + nacosConfigProperties.getPort(),
                nacosConfigProperties.getNamespace(),
                nacosConfigProperties.getUsername(),
                nacosConfigProperties.getPassword());
        return ConfigFactory.createConfigService(properties);
    }
}

