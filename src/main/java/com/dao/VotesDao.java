package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.VotesEntity;
import com.entity.view.KaoqinView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author zhongruiR
 * @date 2024/4/30 11:07
 */
public interface VotesDao extends BaseMapper<VotesEntity> {

    List<VotesEntity> selectListView(Pagination page, @Param("params") Map<String,Object> params);

}
