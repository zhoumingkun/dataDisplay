package com.toughguy.educationSystem.controller.content;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.educationSystem.model.content.Material;
import com.toughguy.educationSystem.model.content.MaterialImage;
import com.toughguy.educationSystem.model.content.MaterialParam;
import com.toughguy.educationSystem.util.CommonUtil;
import com.toughguy.educationSystem.util.HttpsUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/weixinContent")
public class WeixinContentController {
	

	@ResponseBody	
	@RequestMapping(value = "/getToken")
	//@RequiresPermissions("account:save")
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
//        sleep((7200-180)*1000);
    }
		return request_url;                
}
	@ResponseBody	
	@RequestMapping(value = "/getContent")
	//@RequiresPermissions("account:save")
	public static List<Material> getMaterialList(String ACCESS_TOKEN,String type ,String offset, String count) throws Exception {
		String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
//		String ACCESS_TOKEN = "16_QsdYWJqGge2bIMaAkp7faaNqsm-mONl23_Z-Q490YBM2V4CIjpG3gSIAnCkGbrMDP4xS8ZD077oHglscdOAgexsBRJN6iy4gVP0uWDhH_OYEfwrwkX1Q6UAzhO0OrRSfGZ7iDhhak8AwW2lwBWTjAIARMJ";
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
//    		accessToken = null;  
    		// 获取Material失败  
    		System.out.println("获取Material失败");  
    		}  
    		}  
    		return lists;  
    		}  
}
