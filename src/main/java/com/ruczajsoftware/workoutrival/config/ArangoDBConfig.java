package com.ruczajsoftware.workoutrival.config;

import com.arangodb.ArangoDB;
import com.arangodb.entity.CollectionEntity;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.ArangoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableArangoRepositories(basePackages = {"com.ruczajsoftware.workoutrival"})
public class ArangoDBConfig implements ArangoConfiguration {

    private static final String DB_NAME = "workout-rival-db";
    private static final Set<String> COLLECTIONS = Set.of("Users", "Exercises");

    @Value("${arangodb.host}")
    private String host;

    @Value("${arangodb.port}")
    private Integer port;


    @Bean
    public ArangoDB.Builder arango() {
        return new ArangoDB.Builder()
                .host(host, port)
                .user("root")
                .password("root");
    }

    @Bean
    public String database() {
        return DB_NAME;
    }

    @PostConstruct
    public void initCollections() {
        ArangoDB arangoDB = arango().build();
        if (!arangoDB.getDatabases().contains(DB_NAME)) {
            arangoDB.createDatabase(DB_NAME);
        }
        System.out.println("Databases");
        arangoDB.getDatabases().forEach(System.out::println);
        COLLECTIONS.forEach(
        		s -> {
        			if (!arangoDB.db(DB_NAME).getCollections().stream().map(CollectionEntity::getName).collect(Collectors.toList()).contains(s)) {
						arangoDB.db(DB_NAME).createCollection(s);
					}
				}
		);
		System.out.println("Collections");
		arangoDB.db(DB_NAME).getCollections().forEach(collectionEntity -> System.out.println(collectionEntity.getName()));
    }
}
