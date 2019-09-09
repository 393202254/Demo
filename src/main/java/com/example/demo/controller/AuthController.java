package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.dto.GithubUserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.util.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientID);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_url(redirectUrl);
        accessTokenDTO.setState(state);
        String accesstoken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUserDTO user = githubProvider.getUser(accesstoken);
        User user1 = new User();
        user1.setAccountId(String.valueOf(user.getId()));
        user1.setCreateTime(System.currentTimeMillis());
        user1.setName(user.getName());
        user1.setToken(UUID.randomUUID().toString());
        user1.setAvatarUrl(user.getAvatarUrl());
        userMapper.insert(user1);
        if (user != null) {
            httpServletResponse.addCookie(new Cookie("token", user1.getToken()));

            return "redirect:/";
        } else {
            return "redirect:/";
        }

    }
}
