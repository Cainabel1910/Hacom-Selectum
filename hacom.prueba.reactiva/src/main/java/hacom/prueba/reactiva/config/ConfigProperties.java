package hacom.prueba.reactiva.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class ConfigProperties {
	private String uri;
    private String database;
}
