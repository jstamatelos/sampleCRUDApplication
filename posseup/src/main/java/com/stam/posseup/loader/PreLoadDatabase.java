package com.stam.posseup.loader;

import com.stam.posseup.entity.Member;
import com.stam.posseup.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PreLoadDatabase {

    @Bean
    CommandLineRunner initDatabase(MemberRepository repository) {
        return args -> {
            repository.save(new Member("Blackout Chalie", "Leader"));
            repository.save(new Member("Double Barrel Barclay", "Member"));
            repository.save(new Member("Buster Scruggs", "Prospect"));
        };
    }

}
