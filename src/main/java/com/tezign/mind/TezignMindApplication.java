package com.tezign.mind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = "com.tezign.mind.repository")
@EntityScan(basePackages = "com.tezign.mind.entity")
@EnableSwagger2
public class TezignMindApplication {

    public static void main(String[] args) {
        SpringApplication.run(TezignMindApplication.class, args);
    }
}