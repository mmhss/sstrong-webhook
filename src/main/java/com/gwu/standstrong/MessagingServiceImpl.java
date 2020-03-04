package com.gwu.standstrong;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MessagingServiceImpl implements MessagingService {

    @Override
    public void sendMessage(String token, String callbackData) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(callbackData, headers);

        ResponseEntity<String> response = restTemplate
                .exchange(Constants.URL_SEND_POST, HttpMethod.POST, entity, String.class);

        log.info(response.toString());

    }
}
