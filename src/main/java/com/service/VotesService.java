package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.KaoqinEntity;
import com.entity.VotesEntity;
import com.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @author zhongruiR
 * @date 2024/4/30 11:09
 */
public interface VotesService extends IService<VotesEntity> {
    boolean addVote(VotesEntity vote); // 添加投票
    boolean addMessage(VotesEntity message); // 添加留言
    VotesEntity getVoteById(int id); // 根据ID获取投票详情
    List<VotesEntity> getAllVotesAndMessages(); // 获取所有投票和留言

    PageUtils queryPage(Map<String, Object> params);


}