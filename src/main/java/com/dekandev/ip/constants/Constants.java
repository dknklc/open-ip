package com.dekandev.ip.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static String API_URL;
    public static String API_KEY;

    @Value("${ip-stack.api-url}")
    public void setApiUrl(String apiUrl) {
        Constants.API_URL = apiUrl;
    }

    @Value("${ip-stack.api-key}")
    public void setApiKey(String apiKey) {
        Constants.API_KEY = apiKey;
    }
}
