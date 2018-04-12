package io.radanalytics;

import javax.annotation.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Spring Boot app that init the spark context
 */
@SpringBootApplication
public class App {

    @Autowired
    private Properties properties;

    @PostConstruct
    public void init()
    {
        if (!ContextProvider.init(properties)) {

            System.exit(1);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}