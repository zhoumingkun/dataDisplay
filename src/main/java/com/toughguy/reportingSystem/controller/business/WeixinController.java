package com.toughguy.reportingSystem.controller.business;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

 
@RestController
@RequestMapping("/wechat")
@Slf4j
public class WeixinController {
 
    @Autowired
    private WxMpService wxMpService;
 
//    @GetMapping("/authorize")
//    public String authorize(@RequestParam("returnUrl") String returnUrl){
//        String url = "http://localhost:8082/reportingSystem/wechat/userInfo";
//        String redirectURL = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
//        return "redirect:" + redirectURL;
//    }
 
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code) throws Exception {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
        	throw new Exception(e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        System.out.println(openId);
        return openId;
    }
}


