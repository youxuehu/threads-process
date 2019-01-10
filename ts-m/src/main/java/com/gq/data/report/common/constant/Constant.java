package com.gq.data.report.common.constant;

public class Constant {
	/* *************生成流水号*************现在不做区分
	 * 01~07分别代表Redis中的key,用于计数。用于流水号
	 * 01：用户信息；02：散标数据；03散标状态；04：产品信息；05：产品散标配置；06：债权信息；07：交易流水；08：还款计划；09：转让项目 ；10：转让状态；11：承接信息
	 */
	public static final String REDIS_REPORT_BATCH = "REDIS_REPORT_BATCH";
	
	/**
	 *                            &&&&&&&&&&&
	 * ***************************这里用于存量数据推送时的数据位置标识记录****************************
	 * 01：用户信息；02：散标数据；03散标状态；04：产品信息；05：产品散标配置；06：债权信息；07：交易流水；08：还款计划；09：转让项目 ；10：转让状态；11：承接信息
	 */
	public static final String REDIS_REPORT_BATCH_SIGN_01 = "REDIS_REPORT_BATCH_SIGN_01";
	public static final String REDIS_REPORT_BATCH_SIGN_02 = "REDIS_REPORT_BATCH_SIGN_02";
	public static final String REDIS_REPORT_BATCH_SIGN_03 = "REDIS_REPORT_BATCH_SIGN_03";
	public static final String REDIS_REPORT_BATCH_SIGN_04 = "REDIS_REPORT_BATCH_SIGN_04";
	public static final String REDIS_REPORT_BATCH_SIGN_05 = "REDIS_REPORT_BATCH_SIGN_05";
	public static final String REDIS_REPORT_BATCH_SIGN_06 = "REDIS_REPORT_BATCH_SIGN_06";
	public static final String REDIS_REPORT_BATCH_SIGN_07 = "REDIS_REPORT_BATCH_SIGN_07";
	public static final String REDIS_REPORT_BATCH_SIGN_08 = "REDIS_REPORT_BATCH_SIGN_08";
	public static final String REDIS_REPORT_BATCH_SIGN_09 = "REDIS_REPORT_BATCH_SIGN_09";
	public static final String REDIS_REPORT_BATCH_SIGN_10 = "REDIS_REPORT_BATCH_SIGN_10";
	public static final String REDIS_REPORT_BATCH_SIGN_11 = "REDIS_REPORT_BATCH_SIGN_11";
	/*
	 * 每个接口执行完成后记录执行的位置，便于下一次开始标识,
	 * ***************************************这个redis的key用于定时任务标记**********************
	 * 01~07分别代表Redis中的key
	 * 01：用户信息；02：散标数据；03散标状态；04：产品信息；05：产品散标配置；06：债权信息；07：交易流水；08：还款计划；09：转让项目 ；10：转让状态；11：承接信息
	 */
	public static final String REDIS_REPORT_SIGN_01 = "REDIS_REPORT_SIGN_01";
	public static final String REDIS_REPORT_SIGN_02 = "REDIS_REPORT_SIGN_02";
	public static final String REDIS_REPORT_SIGN_03 = "REDIS_REPORT_SIGN_03";
	public static final String REDIS_REPORT_SIGN_04 = "REDIS_REPORT_SIGN_04";
	public static final String REDIS_REPORT_SIGN_05 = "REDIS_REPORT_SIGN_05";
	public static final String REDIS_REPORT_SIGN_06 = "REDIS_REPORT_SIGN_06";
	public static final String REDIS_REPORT_SIGN_07 = "REDIS_REPORT_SIGN_07";
	public static final String REDIS_REPORT_SIGN_08 = "REDIS_REPORT_SIGN_08";
	public static final String REDIS_REPORT_SIGN_09 = "REDIS_REPORT_SIGN_09";
	public static final String REDIS_REPORT_SIGN_10 = "REDIS_REPORT_SIGN_10";
	public static final String REDIS_REPORT_SIGN_11 = "REDIS_REPORT_SIGN_11";
	/**
	 * 用于批量处理用户加密数据，并入库。解决三方工具加密慢的问题；
	 */
	public static final String REDIS_REPORT_SIGN_HSI = "REDIS_REPORT_SIGN_HSI";
	/*
	 * 每个接口对应的表名
	 * 01~07分别代表Redis中的key
	 * 01：用户信息；02：散标数据；03散标状态；04：产品信息；05：产品散标配置；06：债权信息；07：交易流水；08：还款计划；09：转让项目 ；10：转让状态；11：承接信息
	 */
	public static final String REDIS_REPORT_TABLENAME_01 = "P2P_USERINFO";
	public static final String REDIS_REPORT_TABLENAME_02 = "P2P_SCATTERINVEST";
	public static final String REDIS_REPORT_TABLENAME_03 = "P2P_STATUS";
	public static final String REDIS_REPORT_TABLENAME_04 = "P2P_FINANCE";
	public static final String REDIS_REPORT_TABLENAME_05 = "P2P_FINANCESCATTERCONFIG";
	public static final String REDIS_REPORT_TABLENAME_06 = "P2P_TRANSFER";
	public static final String REDIS_REPORT_TABLENAME_07 = "P2P_TRANSACT";
	public static final String REDIS_REPORT_TABLENAME_08 = "P2P_REPAYMENTPLAN";
	public static final String REDIS_REPORT_TABLENAME_09 = "P2P_TRANSFERPROJECT";
	public static final String REDIS_REPORT_TABLENAME_10 = "P2P_TRANSFERSTATUS ";
	public static final String REDIS_REPORT_TABLENAME_11 = "P2P_TAKEINFOMATION";
	public static final String REDIS_REPORT_TABLENAME_12 = "P2P_USERINFO_HIS";
	public static final String REDIS_REPORT_TABLENAME_HIS_02 = "P2P_SCATTERINVEST_HIS";
	public static final String REDIS_REPORT_TABLENAME_HIS_03 = "P2P_STATUS_HIS";
	public static final String REDIS_REPORT_TABLENAME_HIS_04 = "P2P_FINANCE_HIS";
	public static final String REDIS_REPORT_TABLENAME_HIS_05 = "P2P_FINANCESCATTERCONFIG_HIS";
	public static final String REDIS_REPORT_TABLENAME_HIS_06 = "P2P_TRANSFER_HIS";
	public static final String REDIS_REPORT_TABLENAME_HIS_07 = "P2P_TRANSACT_HIS";
	public static final String REDIS_REPORT_TABLENAME_HIS_08 = "P2P_REPAYMENTPLAN_HIS";
	public static final String REDIS_REPORT_TABLENAME_HIS_09 = "P2P_TRANSFERPROJECT_HIS";
	public static final String REDIS_REPORT_TABLENAME_HIS_10 = "P2P_TRANSFERSTATUS_HIS";
	public static final String REDIS_REPORT_TABLENAME_HIS_11 = "P2P_TAKEINFOMATION_HIS";
	/*
	 * 每次查询数据库的条数
	 */
	public static final String DATA_NUM = "5000";
	/*
	 *  每次传输给数据中心的数据量大小;暂时定位1000，他们那里处理太多耗时太久
	 */
	public static final int PUSH_DATA_NUM = 1000;
	/**
	 * *************************手动调用时请求的参数*******************
	 * 1：用户信息；2：散标数据；3:散标状态；4：产品信息；
	 * 5：产品散标配置；6：债权信息；7：交易流水；8：还款计划；9：转让项目 ；10：转让状态；11：承接信息
	 */
	public static final String DATA_TYPE_01 = "1";
	public static final String DATA_TYPE_02 = "2";
	public static final String DATA_TYPE_03 = "3";
	public static final String DATA_TYPE_04 = "4";
	public static final String DATA_TYPE_05 = "5";
	public static final String DATA_TYPE_06 = "6";
	public static final String DATA_TYPE_07 = "7";
	public static final String DATA_TYPE_08 = "8";
	public static final String DATA_TYPE_09 = "9";
	public static final String DATA_TYPE_10 = "10";
	public static final String DATA_TYPE_11 = "11";
	public static final String DATA_TYPE_000 = "000";
	public static final String DATA_TYPE_0001 = "0001";//用户加密后的数据推送类型
	public static final String DATA_TYPE_0002 = "0002";//用户加密后的数据推送类型
	public static final String DATA_TYPE_999 = "999";
	
