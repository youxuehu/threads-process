package com.gq.data.report.common.http;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.cert.utils.SHA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class HttpsUtils {

	public static PoolingHttpClientConnectionManager  poolConnManager=null;

	public static String getReq(String url){
		Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		HttpClient http = new  HttpClient();
		String result = "";
		//get请求
		GetMethod get = new GetMethod(url);
		// 设置请求报文头的编码
		//		get.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		get.setRequestHeader("Content-Type", "multipart/form-data;charset=utf-8");

		try {
			http.executeMethod(get);
			BufferedReader reader = new BufferedReader(new InputStreamReader(get.getResponseBodyAsStream(), "utf-8"));
			String tmp = null;
			while ((tmp = reader.readLine()) != null) {
				result += tmp + "\r\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return removeSlash(result);
	}

	public static String postRequest(String url, Map<String,String> param ) {
		Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		String strResult = "";
		HttpClient httpClient = new HttpClient();


		HttpClientParams httpClientParams = httpClient.getParams();
		httpClientParams.setConnectionManagerTimeout(500000);

		//		HttpConnectionParams 

		HttpConnectionManagerParams httpConnectionManagerParams = httpClient.getHttpConnectionManager().getParams();
		httpConnectionManagerParams.setConnectionTimeout(500000);  
		httpConnectionManagerParams.setSoTimeout(500000);

		//		httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));

		try {
			// post请求
			PostMethod post = new PostMethod(url);
			//			post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false)); 
			List<NameValuePair> params=new ArrayList<NameValuePair>();
			for(Map.Entry<String, String> entry:param.entrySet()){
				params.add(new NameValuePair(entry.getKey(),entry.getValue()));
			}
			NameValuePair[] names= new NameValuePair[params.size()];
			for(int i=0;i<params.size();i++){
				names[i] = new NameValuePair(params.get(i).getName(),params.get(i).getValue());	
			}
			//设置请求报文头的编码
			post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			//			post.setRequestHeader("Content-Type", "multipart/form-data ; charset=utf-8");
			//			post.setRequestHeader("Connection", "keep-alive");    

			post.setRequestBody(names);
			httpClient.executeMethod(post);
			BufferedReader reader = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream(), "utf-8"));
			String tmp = null;
			while ((tmp = reader.readLine()) != null) {
				strResult += tmp + "\r\n";
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return removeSlash(strResult);
	}




	public static String removeSlash(String result){
		result=result.replace("\\", "");
		if(result.length()>0)
			result=result.substring(0, result.length()-1);
		return result;
	}


	public static String deleteRequest(String url,Map<String,String> dataForm) {
		Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		String strResult = "";
		HttpClient http = new HttpClient();
		try {
			DeleteMethod delete = new DeleteMethod(url);

			delete.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");


			List<NameValuePair> data = new ArrayList<NameValuePair>();  
			if(dataForm!=null){  
				Set<String> keys = dataForm.keySet();  
				for(String key:keys){  
					NameValuePair nameValuePair = new NameValuePair(key, (String) dataForm.get(key));  
					data.add(nameValuePair);  
				}  
			}  

			delete.setQueryString(data.toArray(new NameValuePair[0])); 
			http.executeMethod(delete);
			BufferedReader reader = new BufferedReader(new InputStreamReader(delete.getResponseBodyAsStream(), "utf-8"));
			String tmp = null;
			while ((tmp = reader.readLine()) != null) {
				strResult += tmp + "\r\n";
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return removeSlash(strResult);
	}
	public static void main(String[] args) {

		String url="http://localhost:8080/login";
		Map<String,String> map=new HashMap<String,String>();
		map.put("1", "2");
		String request = postRequest(url, map);
		System.out.println(request);

	}


}
