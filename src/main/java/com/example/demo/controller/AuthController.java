package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.dto.GithubUserDTO;
import com.example.demo.util.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${AccessToken.client.id}")
   private String clientID;
    @Value("${AccessToken.client.secret}")
   private String clientSecret;
    @Value("${AccessToken.client.url}")
   private String redirectUrl;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name="code")String code,@RequestParam(name="state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientID);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_url(redirectUrl);
        accessTokenDTO.setState(state);
        String accesstoken=githubProvider.getAccessToken(accessTokenDTO);
        GithubUserDTO user=githubProvider.getUser(accesstoken);
        System.out.println(user.getName());
        System.out.println(user.getId());
        return "index";
    }
}
