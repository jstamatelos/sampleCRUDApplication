package com.stam.posseup.loader;

import com.stam.posseup.entity.Member;
import com.stam.posseup.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PreLoadDatabase {

    public static final Logger logger = LoggerFactory.getLogger(PreLoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(MemberRepository repository) {
        return args -> {
            logger.info("Loading member ..." + repository.save(new Member("Blackout Chalie", "Leader")));
            logger.info("Loading member ..." + repository.save(new Member("Double Barrel Barclay", "Member")));
            logger.info("Loading member ..." + repository.save(new Member("Buster Scruggs", "Prospect")));
        };
    }

}
