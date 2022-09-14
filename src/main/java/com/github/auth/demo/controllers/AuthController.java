package com.github.auth.demo.controllers;

import com.github.auth.demo.viewModels.GetTokenResponseType;
import net.minidev.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("api/github")
public class AuthController {

    @GetMapping("/token")
    public ResponseEntity<String> githubToken(@RequestParam String code) throws IOException {
        String url = "https://github.com/login/oauth/access_token";
        System.out.println("THE CODE FROM FRONT SIDE IS :: " + code);
        GetTokenResponseType response;
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject personJsonObject = new JSONObject();
            personJsonObject.put("client_id", "f59b6fd23be980b1dadb");
            personJsonObject.put("client_secret", "9173d8f3f3afd20f0ad966134012799279754f92");
            personJsonObject.put("code", code);
            personJsonObject.put("redirect_uri", "http://localhost:3000/login");
            response = restTemplate.postForObject(url, personJsonObject, GetTokenResponseType.class);
            System.out.println("RESPONSE FROM API IS :: " + response);

            RestTemplate restTemplateUser = new RestTemplate();
            HttpHeaders userHeaders = new HttpHeaders();
            userHeaders.setContentType(MediaType.APPLICATION_JSON);
            userHeaders.set("Authorization", "token " + response.getAccess_token());
            HttpEntity<String> request = new HttpEntity<>(userHeaders);
            // make an HTTP GET request with headers
            ResponseEntity<String> userResponse = restTemplateUser.exchange(
                    "https://api.github.com/user",
                    HttpMethod.GET,
                    request,
                    String.class,
                    1
            );
            return userResponse;
        } catch (Exception ex) {
            System.out.println("EXCEPTION :: " + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    @GetMapping("/user")
    public ResponseEntity<String> getUser(@RequestParam String token) throws IOException {
        String url = "https://api.github.com/user";

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "token " + token);
            HttpEntity<String> request = new HttpEntity<>(headers);
            // make an HTTP GET request with headers
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    String.class,
                    1
            );
            return response;
        } catch (Exception ex) {
            System.out.println("EXCEPTION :: " + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    @GetMapping("/repos")
    public ResponseEntity<String> getRepositories(@RequestParam String token) throws IOException {
        String url = "https://api.github.com/user/repos";

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "token " + token);
            HttpEntity<String> request = new HttpEntity<>(headers);
            // make an HTTP GET request with headers
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    String.class,
                    1
            );
            return response;
        } catch (Exception ex) {
            System.out.println("EXCEPTION :: " + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }
}
