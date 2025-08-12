package com.hyuuny.trelibrary.init;

import com.hyuuny.trelibrary.popularsearchterms.domain.PopularSearchTerm;
import com.hyuuny.trelibrary.popularsearchterms.domain.PopularSearchTermRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PopularSearchTermDataInitializer implements CommandLineRunner {

    private final PopularSearchTermRepository popularSearchTermRepository;

    public PopularSearchTermDataInitializer(PopularSearchTermRepository popularSearchTermRepository) {
        this.popularSearchTermRepository = popularSearchTermRepository;
    }

    @Override
    public void run(String... args) {
        if (popularSearchTermRepository.count() > 0) return;

        List<PopularSearchTerm> terms = List.of(
                new PopularSearchTerm("Java", 150L),
                new PopularSearchTerm("Spring", 120L),
                new PopularSearchTerm("React", 110L),
                new PopularSearchTerm("Docker", 90L),
                new PopularSearchTerm("Kubernetes", 80L),
                new PopularSearchTerm("MongoDB", 70L),
                new PopularSearchTerm("Kafka", 60L),
                new PopularSearchTerm("AWS", 55L),
                new PopularSearchTerm("Microservices", 50L),
                new PopularSearchTerm("TypeScript", 45L),
                new PopularSearchTerm("Hibernate", 40L),
                new PopularSearchTerm("JUnit", 38L),
                new PopularSearchTerm("GraphQL", 35L),
                new PopularSearchTerm("Redis", 33L),
                new PopularSearchTerm("ElasticSearch", 30L),
                new PopularSearchTerm("OAuth2", 28L),
                new PopularSearchTerm("JUnit5", 25L),
                new PopularSearchTerm("Spring Boot", 23L),
                new PopularSearchTerm("AWS Lambda", 20L),
                new PopularSearchTerm("Vue.js", 18L)
        );

        popularSearchTermRepository.saveAll(terms);
    }
}

