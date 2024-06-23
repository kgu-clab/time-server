package page.time.api;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TimeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeServerApplication.class, args);
	}

}
