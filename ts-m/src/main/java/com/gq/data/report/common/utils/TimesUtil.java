package com.gq.data.report.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gq.data.report.common.constant.Constant;



/**
 * 时间操作类
 * @author wjw
 *
 */
public class TimesUtil {
	
	private final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd hh:mm:ss";
	private final static String YYYY_MM_DD = "yyyy-MM-dd";
	private static Logger logger = LoggerFactory.getLogger(TimesUtil.class);
	/**
	 * 程序执行所耗时间
	 * @return
	 */
	public static void getLongTimeThree(Long timeStartd) {
		Long timeEnd  =  System.currentTimeMillis();
		//输出程序运行时
		logger.info("推送数据三方响应时间**************：" + (timeEnd - timeStartd) + "ms");
	}
	/**
     * 程序执行所耗时间
     * @return
     */
    public static void getLongTime(Long timeStartd) {
    	Long timeEnd  =  System.currentTimeMillis();
        //输出程序运行时
    	logger.info("程序运行时间**************：" + (timeEnd - timeStartd) + "ms");
    }
    /**
     * 程序当前时间
     * 格式为：yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static String getCurrentTime() {
    	String format = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).format(new Date());
    	return format;
    }
    /**
     * 程序当前时间
     * 格式为：yyyy-MM-dd
     * @return
     */
    public static String getCurrentDate() {
    	String format = new SimpleDateFormat(YYYY_MM_DD).format(new Date());
    	return format;
    }
    /**
     * 程序当前时间
     * 格式为：1499841836178
     * @return
     */
    public static long getCurrentTimeMillis() {
    	return System.currentTimeMillis();
    }
    /**
     * 生成squesId
     * 格式为：1499841836178
     * @return
     */
    public static String getDataStartSign(String endSign,int count) {
    	int num = Integer.valueOf(endSign)-count;
    	return String.valueOf(num);
    }
    /**
     * 生成squesId
     * 格式为：1499841836178
     * @return
     */
    public static String getDataEndSign(String starytSign,int count) {
    	int num = Integer.valueOf(starytSign)+count;
    	return String.valueOf(num);
    }
    /**
     * 程序当前时间
     * 格式为：1499841836178
     * @return
     */
    public static String getDataStartSign(String startSign) {
    	int num = Integer.valueOf(startSign)+Integer.valueOf(Constant.DATA_NUM);
    	return String.valueOf(num);
    }

    public static void main(String[] args) {
        System.out.println(getDataStartSign("0"));
    }
}
