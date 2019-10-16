package com.toughguy.dataDisplay.controller.captcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sun.misc.BASE64Encoder; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the"License"); 
 * you may not use this file except in compliance with the License.  
 * You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
 * See the License for the specific language governing permissions and limitations under the License.
 * Copyright [2017] [RanJi] [Email-jiran1221@163.com]
 * 
 * 产生验证码的控制器
 * @author RanJi
 * @date 2017-9-14
 * @since JDK1.7
 * @version 1.0
 */

@Controller
public class CaptchaResource {
	@Autowired
	private Producer captchaProducer;

/*	@RequestMapping("/captcha")
	public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		response.setDateHeader("Expires", 0);
		//-- Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		//-- Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		//-- Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		//-- return a jpeg
		response.setContentType("image/jpeg");
		//-- create the text for the image
		String capText = captchaProducer.createText();
		//-- store the text in the session
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
		//-- create the image with the text
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		
		//-- write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
	}*/
	
	@ResponseBody  
    @RequestMapping(value = "/captcha", method = RequestMethod.POST)  
    public Map<String, Object> captcha(HttpServletResponse response) throws ServletException, IOException {  
  
        // 生成文字验证码  
  
        String text = captchaProducer.createText();  
        // 生成图片验证码  
        ByteArrayOutputStream outputStream = null;   
        BufferedImage image = captchaProducer.createImage(text);  
          
        outputStream = new ByteArrayOutputStream();    
        ImageIO.write(image, "jpg", outputStream);    
          
        // 对字节数组Base64编码    
        BASE64Encoder encoder = new BASE64Encoder();
          
        // 生成captcha的token  
        Map<String, Object> map = new HashMap<>();  
        map.put("img", encoder.encode(outputStream.toByteArray()));  
        map.put("captcha", text);  
        return map;  
    }  
}
