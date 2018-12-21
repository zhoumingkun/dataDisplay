package com.toughguy.educationSystem.controller.content;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.toughguy.educationSystem.model.content.Material;
import com.toughguy.educationSystem.model.content.MaterialParam;
import com.toughguy.educationSystem.util.CommonUtil;
import com.toughguy.educationSystem.util.HttpsUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/wechat")
@Slf4j
public class WeixinController {
    
    /**
	 * 微信小程序获取openId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getOpenId", method = RequestMethod.GET)//此处填自己要用到的项目名。
	//@RequiresPermissions("/wechat:getOpenId")
	 public static String getOpenId(@RequestParam(value="code",required=false)String code) {//接收用户传过来的code，required=false表明如果这个参数没有传过来也可以。
		  //接收从客户端获取的code
		  //向微信后台发起请求获取openid的url
		  String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
	           //这三个参数就是之后要填上自己的值。
	      //替换appid，appsecret，和code
	      String requestUrl = WX_URL.replace("APPID", "wx97731b21bc479485").//填写自己的appid
	        replace("SECRET", "8d6fe3ed2c65892c383c6b236537ca0f").replace("JSCODE", code).//填写自己的appsecret，
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
		 /**
		 * 微信小程序获取微信公众号文章
		 * @return
		 */
		@ResponseBody	
		@RequestMapping(value = "/getToken")
		//@RequiresPermissions("weixinContent:getToken")
		public  String getToken() throws Exception {
		String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	    //appid
	    String APPID = "wxff1f016244a86d99";
	    //appsecret
	    String APPSECRET = "978de69900cf3c35570622fefb5ea06a";        
	    String request_url = TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
	    HttpsUtil httpsUtil = new HttpsUtil();
	    System.out.println(request_url);
	    int i = 0;
	        JSONObject jsonObject = httpsUtil.HttpsUtil(request_url,"GET",null);
	        if(null != jsonObject){
	            String access_tocken = jsonObject.getString("access_token");
	            String expires_in = jsonObject.getString("expires_in");
	            //获取到的access_tocken值可以写入到文本文件中供其他业务逻辑调用，实例中只打印了没有保存到文件
	            System.out.println("获取成功"+"access_tocken="+access_tocken+"||expires_in="+expires_in);  
	            i=Integer.parseInt(expires_in);
	            return access_tocken; 
	       
	        //休眠1小时57分钟，提前三分钟获取新的access_token
//	        sleep((7200-180)*1000);
	    }
			return request_url;                
	}
		@ResponseBody	
		@RequestMapping(value = "/getContent")
		//@RequiresPermissions("weixinContent:getContent")
		public static List<Material> getMaterialList(String ACCESS_TOKEN,String type ,String offset, String count) throws Exception {
			String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
//			String ACCESS_TOKEN = "16_QsdYWJqGge2bIMaAkp7faaNqsm-mONl23_Z-Q490YBM2V4CIjpG3gSIAnCkGbrMDP4xS8ZD077oHglscdOAgexsBRJN6iy4gVP0uWDhH_OYEfwrwkX1Q6UAzhO0OrRSfGZ7iDhhak8AwW2lwBWTjAIARMJ";
			String request_url = TOKEN_URL.replace("ACCESS_TOKEN", ACCESS_TOKEN);
	        String outputStr = "";
	        HttpsUtil httpsUtil = new HttpsUtil();
	        System.out.println(request_url);
	        List<Material> lists = new ArrayList<Material>();//定义图文素材实体类集合
	        //POST请求发送的json参数
	        MaterialParam para = new MaterialParam();//调用接口所需要的参数实体类
	        para.setType("news");
	        para.setOffset(offset);
	        para.setCount(count);
	        
	        JSONObject jsonObject = new JSONObject();
	        jsonObject = JSONObject.fromObject(para);
	        outputStr = jsonObject.toString();//将参数对象转换成json字符串
	        System.out.println(jsonObject);

	        jsonObject = CommonUtil.httpsRequest(request_url, "POST", outputStr);  //发送https请求(请求的路径,方式,所携带的参数);
	    // 如果请求成功  
	    		if (null != jsonObject) {
	    		try {  
	    		JSONArray jsonArray = jsonObject.getJSONArray("item");
	    		for (int i = 0; i < jsonArray.size(); i++) {
	    		JSONObject json = (JSONObject) jsonArray.get(i);
	    		json = json.getJSONObject("content");
	    		System.out.println(json);
	    		
	    		JSONArray arr = json.getJSONArray("news_item");
	    		json = (JSONObject) arr.get(0);
	    		
	    		Material material = new Material();
	    		String title = json.getString("title");
	    		String author = json.getString("author");
	    		String digest = json.getString("digest");
	    		String thumb_media_id = json.getString("thumb_media_id");
	    		String thumb_url = json.getString("thumb_url");
	    		System.out.println(thumb_media_id);
	    		String url = json.getString("url");
	    		String content = json.getString("content");
	    		material.setTitle(title);
	    		material.setAuthor(author);
	    		material.setDigest(digest);
	    		material.setThumb_media_id(thumb_media_id);
	    		material.setThumb_url(thumb_url);
	    		material.setUrl(url);
	    		material.setContent(content);
	    		material.setShow_cover_pic(1);
	    	    lists.add(material);
	    		}
	    		} catch (JSONException e) {  
//	    		accessToken = null;  
	    		// 获取Material失败  
	    		System.out.println("获取Material失败");  
	    		}  
	    		}  
	    		return lists;  
	    		} 

}


