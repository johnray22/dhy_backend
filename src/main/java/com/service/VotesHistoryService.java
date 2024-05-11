package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.VotesEntity;
import com.entity.VotesHistoryEntity;
import com.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @author zhongruiR
 * @date 2024/4/30 11:09
 */
public interface VotesHistoryService extends IService<VotesHistoryEntity> {

    boolean hasVoted(int userId, int voteId);
    void recordVote(int userId, int voteId);
}