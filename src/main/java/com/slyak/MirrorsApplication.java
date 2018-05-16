package com.slyak;

import com.slyak.spring.jpa.GenericJpaRepositoryFactoryBean;
import com.slyak.spring.jpa.GenericJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(
        basePackages = "com.slyak",
        repositoryBaseClass = GenericJpaRepositoryImpl.class,
        repositoryFactoryBeanClass = GenericJpaRepositoryFactoryBean.class
)
@EnableTransactionManagement
public class MirrorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MirrorsApplication.class, args);
    }
}