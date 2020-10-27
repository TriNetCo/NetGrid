package org.njax.trinetco.netgrid.java.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// @SpringBootApplication // (scanBasePackages={"org.njax.trinetco.netgrid.java.app.models"})
@SpringBootApplication
// @ComponentScan(basePackages = {"org.njax.trinetco.netgrid.java.app.models.version", "org.njax.trinetco.netgrid.java.api"})
@ComponentScan(basePackages = {"org.njax.trinetco.netgrid.java.app.services.MyUserDetailsService"})
@EntityScan(basePackages= {"org.njax.trinetco.netgrid.java.app.models"})
@EnableJpaRepositories(basePackages= {"org.njax.trinetco.netgrid.java.app.models","org.njax.trinetco.netgrid.java.app.repositories"})
public class Main {

    public static void main(String[] args) {
        System.out.println( "Booting spring app..." );

        SpringApplication.run(Main.class, args);
    }

}
