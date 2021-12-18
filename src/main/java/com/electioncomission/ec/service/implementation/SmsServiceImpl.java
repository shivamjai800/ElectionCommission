package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.entity.Users;
import com.electioncomission.ec.model.OtpResponse;
import com.electioncomission.ec.service.SmsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
@Slf4j
@PropertySource("classpath:application.yml")
public class SmsServiceImpl implements SmsService {
    private Environment environment;

    private final String USER_NAME;

    private final String WEB_TOKEN;

    private final String URL;

    private final String timeLimit;

    public SmsServiceImpl(final Environment environment) {
        this.environment = environment;
        USER_NAME = environment.getProperty("sms.service.username");
        WEB_TOKEN = environment.getProperty("sms.service.webToken");
        URL = environment.getProperty("sms.service.url");
        timeLimit = environment.getProperty("sms.otp.time");
    }

    @Override
    public String generateRandomOtp(int len)
    {
        String numbers = "0123456789";
        Random rndm_method = new Random();
        String otp = "123456";
        StringBuilder string = new StringBuilder(otp);


        for (int i = 0; i < len; i++)
        {
            string.setCharAt(i, numbers.charAt(rndm_method.nextInt(numbers.length())));
        }
        return string.toString();
    }

    @Override
    public String send(Users users) {

        String msg = users.getOtp()+"+is+your+One+Time+Verification+Code+for+logging+in+to+the+Absentee+Voter+Management+System.+The+verification+code+is+valid+for+"+timeLimit+"+minutes.+Do+not+share+this+verification+code+with+anyone.+-+Chief+Electoral+Officer,+Goa";
        try{

            String urlTemplate = UriComponentsBuilder.fromHttpUrl(URL)
                    .queryParam("app", "{app}")
                    .queryParam("u", "{u}")
                    .queryParam("h", "{h}")
                    .queryParam("op", "{op}")
                    .queryParam("to", "{to}")
                    .queryParam("msg", "{msg}")
                    .encode()
                    .toUriString();

            //params
            Map<String, String> parameters = new HashMap<>();
            parameters.put("app", "ws");
            parameters.put("u", USER_NAME);
            parameters.put("h", WEB_TOKEN);
            parameters.put("op", "pv");
            parameters.put("to", users.getMobileNumber());
            parameters.put("msg", msg);

            //headers
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setCacheControl(CacheControl.noCache());
            headers.setConnection("keep-alive");

            //request Entity
            HttpEntity requestEntity = new HttpEntity<>(headers);

            RestTemplate template = new RestTemplate();

            List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

            ObjectMapper objectMapper = new ObjectMapper()
                    .registerModule(new Jdk8Module())
                    .registerModule(new JavaTimeModule())
                    .configure(
                            SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
            converter.setObjectMapper(objectMapper);
            messageConverters.add(converter);
            template.setMessageConverters(messageConverters);

            ResponseEntity<Object> response = template.exchange(urlTemplate, HttpMethod.GET, requestEntity, Object.class, parameters);
            HttpStatus responseStatus = response.getStatusCode();
            ObjectMapper objectMapper1 = new ObjectMapper();
            LinkedHashMap<String,String>  responseBody = (LinkedHashMap<String, String>) response.getBody();
            OtpResponse otpResponse = objectMapper.convertValue(responseBody,OtpResponse.class);
            if(responseStatus.equals(HttpStatus.OK))
            {
                log.info("request  created successfully successfully ");
                log.debug(otpResponse.toString());
                return "SUCCESS";
            }
            else
            {
                log.error("Failed to send the otp");
                log.debug(otpResponse.toString());
                return otpResponse.getErrorString();
            }
        }
        catch (Exception exception)
        {
            log.error("exception in creating the request"+exception.getMessage());
            return "FAILED DUE TO EXCEPTION"+exception.getMessage();
        }
    }
}

//    @Value("${sms.service.url}")
//    private String URL;
//
//    @Value("${sms.service.username}")
//    private String USER_NAME;
//
//    @Value("${sms.service.webToken}")
//    private String WEB_TOKEN;
//
//    @Value("${sms.service.time}")
//    private String timeLimit;