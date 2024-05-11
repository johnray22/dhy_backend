package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.VotesDao;
import com.dao.VotesHistoryDao;
import com.entity.VotesEntity;
import com.entity.VotesHistoryEntity;
import com.service.VotesHistoryService;
import com.service.VotesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhongruiR
 * @date 2024/5/11 15:11
 */
@Service
@Slf4j
public class VotesHistoryServiceImpl extends ServiceImpl<VotesHistoryDao, VotesHistoryEntity> implements VotesHistoryService {
    @Override
    public boolean hasVoted(int userId, int voteId) {
        // 检查是否存在用户投票历史记录
        return baseMapper.selectCount(new EntityWrapper<VotesHistoryEntity>()
                .eq("user_id", userId)
                .eq("vote_id", voteId)) > 0;
    }

    @Override
    public void recordVote(int userId, int voteId) {
        // 插入新的投票历史记录
        VotesHistoryEntity history = new VotesHistoryEntity();
        history.setUserId(userId);
        history.setVoteId(voteId);
        baseMapper.insert(history);
        log.info("==============votes history recorded: userId={}, voteId={}==============", userId, voteId);
    }
}
