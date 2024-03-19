package com.spring.aws.config;

import com.spring.aws.domain.Character;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class AwsLambdaConfig {

    @Bean
    public Filter getFilter(){
        return new SecurityFilter();
    }

    @Bean(name = "Saludar")
    public Supplier<String> greeting(){
        return () -> "Hello World";
    }

    @Bean
    public Consumer<String> printParam(){
        return (param) -> System.out.println(param);
    }

    @Bean
    public Function<String, String> receiveParam(){
        return (param) -> {
            String name = param.toUpperCase();
            return name;
        };
    }

    // Generate a JSON
    @Bean
    public Supplier<Map<String, String>> createCharacter(){
        return () -> {
            Map<String, String> character = new HashMap<>();
            character.put("name", "Goku");
            character.put("healthPoints", "100");
            character.put("skill", "Kame Hame Ha!");
            return character;
        };
    }

    // Receive a JSON and return String
    @Bean
    public Function<Map<String, Object>, String> receiveCharacter(){
        return (param) -> {
            param.forEach( (key, value) -> System.out.println(key + " - " + value) );
            return "Personaje recibido";
        };
    }

    // Receive an OBJECT and return an OBJECT
    @Bean
    public Function<Character, Character> receiveAnObject(){
        return (param) -> {
          return param;
        };
    }

    // Receive a JSON and return a JSON
    @Bean
    public Function<Map<String, Object>, Map<String, Object>> processCharacters(){
        return (param) -> {
            Map<String, Object> mapCharacter = param;

            mapCharacter.forEach( (key, value) -> System.out.println(key + " - " + value) );

            Map<String, Object> newCharacter = new HashMap<>();
            newCharacter.put("name", "Krillin");
            newCharacter.put("healthPoints", 70);
            newCharacter.put("skills", new String[]{"Ki en zan!", "Kame Hame Ha!"});

            return newCharacter;
        };
    }
}
