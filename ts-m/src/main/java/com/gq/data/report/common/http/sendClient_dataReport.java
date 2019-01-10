package com.gq.data.report.common.http;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.cert.open.CertToolV1;
import org.cert.utils.SHA;

import java.io.*;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.*;

public class sendClient_dataReport  {

	private static String ENDPOINT1 = "https://202.108.211.109:30027/dataReportService/v15/transactionStatistics";
	private static String ENDPOINT2 = "https://202.108.211.109:30027/dataReportService/v15/platformBalanceSheet";
	private static String ENDPOINT3 = "https://202.108.211.109:30027/dataReportService/v15/platformProfitStatement";

	
//	private static String ENDPOINT1 = "https://api.ifcert.org.cn:8443/dataReportService/v15/transactionStatistics";
//	private static String ENDPOINT2 = "https://api.ifcert.org.cn:8443/dataReportService/v15/platformBalanceSheet";
//	private static String ENDPOINT3 = "https://api.ifcert.org.cn:8443/dataReportService/v15/platformProfitStatement";

	public static String apiKey = "39932f24546b11274e1f1caeb6d14wsf"; // 翼龙贷 test - opt
	public static String sourceCode = "TEST20170624003";  // 翼龙贷 test - opt  TEST20170624003
	private static String version = "1.5";

	public static CertToolV1 tool=new CertToolV1();
	public static void main(String[] args) throws Exception {
		sendClient_dataReport test=new sendClient_dataReport();
		test.transactionStatistics();//平台交易统计接口
		test.platformBalanceSheet();//资产负债表接口
		test.platformProfitStatement();//利润采集接口
	}

