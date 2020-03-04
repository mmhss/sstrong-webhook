package com.gwu.standstrong;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public String login(String username, String password) throws ParseException {

        JSONObject payload = new JSONObject();
        payload.put("username", username);
        payload.put("password", password );

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(payload.toJSONString(), headers);

        ResponseEntity<String> response = restTemplate
                .exchange(Constants.URL_SIGN_IN, HttpMethod.POST, entity, String.class);

        log.info(response.toString());

        return JSONUtils.parse(response.getBody(), "token");
    }
}
