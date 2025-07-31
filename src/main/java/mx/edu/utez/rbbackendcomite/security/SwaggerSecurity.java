package mx.edu.utez.rbbackendcomite.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)


public class SwaggerSecurity {

    @Bean
    public OpenAPI config(){

        return new OpenAPI().info(new Info()
                .title("API Rest de Comite de eventos")
                .description("Documentacion de los endpoints del servicio de comite de eventos")
                .version("v1.0")
        );
    }

}
