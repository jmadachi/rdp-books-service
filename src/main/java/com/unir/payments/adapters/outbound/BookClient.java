package com.unir.payments.adapters.outbound;

import com.unir.payments.dtos.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookClient {

    @Value("${getBook.url}")
    private String getBookUrl;

    private final WebClient.Builder webClient;

    public BookDto getBookById(String id) {
        try {
            String url = String.format(getBookUrl, id);
            log.info("Calling rdp-books-service with URL: {}", url);

            return webClient.build()
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(BookDto.class)
                    .block();

        } catch (HttpClientErrorException e) {
            log.error("Client error {} while retrieving book with id {}", e.getStatusCode(), id);
        } catch (HttpServerErrorException e) {
            log.error("Server error {} while retrieving book with id {}", e.getStatusCode(), id);
        } catch (Exception e) {
            log.error("Unexpected error while retrieving book with id {}: {}", id, e.getMessage());
        }

        return null;
    }
}
