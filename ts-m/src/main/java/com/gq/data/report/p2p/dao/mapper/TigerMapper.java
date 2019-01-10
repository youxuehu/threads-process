package com.gq.data.report.p2p.dao.mapper;

import com.gq.data.report.p2p.dao.model.Tiger;
import com.gq.data.report.p2p.dao.model.TigerExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TigerMapper {
    int countByExample(TigerExample example);

    int deleteByExample(TigerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tiger record);

    int insertSelective(Tiger record);

    List<Tiger> selectByExample(TigerExample example);

    Tiger selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tiger record, @Param("example") TigerExample example);

    int updateByExample(@Param("record") Tiger record, @Param("example") TigerExample example);

    int updateByPrimaryKeySelective(Tiger record);

    int updateByPrimaryKey(Tiger record);

    List<Map<String,Object>> selectList(Map<String,Object> map);
}