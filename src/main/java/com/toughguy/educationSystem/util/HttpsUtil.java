package com.toughguy.educationSystem.util;

import java.util.Map;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;
public class HttpsUtil {
	public JSONObject HttpsUtil(String Url,String Method,String Output) throws Exception{
        JSONObject jsonObject = null;
        URL conn_url =  new URL(Url);
        HttpURLConnection conn = (HttpsURLConnection)conn_url.openConnection();
        conn.setRequestMethod(Method);
//        conn.setReadTimeout(5000);
//        conn.setConnectTimeout(5000);
        conn.connect();
        //output获取access_token是不会用到
        if(Output != null){
            OutputStream  outputstream =conn.getOutputStream();
            //字符集，防止出现中文乱码
            outputstream.write(Output.getBytes("UTF-8"));
            outputstream.close();
        }
        //正常返回代码为200
        if(conn.getResponseCode()==200){
            InputStream stream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(stream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
            	System.out.println("qqqq"+str);
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
        stream.close();
        conn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        }
        System.out.println(conn.getResponseCode());
        return jsonObject;
    }   
}
