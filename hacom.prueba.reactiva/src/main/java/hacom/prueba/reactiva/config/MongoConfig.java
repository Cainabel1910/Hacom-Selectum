package hacom.prueba.reactiva.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoDatabase;

import java.util.List;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class MongoConfig {
	
	@Bean
    public MongoClient reactiveMongoClient() {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromCodecs(new OffsetDateTimeCodec()) 
        );

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb://localhost:27017"))
                .codecRegistry(codecRegistry)
                .build();

        return MongoClients.create(settings);
    }

    @Bean
    public MongoDatabase reactiveMongoDatabase(MongoClient mongoClient) {
        return mongoClient.getDatabase("exampleDb");
    }
    
    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(List.of(
            new DateToOffsetDateTimeConverter(),
            new OffsetDateTimeToDateConverter()
        ));
    }
}
