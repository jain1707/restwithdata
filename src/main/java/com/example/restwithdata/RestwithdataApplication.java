package com.example.restwithdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.cache.ElastiCacheAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.cloud.aws.cache.config.annotation.CacheClusterConfig;
import org.springframework.cloud.aws.cache.config.annotation.EnableElastiCache;
import org.springframework.cloud.aws.jdbc.config.annotation.EnableRdsInstance;

@SpringBootApplication(exclude = {
		ContextStackAutoConfiguration.class,
		ElastiCacheAutoConfiguration.class
})
@EnableRdsInstance(dbInstanceIdentifier="${cloud.aws.rds.dbInstanceIdentifier}", databaseName="${cloud.aws.rds.springaws.databaseName}",
		username="${cloud.aws.rds.springaws.username}", password="${cloud.aws.rds.springaws.password}")
//@EnableElastiCache({@CacheClusterConfig(name = "myfirstcache", expiration = 600)})
public class RestwithdataApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestwithdataApplication.class, args);
	}

}
