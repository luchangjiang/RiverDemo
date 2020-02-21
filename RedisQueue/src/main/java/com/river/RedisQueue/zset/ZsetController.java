package com.river.RedisQueue.zset;

/**
 * @author ：River
 * @date ：Created in 7/19/2019 12:29 PM
 * @description：
 * @modified By：
 * @version: $
 */
import com.alibaba.fastjson.JSON;
import com.river.RedisQueue.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * redis的zset使用
 */
@Controller
@RequestMapping(value = "/redis/zset")
public class ZsetController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZsetController.class);

    private static final String ZSET_KEY = "zset_test_key";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 0、向zset中初始化10个元素
     * @return
     */
    @RequestMapping(value = "/init")
    @ResponseBody
    public String init() {
        for (int i = 0; i < 12; i++) {
            String userIdStr = "" + (i + 1);
            double scoreDou = (i + 1) * 100;
            redisTemplate.opsForZSet().add(ZSET_KEY, userIdStr, scoreDou);
        }
        // 获取所有元素
        Set<ZSetOperations.TypedTuple<Object>> listSet = redisTemplate.opsForZSet().reverseRangeWithScores(ZSET_KEY, 0, -1);
        return JSON.toJSONString(listSet);
    }

    /**
     * 1、向zset中添加一个元素
     * @param userId
     * @param score
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public String zset(Long userId, Double score) {
        // 必须将Long 转为 String , 否则包类型转换错误
        boolean isOk = redisTemplate.opsForZSet().add(ZSET_KEY, userId.toString(), score);

        return JSON.toJSONString(isOk);
    }

    /**
     * 2、根据userId获取对应的分数
     * @return
     */
    @RequestMapping(value = "/getScoreByUserId")
    @ResponseBody
    public String getScoreByUserId(Long userId) {
        Double score = redisTemplate.opsForZSet().score(ZSET_KEY, userId.toString());
        if (score == null) {
            score = 0d;
        }
        return score.toString();
    }

    /**
     * 3、根据分数倒序排(分数高--到--分数低)，取5条数据的userId
     *
     * @return
     */
    @RequestMapping(value = "/getTop5UserId")
    @ResponseBody
    public String getTop5UserId() {
        // 倒序取5条数据
        int topNum = 5;
        Set<Object> userIdSet = redisTemplate.opsForZSet().reverseRange(ZSET_KEY, 0, topNum - 1);
        return JSON.toJSONString(userIdSet);
    }

    /**
     * 4、根据分数倒序排(分数高--到--分数低)，取5条数据的userId、score
     * @return
     */
    @RequestMapping(value = "/getTop5UserIdAndScore")
    @ResponseBody
    public String getTop5UserIdAndScore() {
        List<Map<String, Object>> list = new ArrayList<>();
        // 倒序取5条数据
        int topNum = 5;
        // 倒序排序获取value和分数
        Set<ZSetOperations.TypedTuple<Object>> listSet = redisTemplate.opsForZSet().reverseRangeWithScores(ZSET_KEY, 0, topNum - 1);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = listSet.iterator();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
            Object value = typedTuple.getValue();
            double score = typedTuple.getScore();

            Map<String, Object> map = new HashMap<>();
            map.put("userId", Long.parseLong(value.toString()));
            map.put("score", score);
            list.add(map);
        }
        return JSON.toJSONString(list);
    }

    /**
     * 5、设置key的有效期
     * @return
     */
    @RequestMapping(value = "/expireAt")
    @ResponseBody
    public String expireAt() {

        // 当前时间加100秒过期
        Date expireDate = org.joda.time.DateTime.now().plusSeconds(100).toDate();

        redisTemplate.expireAt(ZSET_KEY, expireDate);

        Long expireTime = redisTemplate.getExpire(ZSET_KEY);

        return expireTime.toString();
    }

    /**
     * 6、删除超过最大数量的元素
     * @return
     */
    @RequestMapping(value = "/removeOverNum")
    @ResponseBody
    public String removeOverNum() {
        int maxNum = 9;
        long redisNum = redisTemplate.opsForZSet().size(ZSET_KEY);
        // key存在且超出limit
        if (redisNum > maxNum) {
            // 从头删除redisNum - maxNum个元素（升序）
            redisTemplate.opsForZSet().removeRange(ZSET_KEY, 0, redisNum - maxNum - 1);
        }

        Set<ZSetOperations.TypedTuple<Object>> listSet = redisTemplate.opsForZSet().reverseRangeWithScores(ZSET_KEY, 0, -1);
        return JSON.toJSONString(listSet);
    }

    /**
     * 7、key是否存在
     * @return
     */
    @RequestMapping(value = "/hasKey")
    @ResponseBody
    public Boolean hasKey() {
        return redisTemplate.hasKey(ZSET_KEY);
    }


}