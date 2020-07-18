package com.example.restwithdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.jdbc.config.annotation.EnableRdsInstance;

@SpringBootApplication
@EnableRdsInstance(dbInstanceIdentifier="${cloud.aws.rds.dbInstanceIdentifier}", databaseName="${cloud.aws.rds.springaws.databaseName}",
		username="${cloud.aws.rds.springaws.username}", password="${cloud.aws.rds.springaws.password}")
public class RestwithdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestwithdataApplication.class, args);
	}

}
