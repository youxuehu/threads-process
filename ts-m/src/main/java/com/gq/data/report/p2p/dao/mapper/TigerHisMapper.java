package com.gq.data.report.p2p.dao.mapper;

import com.gq.data.report.p2p.dao.model.TigerHis;
import com.gq.data.report.p2p.dao.model.TigerHisExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TigerHisMapper {
    int countByExample(TigerHisExample example);

    int deleteByExample(TigerHisExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TigerHis record);

    int insertSelective(TigerHis record);

    List<TigerHis> selectByExample(TigerHisExample example);

    TigerHis selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TigerHis record, @Param("example") TigerHisExample example);

    int updateByExample(@Param("record") TigerHis record, @Param("example") TigerHisExample example);

    int updateByPrimaryKeySelective(TigerHis record);

    int updateByPrimaryKey(TigerHis record);

    void insertBatch(List<Map<String, Object>> newList);
}