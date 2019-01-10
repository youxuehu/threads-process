package com.gq.data.report.common.redis;

import com.gq.base.jedis.JedisHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wjw
 */
@Configuration
@EnableCaching
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig extends CachingConfigurerSupport {

	private String timeout;
	private String mastername;
	private String node1;
	private String node2;
	private String node3;

	/**
	 * 缓存管理器
	 * 
	 * @param config
	 * @return
	 */
	@Bean
	public JedisSentinelPool jedisPool(JedisPoolConfig config) {
		@SuppressWarnings("serial")
		Set<String> set = new HashSet<String>() {
			{
				add(node1);
				add(node2);
				add(node3);
			}
		};
		return new JedisSentinelPool(mastername, set, config, Integer.parseInt(timeout));
	}

	@Bean
	public JedisPoolConfig jedisPoolConfig(@Value("${spring.redis.pool.max-active}") int maxTotal,
			@Value("${spring.redis.pool.max-idle}") int maxIdle,
			@Value("${spring.redis.pool.max-wait}") int maxWaitMillis) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMaxWaitMillis(maxWaitMillis);
		return config;
	}

	@Bean
	public JedisHelper jedisHelper(JedisSentinelPool jedisPool) {
		return new JedisHelper(jedisPool);
	}
	
	public void setMastername(String mastername) {
		this.mastername = mastername;
	}

	public void setNode1(String node1) {
		this.node1 = node1;
	}

	public void setNode2(String node2) {
		this.node2 = node2;
	}

	public void setNode3(String node3) {
		this.node3 = node3;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
}
