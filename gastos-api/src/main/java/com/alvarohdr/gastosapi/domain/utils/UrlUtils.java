package com.alvarohdr.gastosapi.domain.utils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

public class UrlUtils {
    private final static String DICTIONARY = "0123456789ABCDEF";
    private final static int URI_LENGTH = 7;

    public static boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }
    public static String crateUri(){
        StringBuilder uri = new StringBuilder();
        for(int i = 0; i < URI_LENGTH; i++){
            Random random = new Random();
            uri.append(DICTIONARY.charAt(random.nextInt(DICTIONARY.length())));
        }
        return uri.toString();
    }
}
