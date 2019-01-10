package com.gq.data.report.common.http;

import org.cert.utils.SHA;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class BalanceServiceClient {

//	private static String url1 = "https://202.108.211.109:30027/balanceService/v15/yiBuMessage";//企业查询异步消息表
//	private static String url2 = "https://202.108.211.109:30027/balanceService/v15/batchNum";//企业推送批次数
//	private static String url3 = "https://202.108.211.109:30027/balanceService/v15/batchList";//企业推送批次列表
//	private static String url4 = "https://202.108.211.109:30027/balanceService/v15/dataList";//企业某批次数据列表
//	private static String url5 = "https://202.108.211.109:30027/balanceService/v15/deleteData";//按批次删除数据接口
//	private static String url6 = "https://202.108.211.109:30027/balanceService/v15/deletedRecord";//删除记录查询接口

		private static String url1 = "https://api.ifcert.org.cn/balanceService/v15/yiBuMessage";//企业查询异步消息表
		private static String url2 = "https://api.ifcert.org.cn/balanceService/v15/batchList";//企业推送批次数
		private static String url3 = "https://api.ifcert.org.cn/balanceService/v15/batchList";//企业推送批次列表
		private static String url4 = "https://api.ifcert.org.cn/balanceService/v15/dataList";//企业某批次数据列表
		private static String url5 = "https://api.ifcert.org.cn/balanceService/v15/deleteData";//按批次删除数据接口
		private static String url6 = "https://api.ifcert.org.cn/balanceService/v15/deleteRecord";//删除记录查询接口

	public static String apiKey = "4XV4e5sWRjxq1SadrpsbjZbZXv8oxcVXf3SXfuiCd6pR"; // 翼龙贷 test - opt
	public static String sourceCode = "CERT20181031004";  // 翼龙贷 test - opt



	public static void main(String[] args) throws Exception {

		String batchNum = "CERT20181031004_201811210_201811210000001";
		String dataType = "0";
		String version = "1.5";
		// inf_type int,#接口类型，
		// 1：查询异步消息
		test1(apiKey, batchNum, dataType, sourceCode, version);
 		// 2：企业推送批次数
		//test2(apiKey, dataType, sourceCode, version);
		// 3：企业推送批次列表
		//test3(apiKey, dataType, sourceCode, version,"1");
		// 4：企业某批次数据列表
		//test4(apiKey, batchNum, dataType, sourceCode, version);
		// 5：按批次删除数据接口
		//test5(apiKey, batchNum, dataType, sourceCode, version);
		// 6：删除记录查询接口
		//test6(apiKey, dataType, sourceCode, version);
	}


	// 6：删除记录查询接口
	public static void test6(String apiKey, String dataType, String sourceCode, String version) throws Exception {
		long timestamp = System.currentTimeMillis();
		String nonce = Integer.toHexString(new Random().nextInt());
		String apiKey2 = getApiKey(apiKey, sourceCode, version, timestamp, nonce);
		StringBuilder url=new StringBuilder(url6);
		url.append("?apiKey=");
		url.append(apiKey2);
		url.append("&dataType=");
		url.append(dataType);
		url.append("&nonce=");
		url.append(nonce);
		url.append("&sourceCode=");
		url.append(sourceCode);
		url.append("&timestamp=");
		url.append(timestamp+"");
		url.append("&version=");
		url.append(version);
		String result=HttpsUtils.getReq(url.toString());
		System.out.println(result);
	}

	// 5：按批次删除数据接口
	public static void test5(String apiKey, String batchNum, String dataType, String sourceCode, String version)
			throws Exception {
		long timestamp = System.currentTimeMillis();
		String nonce = Integer.toHexString(new Random().nextInt());
		String token = getApiKey(apiKey, sourceCode, version, timestamp, nonce);
		Map<String,String> map=new HashMap<String,String>();

		map.put("apiKey", token);
		map.put("nonce",nonce );
		map.put("sourceCode",sourceCode );
		map.put("timestamp",timestamp+"" );
		map.put("version",version );
		map.put("dataType",dataType );
		map.put("batchNum",batchNum );


		String rtnMsg = HttpsUtils.deleteRequest(url5,map);

		System.out.println(rtnMsg);
	}

	// 4：企业某批次数据列表
	public static void test4(String apiKey, String batchNum, String dataType, String sourceCode, String version)
			throws Exception {
		long timestamp = System.currentTimeMillis();
		String nonce = Integer.toHexString(new Random().nextInt());
		String apiKey2 = getApiKey(apiKey, sourceCode, version, timestamp, nonce);
		StringBuilder url=new StringBuilder(url4);
		url.append("?apiKey=");
		url.append(apiKey2);
		url.append("&nonce=");
		url.append(nonce);
		url.append("&sourceCode=");
		url.append(sourceCode);
		url.append("&timestamp=");
		url.append(timestamp+"");
		url.append("&version=");
		url.append(version);
		url.append("&dataType=");
		url.append(dataType);
		url.append("&batchNum=");
		url.append(batchNum);
		String result=HttpsUtils.getReq(url.toString());
		System.out.println(result);
	}

	// 3：企业推送批次列表
	public static void test3(String apiKey, String dataType, String sourceCode, String version,String pageNum) throws Exception {
		long timestamp = System.currentTimeMillis();
		String nonce = Integer.toHexString(new Random().nextInt());
		String apiKey2 = getApiKey(apiKey, sourceCode, version, timestamp, nonce);
		StringBuilder url=new StringBuilder(url3);
		url.append("?apiKey=");
		url.append(apiKey2);
		url.append("&dataType=");
		url.append(dataType);
		url.append("&nonce=");
		url.append(nonce);
		url.append("&sourceCode=");
		url.append(sourceCode);
		url.append("&timestamp=");
		url.append(timestamp+"");
		url.append("&version=");
		url.append(version);
		url.append("&pageNum=");
		url.append(pageNum);
		String result=HttpsUtils.getReq(url.toString());
		System.out.println(result);
	}

	// 2：企业推送批次数
	public static void test2(String apiKey, String dataType, String sourceCode, String version) throws Exception {
		long timestamp = System.currentTimeMillis();
		String nonce = Integer.toHexString(new Random().nextInt());
		String apiKey2 = getApiKey(apiKey, sourceCode, version, timestamp, nonce);
		StringBuilder url=new StringBuilder(url2);
		url.append("?apiKey=");
		url.append(apiKey2);
		url.append("&nonce=");
		url.append(nonce);
		url.append("&sourceCode=");
		url.append(sourceCode);
		url.append("&timestamp=");
		url.append(timestamp+"");
		url.append("&version=");
		url.append(version);
		url.append("&dataType=");
		url.append(dataType);
		System.out.println(url.toString());
		String result=HttpsUtils.getReq(url.toString());
		System.out.println(result);
	}

	// 1：查询异步消息
	public static void test1(String apiKey, String batchNum, String dataType, String sourceCode, String version) {
		try {
			long timestamp = System.currentTimeMillis();
			String nonce = Integer.toHexString(new Random().nextInt());
			String apiKey2 = getApiKey(apiKey, sourceCode, version, timestamp, nonce);
			StringBuilder url=new StringBuilder(url1);
			url.append("?apiKey=");
			url.append(apiKey2);
			url.append("&nonce=");
			url.append(nonce);
			url.append("&sourceCode=");
			url.append(sourceCode);
			url.append("&timestamp=");
			url.append(timestamp+"");
			url.append("&version=");
			url.append(version);
			url.append("&dataType=");
			url.append(dataType);
			url.append("&batchNum=");
			url.append(batchNum);
			System.out.println(url.toString());
			String result=HttpsUtils.getReq(url.toString());
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * apiKey加密
	 * 
	 * @param apiKey
	 * @param source_code
	 * @param
	 *             ，如：v1.1-->110; v1.2-->120; v1.3-->130
	 * @return
	 * @throws
	 */


	private static String getApiKey(String apiKey, String source_code, String versionStr, Long currentTime,String nonce) {

		double version_double = Double.valueOf(versionStr);
		int version =(int)(version_double*100);
		String versionHex = "0x" + Integer.toHexString(version);
		StringBuilder s = new StringBuilder();
		s.append(source_code);
		s.append(versionHex);
		s.append(apiKey);
		s.append(currentTime);
		s.append(nonce);
		String key = SHA.SHA256(s.toString());
		return key;
	}

}
