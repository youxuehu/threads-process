package com.gq.data.report.common.http;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.cert.open.CertException;
import org.cert.open.CertToolV1;
import org.cert.utils.SHA;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gq.base.jedis.JedisHelper;
import com.gq.data.report.common.constant.Constant;
import com.gq.data.report.common.utils.DateFormatUtils;
import com.gq.data.report.common.utils.TimesUtil;

/**
 * 自己用
 *
 * @author wjw
 */
@Component
public class GqCline {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    JedisHelper jedisHelper;
    @Autowired
    RedissonClient redissonClient;

    @Autowired
    private CertToolV1 certToolV1;

    public String sendGet(String url) {
    	String rtnMsg = HttpsUtils.getReq(url);
    	return rtnMsg;
    }

    /**
     * 获取phoneHash 和userUUid
     *
     * @param phonenum
     * @return
     * @throws CertException
     */
    public Map<String, String> getPhoneAndSalt(String phonenum) throws CertException {
        Map<String, String> map = new HashMap<String, String>();
        JSONObject json = certToolV1.phoneHash(phonenum);
        String phoneHash = json.getString("phone");
        String salt = json.getString("salt");
        map.put("phoneHash", phoneHash);
        map.put("userUuid", salt);
        return map;
    }

    /**
     * 获取phoneHash 和userUUid
     *
     * @return
     * @throws CertException
     */
    public void getPhoneAndSalt_01(Map<String, Object> map) throws CertException {

        JSONObject json = certToolV1.phoneHash((String) map.get("phonenum"));
        String phoneHash = json.getString("phone");
        String salt = json.getString("salt");
        map.put("phoneHash", phoneHash);
        map.put("userUuid", salt);
    }

    /**
     * 生成序列号seqId
     *
     * @return
     */
    public String generateSeqIdCode(String batchNumberType) {
        Date currentDate = new Date();
        String data = DateFormatUtils.formatDate(currentDate, "yyyyMMdd");
        String dataTime = DateFormatUtils.formatDate(currentDate, "yyyyMMdd");
        RLock lock = redissonClient.getLock(dataTime);
        lock.lock();
        RBucket rBucket = redissonClient.getBucket(batchNumberType + data);
        Long count = 1l;
        if (rBucket.isExists()) {
            count = (Long) rBucket.get() + 1;
            rBucket.set(count, 1, TimeUnit.DAYS);
        } else {
            redissonClient.getBucket(batchNumberType + data).set(count, 1, TimeUnit.DAYS);
        }

        String newSeq = String.format("%07d", count);
        lock.unlock();
        logger.info("当前时间为：" + dataTime + "，生成的订单号为：" + (dataTime + newSeq));
        return dataTime + newSeq;
    }

    public String getApiKey(String apiKey, String source_code, String versionStr, Long currentTime, String nonce) {

        double version_double = Double.valueOf(versionStr);
        int version = (int) (version_double * 100);
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
     * 后4位使用*替换
     *
     * @param phone
     * @return
     */
    public String midleReplaceStar(String phone) {
        StringBuilder builder = new StringBuilder();
        if (!StringUtils.isEmpty(phone)) {
            String substring = phone.substring(0, phone.length() - 4);
            builder.append(substring).append("****");
        }
        return builder.toString();
    }

    /**
     * 获取对应数据接口对应的redis指针位置
     *
     * @param dataType
     * @return
     */
    public Map<String, String> getRedisPageInf(String dataType) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("pageSize", Constant.DATA_NUM);
        String startSign = jedisHelper.get(dataType);
        if (null == startSign || "null".equals(startSign) || "".equals(startSign)) {
            startSign = "0";
            jedisHelper.set(dataType, startSign);
        } else {
            map.put("startSign", startSign);
            map.put("endSign", TimesUtil.getDataStartSign(startSign));
        }
        return map;
    }


}
