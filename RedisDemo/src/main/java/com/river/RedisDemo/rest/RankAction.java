package com.river.RedisDemo.rest;

import com.river.RedisDemo.component.RankListComponent;
import com.river.RedisDemo.modal.RankDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by @author yihui in 20:16 18/12/25.
 */
@RestController
@RequestMapping("/rank")
public class RankAction {
    @Autowired
    private RankListComponent rankListComponent;

    @GetMapping(path = "/topn")
    public List<RankDO> showTopN(int n) {
        return rankListComponent.getTopNRanks(n);
    }

    @GetMapping(path = "/update")
    public RankDO updateScore(long userId, float score) {
        return rankListComponent.updateRank(userId, score);
    }

    @GetMapping(path = "/rank")
    public RankDO queryRank(long userId) {
        return rankListComponent.getRank(userId);
    }

    @GetMapping(path = "/around")
    public List<RankDO> around(long userId, int n) {
        return rankListComponent.getRankAroundUser(userId, n);
    }

}
