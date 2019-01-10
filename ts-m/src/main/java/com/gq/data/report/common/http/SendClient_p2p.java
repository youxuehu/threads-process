package com.gq.data.report.common.http;

import net.sf.json.JSONObject;
import org.cert.open.CertException;
import org.cert.open.CertToolV1;
import org.cert.utils.SHA;

import java.text.SimpleDateFormat;
import java.util.*;


public class SendClient_p2p {

	
	private static String ENDPOINT1 = "https://api.ifcert.org.cn/p2p/userInfo/test";
	private static String ENDPOINT2 = "https://api.ifcert.org.cn/p2p/scatterInvest/test";
	private static String ENDPOINT3 = "https://api.ifcert.org.cn/p2p/finance/test";
	private static String ENDPOINT4 = "https://api.ifcert.org.cn/p2p/transact/test";
	private static String ENDPOINT5 = "https://api.ifcert.org.cn/p2p/status/test";
	private static String ENDPOINT6 = "https://api.ifcert.org.cn/p2p/transfer/test";
	private static String ENDPOINT7 = "https://api.ifcert.org.cn/p2p/expectedReturns/test";
	private static String ENDPOINT8 = "https://api.ifcert.org.cn/p2p/financeScatterConfig/test";
	private static String version="1.5";

//	public static String apiKey = "10"; // 翼龙贷 test - opt
//	public static String sourceCode = "CERT10";  // 翼龙贷 test - opt
	public static String apiKey = "4XV4e5sWRjxq1SadrpsbjZbZXv8oxcVXf3SXfuiCd6pR"; // 翼龙贷 test - opt
	public static String sourceCode = "CERT20181031004";  // 翼龙贷 test - opt
	public static CertToolV1 tool=new CertToolV1();
	public  static Random rand=new Random();
	public static void main(String[] args) throws CertException {
		for(int i=0;i<1;i++){
			//userinfoTest(); // 用户
			//scatterInvestTest(); // 散标
			financingTest(); // 理财计划
//			transferTest(); // 债权转让
//			updateStatusTest(); // 状态更新
//			transactTest(); // 交易流水
//			infExpectedReturnsTest(); // 预期收益
//			infFinceScatterConfigTest(); // 理财散标配置
		}
	}

