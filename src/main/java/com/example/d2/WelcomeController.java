package com.example.d2;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@RestController
public class WelcomeController {

   private static Map<String, String> helloMap;
   private static final String DEFAULT_HELLO = "Hello!";

   public WelcomeController() {
      helloMap = new HashMap<>();
      helloMap.put("FR", "Bonjour!");
      helloMap.put("ES", "Hola!");
      helloMap.put("DE", "Hallo!");
      helloMap.put("IT", "Ciao!");
      helloMap.put("RU", "Privet!");
      helloMap.put("JA", "Konnichiwa!");
      helloMap.put("KO", "Annyeong!");
      helloMap.put("ZH", "Ni hao!");
   }

   @GetMapping(value = "/", produces = MediaType.IMAGE_GIF_VALUE)
   public @ResponseBody byte[] showWelcome() throws IOException {
      InputStream in = getClass().getResourceAsStream("/giphy.gif");
      return IOUtils.toByteArray(in);
   }

   @GetMapping("/hello")
   public String sayWelcome(Locale locale) {
      if (Objects.nonNull(locale) && Objects.nonNull(locale.getLanguage())) {
         String key = locale.getLanguage().toUpperCase();
         if (helloMap.containsKey(key)) {
            return helloMap.get(key);
         }
      }
      return DEFAULT_HELLO;
   }
}
