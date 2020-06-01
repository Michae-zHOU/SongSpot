package server.songspotserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SongspotserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SongspotserverApplication.class, args);
    }

}
