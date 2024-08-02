package page.time.api.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public OpenAPI springBootAPI() {
        Info info = new Info()
                .title("Time Page")
                .description("경기대 Time Page API Document")
                .contact(new Contact().name("전민주").url("https://github.com/mingmingmon").email("mingmingmon@naver.com"))
                .license(new License().name("Time Page License Version 1.0").url("https://github.com/KGU-C-Lab/time-server"));

        Server server = new Server().url("/");

        return new OpenAPI()
                .servers(List.of(server))
                .info(info);
    }

}
