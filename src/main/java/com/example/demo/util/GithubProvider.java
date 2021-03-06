package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.dto.GithubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    //获取TOKEN
  public String getAccessToken(AccessTokenDTO accessTokenDTO){
     MediaType mediaType = MediaType.get("application/json; charset=utf-8");
      OkHttpClient client = new OkHttpClient();
          RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
          Request request = new Request.Builder()
                  .url("https://github.com/login/oauth/access_token")
                  .post(body)
                  .build();
          try (Response response = client.newCall(request).execute()) {
              String string=response.body().string();
             String tokenstr= string.split("&")[0];
             // System.out.println(string);
              return tokenstr;

          } catch (IOException e) {
              e.printStackTrace();
          }
        return null;
  }

  public GithubUserDTO getUser(String accessToken){
      //获取用户信息
      OkHttpClient client = new OkHttpClient();
          Request request = new Request.Builder()
                  .url("https://api.github.com/user?"+accessToken)
                  .build();
          try (Response response = client.newCall(request).execute()) {
              String string=response.body().string();
              return   JSON.parseObject(string,GithubUserDTO.class);
          } catch (IOException e) {
              e.printStackTrace();
          }
        return  null;
  }
}
