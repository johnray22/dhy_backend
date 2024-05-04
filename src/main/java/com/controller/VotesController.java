package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.HuidaEntity;
import com.entity.VoteRequest;
import com.entity.VotesEntity;
import com.entity.XueshengEntity;
import com.entity.view.HuidaView;
import com.entity.view.LaoshiView;
import com.service.DictionaryService;
import com.service.VotesService;
import com.utils.PageUtils;
import com.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhongruiR
 * @date 2024/5/4 11:35
 */
@RestController
@Controller
@RequestMapping("/votes")
public class VotesController {
    private static final Logger logger = LoggerFactory.getLogger(KaoqinController.class);

    @Autowired
    private VotesService votesService;
    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(), JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("学生".equals(role))
            params.put("xueshengId",request.getSession().getAttribute("userId"));
        else if("老师".equals(role))
            params.put("laoshiId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = votesService.queryPage(params);
        //字典表数据转换
        List<VotesEntity> list =(List<VotesEntity>)page.getList();
        for(VotesEntity c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }

        return R.ok().put("data", page);
    }

    @RequestMapping("/save")
    public R save(@RequestBody VotesEntity votesEntity, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,huida:{}",this.getClass().getName(),votesEntity.toString());
        votesEntity.setUsername(String.valueOf(request.getSession().getAttribute("username")));
        votesEntity.setTimestamp(new Date());
        votesService.insert(votesEntity);
        return R.ok();

    }

    @PostMapping("/vote")
    public ResponseEntity<R> vote(@RequestBody VoteRequest voteRequest) {
        try {
            Wrapper<VotesEntity> queryWrapper = new EntityWrapper<VotesEntity>()
                    .eq("id", voteRequest.getVoteId());

            logger.info("sql语句:"+queryWrapper.getSqlSegment());
            VotesEntity vote = votesService.selectOne(queryWrapper);
            switch (voteRequest.getSelectedOption()) {
                case "option1_num":
                    vote.setOption1Num(vote.getOption1Num() + 1);
                    break;
                case "option2_num":
                    vote.setOption2Num(vote.getOption2Num() + 1);
                    break;
                case "option3_num":
                    vote.setOption3Num(vote.getOption3Num() + 1);
                    break;
                case "option4_num":
                    vote.setOption4Num(vote.getOption4Num() + 1);
                    break;
                default:
                    return ResponseEntity.badRequest().body(new R("无效的选项"));
            }
            votesService.updateById(vote);
            return ResponseEntity.ok(new R("投票成功"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new R("投票过程中发生错误"));
        }
    }
}
