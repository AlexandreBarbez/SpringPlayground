package com.abrbz.SpringPlayground;

import com.abrbz.SpringPlayground.user.Utilisateur;
import com.abrbz.SpringPlayground.user.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UtilisateurRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Utilisateur("Bilbo Baggins", "burglar@mail")));
            log.info("Preloading " + repository.save(new Utilisateur("Frodo Baggins", "thief@mail")));
        };
    }
}
