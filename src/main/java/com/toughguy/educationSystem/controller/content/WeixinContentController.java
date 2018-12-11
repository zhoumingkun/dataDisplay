package com.toughguy.educationSystem.controller.content;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

@Controller
@RequestMapping(value = "/weixinContent")
public class WeixinContentController {
	
	@ResponseBody	
	@RequestMapping(value = "/get")
	//@RequiresPermissions("account:save")
		public void getFirst(){
			String baseUrl = "http://weixin.sogou.com/weixin?type=1&ie=utf8&query=";//搜狗微信的基础地址
	    	String searchUrl = baseUrl + "gh_fae9c9be22b6";//加上安泰科现货价格的微信公总号
	    	Document document = getDocument(searchUrl);
	    	String listUrl = document.select(".tit a").attr("href");//查询到所有列表信息的url
	    	System.out.println(listUrl);
	    	System.out.println("-----------------------");
	    	Document doc = getDocument(listUrl);
	    	//截取前10条推送信息的json中的list数据
	    	String jsonList = doc.html().split("var msgList = ")[1].split("seajs.use")[0].trim();
	    	//截取出来第一条信息的url
	    	String url = jsonList.split("content_url\":\"")[1].split("\",\"copyright_stat")[0].replaceAll("amp;", "");
	    	if(url.startsWith("/s")){//如果url以/s开头，处理成http格式
	    		url = "http://mp.weixin.qq.com" + url;
	    	}
	    	System.out.println(url);
	    	//获取第一条推送信息的标题
	    	String title = jsonList.split("title\":\"")[1].split("\"},\"comm_msg_info")[0];
	    	System.out.println(title);
}
	
	/**
	 * 获取页面
	 * @author siqiangming 2018年5月9日 下午2:23:01
	 * @param url url
	 * @return
	 */
	public Document getDocument(String url){
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;

	}
}
