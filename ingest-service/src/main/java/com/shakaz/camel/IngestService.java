package com.shakaz.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IngestService {

    /*@Bean
    public CommandLineRunner demo(SampleRepo sampleRepo) {
        return args -> {

            sampleRepo.save(new SampleEntity("name1"));
            sampleRepo.save(new SampleEntity("name2"));
            sampleRepo.save(new SampleEntity("name3"));

            SampleEntity se = sampleRepo.findByName("name1").get(0);
            int i = 0;
        };
    }*/

    public static void main(String [] args) {
        SpringApplication.run(IngestService.class, args);
    }
}