	private void platformProfitStatement() throws RemoteException, UnsupportedEncodingException {

		List<Map<String, String>> paramList1 = new ArrayList<Map<String, String>>();
			Map<String, String> param = new LinkedHashMap<String, String>();
			param.put("version",version);
			param.put("sourceCode",sourceCode);
			param.put("type","1");
			param.put("description","月表");
			param.put("reportDate", "2017-05");
			param.put("operatingIncome","2037791138");
			param.put("operatingCost","69511");
			param.put("businessTaxAndSurcharges"," 2179105166");
			param.put("operatingExpenses","8");
			param.put("generalAndAdministrativeExpense","73" );
			param.put("financingExpense","83241"  );
			param.put("impairmentLossAssetsImpairment","23283238");
			param.put( "interestExpenses","0.32");
			param.put("handlingChargesAndCommissionsIncome","14");
			param.put("handlingChargesAndCommissionExpenses","81822");
			param.put("businessExpenses","34443298");
			param.put("gainOrLossFromChangesInFairValues","0.21");
			param.put("investmentIncome","23233");
			param.put("interestIncome","2333433");
			param.put("incomeFromOtherOperations","0.01");
			param.put("operatingProfit","332340");
			param.put("nonOperatingRevenues","342121");
			param.put("nonOperatingExpenses","2");
			param.put("totalProfit","2323121212");
			param.put("incomeTax","2321212");
			param.put("netProfit","23234");
			param.put("earningsPerShare","98729323.23");
			paramList1.add(param);

		String str = JSONSerializer.toJSON(paramList1).toString();
		str = str.substring(1, str.length() - 1);


		long timestamp = System.currentTimeMillis();
		String nonce = Integer.toHexString(new Random().nextInt());
		///////////////////////////////////////////
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:dd");
		String sentTime=sdf.format(new Date());		

		JSONObject json = new JSONObject();
		json.accumulate("version", version);
		json.accumulate("batchNum", sourceCode + "_" + getTimeFormat() + "_" + System.currentTimeMillis());
		json.accumulate("checkCode", tool.checkCode(paramList1.toString()));
		json.accumulate("totalNum", paramList1.size());
		json.accumulate("sentTime", sentTime);
		json.accumulate("sourceCode", sourceCode);
		json.accumulate("infType", "9");
		json.accumulate("dataType", "1");
		json.accumulate("timestamp", timestamp + "");
		json.accumulate("nonce",nonce );
		json.accumulate("dataList", "[" + str + "]");
		
		String result=sendPost(json, timestamp, nonce, ENDPOINT3);
		System.out.println("用户接口测试结果：" + result);


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



	public void platformBalanceSheet() throws RemoteException, UnsupportedEncodingException{
		List<Map<String, String>> paramList1 = new ArrayList<Map<String, String>>();
		for(int i=0;i<1;i++){
			Map<String, String> param = new LinkedHashMap<String, String>();

			param.put("version", version);
			param.put("sourceCode",sourceCode);
			param.put("type","4");
			param.put("description","年表");
			param.put("reportDate", "2018");
			param.put("f1001", "111111111111111.11");
			param.put("f1002", "10.02");
			param.put("f1012", "3.00");
			param.put("f101201", "1012.22");
			param.put("f101202", "22.22");
			param.put("f101203", "121212212");
			param.put("f101204", "255222");
			param.put("f101205", "223");
			param.put("f101206", "23232");
			param.put("f1101", "1212121212");
			param.put("f110101", "12121.23");
			param.put("f11010101", "323");
			param.put("f11010102", "2");
			param.put("f11010103", "2323");
			param.put("f11010104", "23");
			param.put("f11010199", "1");
			param.put("f110102", "23232");
			param.put("f11010201", "5");
			param.put("f11010202", "121484545");
			param.put("f11010203", "484545");
			param.put("f11010204", "2232323");
			param.put("f11010299", "121213232654545");
			param.put("f1121", "200");
			param.put("f1122", "220");
			param.put("f1123", "555");
			param.put("f1131", "222");
			param.put("f1132", "22");
			param.put("f1221", "12121212");
			param.put("f1231", "2552");
			param.put("f123101", "223");
			param.put("f123102", "23232");
			param.put("f1321", "1212121212");
			param.put("f1401", "12121.23");
			param.put("f1402", "323");
			param.put("f1403", "2");
			param.put("f1404", "2323");
			param.put("f1405", "23");
			param.put("f1406", "1");
			param.put("f1407", "23232");
			param.put("f1408", "5");
			param.put("f1411", "121484545");
			param.put("f1471", "484545");
			param.put("f1501", "2232323");
			param.put("f150101", "121213232654545");
			param.put("f150102", "200");
			param.put("f150103", "220");
			param.put("f1502", "555");
			param.put("f1503", "222");
			param.put("f1511", "22");
			param.put("f151101", "12121212");
			param.put("f151102", "2552");
			param.put("f151103", "223");
			param.put("f1512", "23232");
			param.put("f1521", "1212121212");
			param.put("f152101", "12121.23");
			param.put("f152102", "323");
			param.put("f1531", "2");
			param.put("f1532", "2323");
			param.put("f1601", "23");
			param.put("f1602", "1");
			param.put("f1603", "23232");
			param.put("f1604", "5");
			param.put("f160401", "121484545");
			param.put("f160402", "484545");
			param.put("f160403", "2232323");
			param.put("f160404", "121213232654545");
			param.put("f1605", "200");
			param.put("f160501", "220");
			param.put("f160502", "555");
			param.put("f160503", "222");
			param.put("f160504", "22");
			param.put("f1606", "12121212");
			param.put("f1701", "2552");
			param.put("f1702", "223");
			param.put("f1703", "23232");
			param.put("f1711", "1212121212");
			param.put("f1801", "12121.23");
			param.put("f1811", "323");
			param.put("f1901", "2");
			param.put("f2001", "2323");
			param.put("f2101", "23");
			param.put("f210101", "1");
			param.put("f210102", "23232");
			param.put("f2201", "5");
			param.put("f2202", "121484545");
			param.put("f2203", "484545");
			param.put("f2211", "2232323");
			param.put("f221101", "121213232654545.33");
			param.put("f221102", "200");
			param.put("f221103", "220");
			param.put("f221104", "555");
			param.put("f221105", "222");
			param.put("f221106", "22");
			param.put("f221107", "12121212.22");
			param.put("f2221", "2552");
			param.put("f222101", "223");
			param.put("f22210101", "23232");
			param.put("f22210102", "1212121212");
			param.put("f22210103", "12121.23");
			param.put("f22210104", "323");
			param.put("f22210105", "2");
			param.put("f22210106", "2323");
			param.put("f22210107", "23");
			param.put("f22210108", "1");
			param.put("f22210109", "23232");
			param.put("f222102", "5");
			param.put("f222103", "121484545");
			param.put("f222104", "484545");
			param.put("f222105", "2232323");
			param.put("f222106", "121213232654545");
			param.put("f222107", "200");
			param.put("f222108", "220");
			param.put("f222109", "555");
			param.put("f222110", "222");
			param.put("f222111", "22");
			param.put("f222112", "12121212");
			param.put("f222113", "2552");
			param.put("f222114", "223");
			param.put("f2231", "23232");
			param.put("f2232", "1212121212");
			param.put("f2241", "12121.23");
			param.put("f2314", "323");
			param.put("f2401", "2");
			param.put("f2501", "2323");
			param.put("f250101", "23");
			param.put("f250102", "1");
			param.put("f2502", "23232");
			param.put("f250201", "5");
			param.put("f250202", "121484545");
			param.put("f250203", "484545");
			param.put("f2701", "2232323");
			param.put("f2702", "121213232654545");
			param.put("f2711", "200");
			param.put("f2801", "220");
			param.put("f2901", "555");
			param.put("f3101", "222");
			param.put("f3201", "22");
			param.put("f3202", "12121212");
			param.put("f4001", "2552");
			param.put("f4002", "223");
			param.put("f400201", "23232");
			param.put("f400202", "1212121212");
			param.put("f400203", "12121.23");
			param.put("f4101", "323");
			param.put("f410101", "2");
			param.put("f410102", "2323");
			param.put("f410103", "23");
			param.put("f410104", "1");
			param.put("f410105", "23232");
			param.put("f410106", "5");
			param.put("f4103", "121484545");
			param.put("f4104", "484545");
			param.put("f410401", "2232323");
			param.put("f410402", "121213232654545");
			param.put("f410403", "200");
			param.put("f410404", "220");
			param.put("f410405", "555");
			param.put("f410406", "222");
			param.put("f410407", "22");
			param.put("f410408", "12121212");
			param.put("f410409", "2552");
			param.put("f410410", "223");
			param.put("f410411", "23232");
			param.put("f4201", "1212121212");
			param.put("f5001", "12121.23");
			param.put("f500101", "323");
			param.put("f500102", "2");
			param.put("f5101", "2323");
			param.put("f5201", "23");
			param.put("f5301", "1");
			param.put("f530101", "23232");
			param.put("f530102", "5");
			param.put("f6001", "121484545");
			param.put("f6051", "484545");
			param.put("f6101", "2232323");
			param.put("f6111", "121213232654545");
			param.put("f6301", "200");
			param.put("f6401", "220");
			param.put("f6402", "555");
			param.put("f6403", "222");
			param.put("f6601", "22");
			param.put("f660101", "12121212");
			param.put("f660102", "2552");
			param.put("f660103", "223");
			param.put("f660104", "23232");
			param.put("f660105", "1212121212");
			param.put("f660106", "12121.23");
			param.put("f660107", "323");
			param.put("f660108", "2");
			param.put("f660109", "2323");
			param.put("f660110", "23");
			param.put("f660111", "1");
			param.put("f660112", "23232");
			param.put("f660113", "5");
			param.put("f660114", "121484545");
			param.put("f660115", "484545");
			param.put("f660116", "2232323");
			param.put("f660117", "121213232654545");
			param.put("f660118", "200");
			param.put("f660119", "220");
			param.put("f660120", "555");
			param.put("f660121", "222");
			param.put("f660122", "22");
			param.put("f660199", "12121212");
			param.put("f6602", "2552");
			param.put("f660201", "223");
			param.put("f660202", "23232");
			param.put("f660203", "1212121212");
			param.put("f660204", "12121.23");
			param.put("f660205", "323");
			param.put("f660206", "2");
			param.put("f660207", "2323");
			param.put("f660208", "23");
			param.put("f660209", "1");
			param.put("f660210", "23232");
			param.put("f660211", "5");
			param.put("f660212", "121484545");
			param.put("f660213", "484545");
			param.put("f660214", "2232323");
			param.put("f660215", "121213232654545");
			param.put("f660216", "200");
			param.put("f660217", "220");
			param.put("f660218", "555");
			param.put("f660219", "222");
			param.put("f660220", "22");
			param.put("f660221", "12121212");
			param.put("f660222", "2552");
			param.put("f660223", "223");
			param.put("f660224", "23232");
			param.put("f660225", "1212121212");
			param.put("f660226", "12121.23");
			param.put("f660227", "323");
			param.put("f660228", "2");
			param.put("f6603", "2323");
			param.put("f660301", "23");
			param.put("f660302", "1");
			param.put("f660303", "23232");
			param.put("f660399", "5");
			param.put("f6701", "121484545");
			param.put("f6711", "484545");
			param.put("f6801", "2232323");
			param.put("f680101", "121213232654545");
			param.put("f680102", "200");
			param.put("f6901", "220");
			paramList1.add(param);
		}



		String str = JSONSerializer.toJSON(paramList1).toString();
		str = str.substring(1, str.length() - 1);


		long timestamp = System.currentTimeMillis();
		String nonce = Integer.toHexString(new Random().nextInt());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:dd");
		String sentTime=sdf.format(new Date());		

		JSONObject json = new JSONObject();
		json.accumulate("version", version);
		json.accumulate("batchNum", sourceCode + "_" + getTimeFormat() + "_" + System.currentTimeMillis());
		json.accumulate("checkCode", tool.checkCode(paramList1.toString()));
		json.accumulate("totalNum", paramList1.size());
		json.accumulate("sentTime", sentTime);
		json.accumulate("sourceCode", sourceCode);
		json.accumulate("infType", "8");
		json.accumulate("dataType", "1");
		json.accumulate("timestamp", timestamp + "");
		json.accumulate("nonce",nonce );
		json.accumulate("dataList", "[" + str + "]");

		String result=sendPost(json, timestamp, nonce, ENDPOINT2);
		System.out.println("接口测试结果：" + result);

	}
	public void transactionStatistics() throws RemoteException{
		List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
		Map<String, Object> param = new LinkedHashMap<String, Object>();
		param.put("version", version);
		param.put("sourceCode",sourceCode);
		param.put("statisticsTime", "2012-12-12");
		param.put("accumulateTransMoney", "31245121435.03");
		param.put("accumulateTransNum", "200" );
		param.put("loanBalance", "220");
		param.put("financialLiquidity", "5555");
		param.put("leverRatio", "12.0");
		param.put("overdue90Rate", "20.20");
		param.put("productTotal", "12121212");
		param.put("aggregateAmount", "2552");
		param.put("productAverageRate", "25.23");
		param.put("averageDeadlineProduct", "88.22");
		param.put("successProductTotal", "1212121212");
		param.put("successProductAmount", "12121.22");
		param.put("successProductAverageRate", "13.23");
		param.put("overdueProjectNum", "2");
		param.put("overdueMoney", "2323");
		param.put("overdue", "10.23");
		param.put("overdue90ProjectNum", "10");
		param.put("overdue90Money", "23232");
		param.put("overdue90", "0.25");
		param.put("registerInvestNumPeople", "121484545");
		param.put("accumulateInvestNumOfPeople", "484545");
		param.put("accumulateInvestNumOfPeopleNow", "2232323");
		param.put("tzTransMoney", "121223226213.25");
		param.put("averageInvestMoney", "100010.22");
		param.put("averageInvestMoneyNow", "121212.23");
		param.put("top10InvestSum", "12454");
		param.put("top10InvertConcentration", "10.25");
		param.put("top1InvestSum", "10000");
		param.put("top1InvertConcentration", "1.12");
		param.put("registerBorrowedNumPeople", "1245");
		param.put("accumulateBorrowedNumOfPeople", "123");
		param.put("accumulateBorrowedNumOfPeopleNow", "235");
		param.put("jkTransMoney", "124578");
		param.put("averageBorrowedMoney", "12345");
		param.put("averageBorrowedMoneyNow", "12354");
		param.put("top10BorrowedSum", "10000");
		param.put("top10BorrowedConcentration", "15.20");
		param.put("top1BorrowedSum", "12454");
		param.put("top1BorrowedConcentration", "10.12");
		param.put("singleLoanRedlinesNum", "1245");
		param.put("singleLoanRedlinesRate", "25");
		param.put("cumulativeCompensationAmount", "32323.26");
		param.put("cumulativeCompensationNum", "14895");

		paramList.add(param);
		String str = JSONSerializer.toJSON(paramList).toString();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:dd");
		String sendTime =sdf.format(new Date());

		long timestamp = System.currentTimeMillis();
		String nonce = Integer.toHexString(new Random().nextInt());

		JSONObject json = new JSONObject();
		json.accumulate("version", version);
		json.accumulate("batchNum", sourceCode + "_" + getTimeFormat() + "_" + System.currentTimeMillis());
		json.accumulate("checkCode", "abacaa");
		json.accumulate("totalNum", 1);
		json.accumulate("sentTime", sendTime);
		json.accumulate("sourceCode", sourceCode);
		json.accumulate("infType", "0");
		json.accumulate("dataType", "1");
		json.accumulate("timestamp", timestamp + "");
		json.accumulate("nonce",nonce );

		json.accumulate("dataList",  str );

		String result=sendPost(json, timestamp, nonce, ENDPOINT1);
		System.out.println("接口测试结果：" + result);
	}
	/**
	 * apiKey加密
	 */
	private static String getApiKey(String apiKey, String source_code, String versionStr, Long currentTime,
			String nonce) {
		//		Integer version = Integer.parseInt(versionStr);
		double version_double = Double.valueOf(versionStr);	
		int version2 =(int)(version_double*100);
		String versionHex = "0x" + Integer.toHexString(version2);
		//		String versionHex = "0x" + Integer.toHexString(version);
		StringBuilder s = new StringBuilder();
		s.append(source_code);
		//		System.out.println(versionHex);
		s.append(versionHex);
		s.append(apiKey);
		s.append(currentTime);
		s.append(nonce);
		String key = SHA.SHA256(s.toString());
		//		System.out.println(key);
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
	 * 获取全部文件路径
	 * @param path
	 * @return
	 */
	public static List<String> pathList(String path){
		List<String> list=new ArrayList<String>();
		File file=new File(path);
		if(!file.isDirectory()){
			list.add(path);

		}else{
			String[] filelist=file.list();
			for(String filepath:filelist){
				String paths=path+"\\"+filepath;
				list.add(paths);
			}
		}
		return list;

	}
	/**
	 * 读取文件
	 * 
	 * @param path - 文件路径
	 * @return
	 */
	public static String readFile(String path) {
		File file = new File(path);  
		BufferedReader reader = null;
		String tempString = "";  
		try {  
			reader = new BufferedReader(new FileReader(file));  
			tempString = reader.readLine();
			reader.close();  
		} catch (IOException e) {  
			e.printStackTrace();  
		} finally {  
			if (reader != null) {  
				try {  
					reader.close();  
				} catch (IOException e1) {  
				}  
			}  
		}  
		return tempString;  
	}  




}
