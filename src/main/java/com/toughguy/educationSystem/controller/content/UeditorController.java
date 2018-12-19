package com.toughguy.educationSystem.controller.content;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.util.encoders.BufferedDecoder;
import org.json.JSONException;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.toughguy.educationSystem.ueditor.ActionEnter;


@RestController
@CrossOrigin
@RequestMapping("/ueditor")
public class UeditorController {
	
	@RequestMapping(value="/exec")
	@ResponseBody
	public String exec(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String rootPath = request.getRealPath("/");
		System.out.println(rootPath);
		try {
			return new ActionEnter(request, rootPath).exec();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
//	@RequestMapping("/addNews.do")
//    public String addNews(String content) {
//
//        System.out.println(content);
//        return "ok";
//    }
//	
//	@RequestMapping("/uploadimage")
//    @ResponseBody
//    public Map<String, Object> images (MultipartFile upfile, HttpServletRequest request, HttpServletResponse response){
//        Map<String, Object> params = new HashMap<String, Object>();
//        System.out.println("1111111111111");
//        try{
//            String basePath = "E:/项目";  //与properties文件中lyz.uploading.url相同，未读取到文件数据时为basePath赋默认值
//            String  visitUrl = "/upload/"; //与properties文件中lyz.visit.url相同，未读取到文件数据时为visitUrl赋默认值
//             String ext = "abc" + upfile.getOriginalFilename();
//             String fileName = String.valueOf(System.currentTimeMillis()).concat("_").concat(""+new Random().nextInt(6)).concat(".").concat(ext);
//             StringBuilder sb = new StringBuilder();
//             //拼接保存路径
//             sb.append(basePath).append("/").append(fileName);
//             visitUrl = visitUrl.concat(fileName);
//             File f = new File(sb.toString());
//             if(!f.exists()){
//                 f.getParentFile().mkdirs();
//             }
//             OutputStream out = new FileOutputStream(f);
//             FileCopyUtils.copy(upfile.getInputStream(), out);
//             params.put("state", "SUCCESS");
//             params.put("url", visitUrl);
//             params.put("size", upfile.getSize());
//             params.put("original", fileName);
//             params.put("type", upfile.getContentType());
//        } catch (Exception e){
//            params.put("state", "ERROR");
//       }
//        return params;
//   }


}
