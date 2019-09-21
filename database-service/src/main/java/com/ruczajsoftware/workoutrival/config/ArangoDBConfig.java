package com.ruczajsoftware.workoutrival.config;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.AbstractArangoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableArangoRepositories(basePackages = { "com.ruczajsoftware.workoutrival" })
public class ArangoDBConfig extends AbstractArangoConfiguration {
	@Override
	public ArangoDB.Builder arango() {
		return new ArangoDB.Builder()
			.host("localhost", 8529)
			.user("root")
			.password("root");
	}

	@Override
	public String database() {
		return "WorkoutRivalDB";
	}
}
