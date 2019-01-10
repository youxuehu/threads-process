package com.gq.data.report.common.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wyq_tomorrow on 2017/12/4.
 */
@Configuration
public class RedissonConfig {
  @Value("${spring.redis.node}") 
  private String realUrls;
  @Value("${spring.redis.host}")
  private String devUrl;
  @Value("${spring.redis.masterName}")
  private String master;
  @Value("${spring.redis.database}")
  private int database;
  @Value("${spring.redis.isRealServer}")
  private boolean isRealServer;

  @Bean
  public RedissonClient redissonClient() {
    String[] urls = realUrls.split(",");
    Config config = new Config();
    if(isRealServer)
      config.useSentinelServers().setMasterName(master).setDatabase(database)
              .addSentinelAddress(urls).setSubscriptionConnectionPoolSize(25);
    else
      config.useSingleServer()
              .setAddress(devUrl)
              .setSubscriptionConnectionPoolSize(25)
              .setConnectionPoolSize(100)
              .setDatabase(13);

    RedissonClient client = Redisson.create(config);
    return client;
  }
}
