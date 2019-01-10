package com.gq.data.report.p2p.service.impl;

import com.alibaba.fastjson.JSON;
import com.gq.data.report.common.utils.ThreadPoolUtil;
import com.gq.data.report.p2p.dao.mapper.TigerHisMapper;
import com.gq.data.report.p2p.dao.mapper.TigerMapper;
import com.gq.data.report.p2p.service.TigerService;
import net.sf.json.JSONObject;
import org.cert.open.CertException;
import org.cert.open.CertToolV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Service
@Transactional
public class TigerServiceImpl implements TigerService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired private TigerMapper tigerMapper;
    @Autowired private TigerHisMapper tigerHisMapper;
    @Autowired private CertToolV1 certToolV1;

    private int doNum;
    private void setDealTotal(int i) {
        this.doNum = i;
    }

    private int getDealTotal() {
        return doNum;
    }
    @Override
    public void processTigersThreads() {

        List<Map<String, Object>> tigerList = tigerMapper.selectList(null);
        if(tigerList.isEmpty()){
            logger.warn(" 加密数据已经处理完成！");
            return;
        }
        boolean flag = doHashTigers(tigerList);
    }

    private boolean doHashTigers(List<Map<String, Object>> tigerList) {
        boolean dealRes = false;
        int threadNum = 2;

        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        //==========将：集合一份，拆分成五分，进行处理

        int splitNum = 0;
        if (!tigerList.isEmpty()) {
            splitNum = tigerList.size() / threadNum;
        }
        //====将集合：进行拆分
        JSONObject resObj = splitList(threadNum, splitNum, tigerList);

        for (int j=1;j<=threadNum;j++) {
            String listStr = "targetList"+j;
            dealUserInfoForThread(countDownLatch, (List<Map<String, Object>>) resObj.get(listStr),j);
        }
        try {
            logger.info("method[doHashUserInfos],等待【"+threadNum+"】个子线程执行完毕...");
            countDownLatch.await();
            setDealTotal(getDealTotal()+tigerList.size());
            logger.info("method[doHashUserInfos],一共【"+threadNum+"】个子线程已经执行完毕,并且成功处理了【"+getDealTotal()+"】条数据！");
            logger.info("method[doHashUserInfos],继续执行主线程");

            dealRes = true;
        } catch (InterruptedException e) {
            logger.warn("method[doHashUserInfos]，清洗：用户数据，系统异常！",e);
        }
        return dealRes;
    }
    private JSONObject splitList(int endIndex, int splitNum, List<Map<String, Object>> targetList) {
        JSONObject obj = new JSONObject();
        for (int i=0;i<endIndex;i++) {
            int remainData = 0;
            if ((i+1) == endIndex) {
                //====判断，截取集合大小是和payAccList大小，进行比较
                if (targetList.size() > (i+1)*splitNum) {
                    remainData = targetList.size() - (i+1)*splitNum;
                }
            }
            List<Map<String, Object>> tempList = targetList.subList(i*splitNum, (i+1)*splitNum+remainData);
            obj.put("targetList"+(i+1),tempList);
        }

        return obj;
    }

    /**
     * 使用：多线程处理账户业务
     * @param countDownLatch
     * @param targetThreadList
     */
    private void dealUserInfoForThread(CountDownLatch countDownLatch,List<Map<String, Object>> targetThreadList,int j) {
        ThreadPoolTaskExecutor taskExecutor = ThreadPoolUtil.taskExecutor;

        taskExecutor.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        //================真正处理业务
                        doSomeThings(targetThreadList,j);
                        logger.info("method[dealUserInfoForThread],使用线程池进行处理业务，《"+Thread.currentThread().getName()+"》，执行批次："+j);
                        countDownLatch.countDown();
                    }
                }
        );
    }


    private void doSomeThings(List<Map<String, Object>> list,int j) {
        List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();

        logger.info("method[doSomeThings],使用线程池进行处理业务，循环执行第"+j+"批；用户数据加密处理：线程号《"+Thread.currentThread().getName()+"》");
        for (int i = 0; i < list.size(); i++) {

            try{
                String pass= (String) list.get(i).get("password");
                list.get(i).put("password", certToolV1.idCardHash((String) list.get(i).get("password")));

                newList.add(list.get(i));
            }catch(CertException ce){
                logger.warn("certToolV1 工具加密问题,问题数据："+ JSON.toJSONString(list.get(i)));
                logger.warn(ce.getErrorCode()+":"+ce.getMessage());
                //问题数据入库

            } catch (Exception e) {

                logger.warn("执行批处理系统异常:异常批次号："+j,e);
            }
        }
        if(newList.size()>0){
            logger.info("批次号"+j+";成功处理数据量"+newList.size());
            tigerHisMapper.insertBatch(newList);
            newList.clear();
        }
        logger.info("method[doSomeThings],使用线程池进行处理业务，批量用户数据加密处理：线程号《"+Thread.currentThread().getName()+"》");
    }


}