	/**
	 * 推送三方时对应的接口类型
	 * 1--用户接口，2--散标接口，3--产品接口，
	 * 4--流水接口，5—债权转 让接口(****已失效***)，6--散标状态接口，
	 * 7--预期收益接口，10—产品散标配置接 口;
	 * 81：还款计划接口；82：债权信息接口；83：转让项目接口；
	 * 84：转让状态接口；85：承接信息
	 */
	public static final String INF_TYPE_01 = "1";
	public static final String INF_TYPE_02 = "2";
	public static final String INF_TYPE_03 = "3";
	public static final String INF_TYPE_04 = "4";
	public static final String INF_TYPE_05 = "5";//****已失效
	public static final String INF_TYPE_06 = "6";
	public static final String INF_TYPE_07 = "7";
	public static final String INF_TYPE_10 = "10";
	public static final String INF_TYPE_81 = "81";
	public static final String INF_TYPE_82 = "82";
	public static final String INF_TYPE_83 = "83";
	public static final String INF_TYPE_84 = "84";
	public static final String INF_TYPE_85 = "85";
	
	/**
	 * 接口数据类型；0：调试数据；1 正式数据（接口联调阶段传 0，正式推数据阶段传 1）
	 * 
	 */
	public static final String PUSH_DATA_TYPE_TEST = "0";
	public static final String PUSH_DATA_TYPE_FORMAL = "1";
	
	public static final String CRONVLUE_SECOND = "0 */15 * * * ?";//"*/20 * * * * ?";
	public static final String CRONVLUE_MINUTE = "0 */15 * * * ?";
	
	/**
	 * 异步查询接口url
	 */
	public static final String   YIBUMESSAGE  = "https://api.ifcert.org.cn/balanceService/v15/yiBuMessage";
	public static final String   BATCHLIST  = "https://api.ifcert.org.cn/balanceService/v15/batchNum";
	public static final String   DELETEDATA  = "https://api.ifcert.org.cn/balanceService/v15/deleteData";
}
