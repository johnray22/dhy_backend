package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.JiangchengDao;
import com.dao.VotesDao;
import com.dao.VotesHistoryDao;
import com.entity.JiangchengEntity;
import com.entity.VotesEntity;
import com.entity.VotesHistoryEntity;
import com.entity.view.JiangchengView;
import com.service.JiangchengService;
import com.service.VotesService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zhongruiR
 * @date 2024/4/30 11:09
 */
@Service("VotesService")
@Transactional
public class VotesServiceImpl extends ServiceImpl<VotesDao, VotesEntity> implements VotesService {

    @Autowired
    private VotesHistoryDao voteHistoryDao;
    @Override
    public boolean addVote(VotesEntity vote) {
        if (vote == null || !vote.getType().equals("vote")) {
            return false; // Ensure the type is correct for "vote"
        }
        return baseMapper.insert(vote) > 0;
    }

    @Override
    public boolean addMessage(VotesEntity message) {
        if (message == null || !message.getType().equals("message")) {
            return false; // Ensure the type is correct for "message"
        }
        return baseMapper.insert(message) > 0;
    }

    @Override
    public VotesEntity getVoteById(int id) {
        VotesEntity entity = baseMapper.selectById(id);
        if (entity != null && entity.getType().equals("vote")) {
            return entity;
        }
        return null;
    }

    @Override
    public List<VotesEntity> getAllVotesAndMessages() {
//        return baseMapper.selectList();
        return null;
    }

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<VotesEntity> page =new Query<VotesEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
