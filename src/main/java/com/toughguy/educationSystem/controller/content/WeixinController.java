package com.toughguy.educationSystem.controller.content;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/wechat")
@Slf4j
public class WeixinController {
 
    @Autowired
    private WxMpService wxMpService;

    
	
	@ResponseBody
	@RequestMapping(value = "/getOpenId", method = RequestMethod.GET)//此处填自己要用到的项目名。
	 public static String getOpenId(@RequestParam(value="code",required=false)String code) {//接收用户传过来的code，required=false表明如果这个参数没有传过来也可以。
		  //接收从客户端获取的code
		  //向微信后台发起请求获取openid的url
		  String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
	           //这三个参数就是之后要填上自己的值。
	      //替换appid，appsecret，和code
	      String requestUrl = WX_URL.replace("APPID", "wx05dd96e3e0d5a7fb").//填写自己的appid
	        replace("SECRET", "d6aae1ffc685b60fbc7a8b848514108f").replace("JSCODE", code).//填写自己的appsecret，
	        replace("authorization_code", "authorization_code");
		   
	       //调用get方法发起get请求，并把返回值赋值给returnvalue
	         String  returnvalue=GET(requestUrl);
	         System.out.println(requestUrl);//打印发起请求的url
//	         System.out.println(returnvalue);//打印调用GET方法返回值
	         //定义一个json对象。 
	         JSONObject convertvalue=new JSONObject();
	      
	         //将得到的字符串转换为json
	         convertvalue=(JSONObject) JSONObject.fromObject(returnvalue);
//	         JSONObject convertvalue = JSONObject.parseObject(requestBody);
	 
	       System.out.println("return openid is ："+(String)convertvalue.get("openid")); //打印得到的openid
//	       System.out.println("return sessionkey is ："+(String)convertvalue.get("session_key"));//打印得到的sessionkey，
	       //把openid和sessionkey分别赋值给openid和sessionkey
	       String openid=(String) convertvalue.get("openid");
	       String sessionkey=(String) convertvalue.get("session_key");//定义两个变量存储得到的openid和session_key.
	 
	       return openid;//返回openid
	 }
	        //发起get请求的方法。
		public static String GET(String url) {
			String result = "";
			BufferedReader in = null;
			InputStream is = null;
			InputStreamReader isr = null;
			try {
				URL realUrl = new URL(url);
				URLConnection conn = realUrl.openConnection();
				conn.connect();
				Map<String, List<String>> map = conn.getHeaderFields();
				is = conn.getInputStream();
				isr = new InputStreamReader(is);
				in = new BufferedReader(isr);
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			} catch (Exception e) {
				// 异常记录
			} finally {
				try {
					if (in != null) {
						in.close();
					}
					if (is != null) {
						is.close();
					}
					if (isr != null) {
						isr.close();
					}
				} catch (Exception e2) {
					// 异常记录
				}
			}
			return result;
		}


//	@GetMapping("/userInfo")
//    public String userInfo(@RequestParam("code") String code) throws Exception {
//        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
//        try {
//            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
//        } catch (WxErrorException e) {
//        	throw new Exception(e.getError().getErrorMsg());
//        }
//        String openId = wxMpOAuth2AccessToken.getOpenId();
//        System.out.println(openId);
//        return openId;
//    }
	
	
	
}


