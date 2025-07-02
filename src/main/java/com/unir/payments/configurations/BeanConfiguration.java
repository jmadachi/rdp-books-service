package com.unir.payments.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		return mapper;
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Relatos de Papel - Pagos de Libros")
                        .version("1.0")
                        .description("API REST para la gestión de pagos de libros en el sistema Relatos de Papel.")
                        .contact(new Contact()
                                .name("Leilanys Ríos, Mauricio Adachi")
                                .email("leilanyjohana.rios431@comunidadunir.net, josemauricio.adachi188@comunidadunir.net")
                        )
                );
    }
}
