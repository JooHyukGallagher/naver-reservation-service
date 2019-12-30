package me.weekbelt.naverreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NaverReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NaverReservationApplication.class, args);
    }

}