	/*
	 * p2p 1.5 用户数据推送
	 */
	private static void userinfoTest() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			for(int i=0;i<1;i++){
				Map<String, Object> param = new LinkedHashMap<String, Object>();
				String idcard=new StringBuilder("14272319991123").append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10)).toString();
				String phonenum=new StringBuilder("1833512").append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10)).toString();
				Map<String,String> map=getPhoneAndSalt(phonenum);
				param.put("version","1.5");
				param.put("userCreateTime", "2017-05-17 00:00:00");
				param.put("sourceCode", sourceCode);
				param.put("userSource", "6579544"+rand.nextInt(1000));
				param.put("userAttr", "1");
				param.put("userUuid", map.get("userUuid"));
				param.put("userStatus", "2");
				param.put("userIdcardHash", tool.idCardHash(idcard));
				param.put("userType", "1");
				param.put("userName", "测试InfUser接口");
				param.put("userIdcard","147852dfghjklfghjkl" );

				param.put("userNameHash", tool.nameHash("向上飞"+rand.nextInt(1000)));
				param.put("userPhoneHash", map.get("phoneHash"));

				param.put("userAscription", tool.getIdcardAscription(idcard));
				param.put("userAge", tool.getAge(idcard));
				param.put("userSex",tool.getSex(idcard));
				param.put("phoneAscription", tool.getPhoneAscription("18335124796"));

				param.put("userPhone", "18330******");
				param.put("userMail", "qq@163.com");
				param.put("userLawperson", "dsad");
				param.put("userFund", "ewqd");
				param.put("userProvince", "dsad3ref");
				param.put("userAddress", "北京");
				param.put("registerDate", "2016-04-12");


				List<Map<String, String>> tlist = new ArrayList<Map<String, String>>();
				Map<String, String> tmp1 = new LinkedHashMap<String, String>();
				tmp1.put("userPay", "汇付天下");
				tmp1.put("userPayAccount", "HFTX00001");
				tmp1.put("userBank", "汇付天下北京分部");
				tmp1.put("userBankAccount", "60100000001");
				Map<String, String> tmp2 = new LinkedHashMap<String, String>();
				tmp2.put("userPay", "如意支付");
				tmp2.put("userPayAccount", "RYF00001");
				tmp2.put("userBank", "北京如意支付");
				tmp2.put("userBankAccount", "60100000001");
				tlist.add(tmp1);
				tlist.add(tmp2);
				param.put("userList", tlist);
				list.add(param);
			}
			JSONObject json = new JSONObject();
			String nonce = Integer.toHexString(new Random().nextInt());
			long timestamp = System.currentTimeMillis();
			json.accumulate("version", version);
			json.accumulate("batchNum", sourceCode + "_" + getTimeFormat() + "_" + System.currentTimeMillis());
			json.accumulate("checkCode", tool.checkCode(list.toString()));
			json.accumulate("totalNum", list.size()+"");
			json.accumulate("sentTime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			json.accumulate("sourceCode", sourceCode);
			json.accumulate("infType", "1");
			json.accumulate("dataType", "1");
			json.accumulate("timestamp", timestamp+"");
			json.accumulate("nonce", nonce);
			json.accumulate("dataList", list);

			String result=sendPost(json, timestamp, nonce,ENDPOINT1);
			System.out.println("p2p_v1.5 用户接口测试结果：" + result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * p2p_v1.5 散标接口
	 */
	private static void scatterInvestTest() {
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < 1; i++) {
				Map<String, Object> param = new LinkedHashMap<String, Object>();
				param.put("version", version);
				param.put("productStartTime", "2017-05-18 12:31:39");
				param.put("productRegType", "新人贷");
				param.put("productName", "房抵贷短期周转（3期）");
				param.put("sourceCode", sourceCode);
				param.put("sourceProductCode", "3234");
				param.put("amount", "50000");
				param.put("rate", "0.000016");
				param.put("termType", "天");
				param.put("loanRate", "0.002455");
				param.put("securityInfo", "担保信息");
				param.put("collateralDesc", "2012年8月份大众帕萨特一辆，1.8T排量，裸车价21万元，里程5.6万公里， 车况良好，无划痕。");
				param.put("collateralInfo", "3月");
				param.put("amountLimmts", "300");
				param.put("amountLimmtl", "500");
				param.put("term", "12");
				param.put("loanUse", "4");
				param.put("loanDescribe", "还款描述");
				param.put("productMark", "信用贷");
				param.put("userIdcardHash",tool.idCardHash("142723199902022055"));
				param.put("payType", "1");
				param.put("serviceCost", "1000");
				param.put("riskMargin", "4000");
				param.put("loanType", "1");
				param.put("loanCreditRating", "A");
				param.put("overdueLimmit", "4月");
				param.put("badDebtLimmit", "4月");
				param.put("allowTransfer", "1");
				param.put("closeLimmit", "抵押");
				param.put("securityType", "线下");
				param.put("projectSource", "http://www.nmyjd.com/invest/a20151100034.html");
				param.put("sourceProductUrl", "www.baidu.com");
				list.add(param);
			}
			JSONObject json = new JSONObject();
			long timestamp = System.currentTimeMillis();
			String nonce = Integer.toHexString(new Random().nextInt());
			json.accumulate("version", version);
			json.accumulate("batchNum", sourceCode + "_" + getTimeFormat() + "_" + System.currentTimeMillis());
			json.accumulate("checkCode", tool.checkCode(list.toString()));
			json.accumulate("totalNum", ""+list.size());
			json.accumulate("sentTime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			json.accumulate("sourceCode", sourceCode);
			json.accumulate("infType", "2");
			json.accumulate("dataType",  "1");
			json.accumulate("timestamp", timestamp + "");
			json.accumulate("nonce", nonce);
			json.accumulate("dataList", list);
			String result=sendPost(json, timestamp, nonce, ENDPOINT2);
			System.out.println("p2p_v1.5 散标接口测试结果：" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *  p2p_v1.5 状态更新接口
	 */
	private static void updateStatusTest() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			for (int i = 0; i < 1; i++) {
				Map<String, Object> param = new LinkedHashMap<String, Object>();
				param.put("version", version);
				param.put("sourceCode", sourceCode);
				param.put("sourceProductCode", "fin1222323");
				param.put("sourceFinancingCode", "fin1222323");
				param.put("productStatus", "2");
				param.put("producStatusDesc", "截标");
				param.put("productDate", "2016-05-12 18:33:59");
				list.add(param);
			}
			long timestamp = System.currentTimeMillis();
			String nonce = Integer.toHexString(new Random().nextInt());
			JSONObject json = new JSONObject();
			json.accumulate("version", version);
			json.accumulate("batchNum", sourceCode + "_" + getTimeFormat() + "_" + System.currentTimeMillis());
			json.accumulate("checkCode", tool.checkCode(list.toString()));
			json.accumulate("totalNum", ""+list.size());
			json.accumulate("sentTime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			json.accumulate("sourceCode", sourceCode);
			json.accumulate("infType", "6");
			json.accumulate("dataType",  "1");
			json.accumulate("timestamp", timestamp + "");
			json.accumulate("nonce", nonce);
			json.accumulate("dataList", list);
			String result=sendPost(json, timestamp, nonce, ENDPOINT6);
			System.out.println("p2p_v1.5 状态更新接口测试结果：" + result);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("\n\n\n");
		}
	}
	/**
	 * p2p_v1.5 理财计划接口
	 */
	private static void financingTest() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Map<String, Object> param = new LinkedHashMap<String, Object>();
			param.put("version", version);
			param.put("productRegType", "02");
			param.put("productName", "保本保息_asdf");
			param.put("sourceCode", sourceCode);
			param.put("sourceFinancingCode","fin2020");
			param.put("rate", "0.082342");
			param.put("redRate", "0.082342");
			param.put("sourceProductUrl", "http://www.nmyjd.com/invest/a20151100034.html");
			param.put("productMark", "2");
			param.put("financingStartTime", "2017-02-28 12:09:23");
			param.put("isshow", "1");
			param.put("term", "30");
			param.put("termType", "天");
			param.put("financeType", "1");
			param.put("realRaiseAmount", "10000");
			param.remove("borrowamt");
			param.remove("beginDate");
			param.remove("endDate");
			param.put("planRaiseAmount", "200000");
			param.put("remark", "TEST");
			param.put("amountLimmtl", "200000");
			param.put("amountLimmts", "5000");
			list.add(param);
			long timestamp = System.currentTimeMillis();
			String nonce = Integer.toHexString(new Random().nextInt());
			JSONObject json = new JSONObject();
			json.accumulate("version", version);
			json.accumulate("batchNum", sourceCode + "_" + getTimeFormat() + "_" + System.currentTimeMillis());
			json.accumulate("checkCode", tool.checkCode(list.toString()));
			json.accumulate("totalNum", ""+list.size());
			json.accumulate("sentTime",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			json.accumulate("sourceCode", sourceCode);
			json.accumulate("infType", "3");
			json.accumulate("dataType",  "1");
			json.accumulate("timestamp", timestamp + "");
			json.accumulate("nonce", nonce);
			json.accumulate("dataList", list);
			String result=sendPost(json, timestamp, nonce, ENDPOINT3);
			System.out.println("p2p_v1.5 理财计划接口测试结果：" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * p2p_v1.5 债权转让接口
	 */
	private static void transferTest() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			for (int i = 0; i < 1; i++) {
				Map<String, Object> param = new LinkedHashMap<String, Object>();
				param.put("sourceCode", sourceCode);
				param.put("version", version);
				param.put("transferStartTime", "2017-05-18 18:33:59");
				param.put("transferId","1212121");
				param.put("productRegType", "新人贷");
				param.put("productName", "项目名称：转让特定部分");
				param.put("productMark", "抵押贷");
				param.put("sourceProductCode", "sou1212");
				param.put("sourceFinancingCode", "fian12121");
				param.put("assigneeName", "jfasldkjfljweejl4oi34");
				param.put("transferName", "王明");
				param.put("sourceProductUrl", "http://www.nmyjd.com/invest/a20151100034.html");
				param.put("holdTime", "30");
				param.put("overplusTime", "12月");
				param.put("amt", "44432");
				param.put("transferRate", "0.345786");
				param.put("transferFee", "3333");
				param.put("remark", "备注信息");
				param.put("transferTransid", "94679162");
				param.put("assigneeTransid", "94679162");
				list.add(param);
			}
			long timestamp = System.currentTimeMillis();
			String nonce = Integer.toHexString(new Random().nextInt());
			JSONObject json = new JSONObject();
			json.accumulate("version", version);
			json.accumulate("batchNum", sourceCode + "_" + getTimeFormat() + "_" + System.currentTimeMillis());
			json.accumulate("checkCode", tool.checkCode(list.toString()));
			json.accumulate("totalNum", ""+list.size());
			json.accumulate("sentTime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			json.accumulate("sourceCode", sourceCode);
			json.accumulate("infType", "5");
			json.accumulate("dataType",  "1");
			json.accumulate("timestamp", timestamp + "");
			json.accumulate("nonce", nonce);
			json.accumulate("dataList", list);
			String result=sendPost(json, timestamp, nonce, ENDPOINT5);
			System.out.println("p2p_v1.5 债权转让接口测试结果：" + result);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("\n\n\n");
		}
	}
	/**
	 * p2p_v1.5 交易流水接口
	 */
	private static void transactTest() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			for (int i = 0; i < 1; i++) {
				Map<String, Object> param = new LinkedHashMap<String, Object>();
				param.put("version", version);
				param.put("transTime", "2016-05-16 12:00:01");
				param.put("transId", "11111111111111111111111");
				param.put("sourceCode", sourceCode);
				param.put("sourceProductCode", "3234");
				param.put("sourceFinancingCode", "12");
				param.put("transType", "10");
				param.put("transPayment", "a");
				param.put("transTypeDec", "放款");
				param.put("transMoney", "232323");
				param.put("userSource", "3355");
				param.put("transDate", "2015-10-12");
				param.put("transBank", "建设银行");
				param.put("transAccount","323232");
				param.put("transSourcePeer", "1111");
				param.put("transBankPeer", "汇付天下");
				param.put("transAccountPeer", "张飞");
				param.put("userIdcardHash", tool.idCardHash("142723199902062020"));
				param.put("sourceProductName", "fin00001");
				param.put("sourceFinancingName", "sin00001");
				list.add(param);
			}

			long timestamp = System.currentTimeMillis();
			String nonce = Integer.toHexString(new Random().nextInt());
			JSONObject json = new JSONObject();
			json.accumulate("version", version);
			json.accumulate("batchNum", sourceCode + "_" + getTimeFormat() + "_" + System.currentTimeMillis());
			json.accumulate("checkCode", tool.checkCode(list.toString()));
			json.accumulate("totalNum", ""+list.size());
			json.accumulate("sentTime",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			json.accumulate("sourceCode", sourceCode);
			json.accumulate("infType", "4");
			json.accumulate("dataType",  "1");
			json.accumulate("timestamp", timestamp + "");
			json.accumulate("nonce", nonce);
			json.accumulate("dataList",list);
			String result=sendPost(json, timestamp, nonce, ENDPOINT4);
			System.out.println("p2p_v1.5 交易流水接口测试结果：" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * p2p_v1.5 预期收益接口
	 */
	private static void infExpectedReturnsTest() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			for (int i = 0; i <1; i++) {
				Map<String, Object> param = new LinkedHashMap<String, Object>();
				param.put("version", version);
				param.put("sourceCode", sourceCode);
				param.put("sourceProductCode", "fin1212");
				param.put("sourceFinancingCode", "fin1212");
				param.put("userBalance", "4");
				param.put("expectMoney", "85858");
				param.put("expectDate", "2016-05-12");
				param.put("userSource", "121212");
				param.put("userIdcardHash", tool.idCardHash("142723199902062020"));
				param.put("expectRate", "0.121214");
				list.add(param);
			}
			long timestamp = System.currentTimeMillis();
			String nonce = Integer.toHexString(new Random().nextInt());
			JSONObject json = new JSONObject();
			json.accumulate("version", version);
			json.accumulate("batchNum", sourceCode + "_" + getTimeFormat() + "_" + System.currentTimeMillis());
			json.accumulate("checkCode", tool.checkCode(list.toString()));
			json.accumulate("totalNum", ""+list.size());
			json.accumulate("sentTime",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			json.accumulate("sourceCode", sourceCode);
			json.accumulate("infType", "7");
			json.accumulate("dataType",  "1");
			json.accumulate("timestamp", timestamp + "");
			json.accumulate("nonce", nonce);
			json.accumulate("dataList", list);
			String result=sendPost(json, timestamp, nonce, ENDPOINT7);
			System.out.println("p2p_v1.5预期收益接口测试结果：" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *  p2p_v1.5 理财散标配置接口
	 */
	private static void infFinceScatterConfigTest() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String time=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		try {
			for (int i = 0; i <1; i++) {
				Map<String, Object> param = new LinkedHashMap<String, Object>();
				param.put("version", version);
				param.put("id", "121212");
				param.put("userIdcardHash", "032da1988af3e5a1733be16a52cc9d9f8b96c9564a868061197da17c179d3b42");
				param.put("sourceCode", sourceCode);
				param.put("sourceProductCode", "2");
				param.put("sourceProductName", "鹊桥搭建经费");
				param.put("sourceFinancingCode", "108045");
				param.put("sourceFinancingName", "月老基金费");
				param.put("productMoney","25000000");
				param.put("investMoney", "25000000");
				param.put("configMoney","26300" );
				param.put("configDate", "2017-02-02 12:12:12");
				param.put("beginDate","2017-02-02" );
				param.put("endDate", "2017-02-02");
				param.put("scatterType", "1");
				list.add(param);
			}
			long timestamp = System.currentTimeMillis();
			String nonce = Integer.toHexString(new Random().nextInt());
			JSONObject json = new JSONObject();
			json.accumulate("version", version);
			json.accumulate("batchNum", sourceCode + "_" + getTimeFormat() + "_" + System.currentTimeMillis());
			json.accumulate("checkCode", tool.checkCode(list.toString()));
			json.accumulate("totalNum", ""+list.size());
			json.accumulate("sentTime",time);
			json.accumulate("sourceCode", sourceCode);
			json.accumulate("infType", "10");
			json.accumulate("dataType",  "1");
			json.accumulate("timestamp", timestamp + "");
			json.accumulate("nonce", nonce);
			json.accumulate("dataList", list);
			String result=sendPost(json, timestamp, nonce, ENDPOINT8);
			System.out.println("p2p_v1.5 理财散标配置接口测试结果：" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String sendPost(JSONObject json, Long timestamp,String nonce,String url){

		String token = getApiKey(apiKey, sourceCode, version, timestamp, nonce);
		String msg=json.toString();
		
		Map<String,String> params=new HashMap<String,String>(2);

		params.put("apiKey", token);
		params.put("msg", msg);

		String rtnMsg = HttpsUtils.postRequest(url, params);
		return rtnMsg;
	}
	


	/**
	 * apiKey加密
	 * @param apiKey      
	 * @param source_code 平台编号
	 * @param versionStr
	 * 			版本号，如：1.3 ; 1.4
	 * @param currentTime 时间戳
	 * @param nonce       随机数
	 * @return
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
	/**
	 * 获取时间格式化后的字符串
	 * 
	 * @return
	 */
	private static String getTimeFormat() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String time = dateFormat.format(now);
		return time;
	}
	/**
	 * 获去phoneHash 和userUUid
	 * @param phonenum
	 * @return
	 * @throws CertException
	 */
	private static Map<String,String> getPhoneAndSalt(String phonenum) throws CertException{
		Map<String,String> map =new HashMap<String,String>();
		JSONObject json=tool.phoneHash(phonenum);
		String phoneHash =json.getString("phone");
		String salt=json.getString("salt");
		map.put("phoneHash", phoneHash);
		map.put("userUuid", salt);
		return map;
	}
}
