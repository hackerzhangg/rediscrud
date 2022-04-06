package com.cx.rediscrud.controller;

import com.cx.rediscrud.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("interface/redis")
public class RedisController {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 向缓存中添加数据
     * @return
     */
    @GetMapping("setRedisData")
    public Object setRedisData(String params){

        Boolean setRedisData = redisUtil.set("DomeValue1",params);
        return setRedisData;
    }

    /**
     * 向缓存中添加数据并设置过期时间
     * @return
     */
    @RequestMapping("setRedisDataTime")
    public Boolean setRedisDataTime(String params){

        boolean setRedisDataTime = redisUtil.set("DomeValue2", params, 20);
        return setRedisDataTime;
    }

    /**
     * 获取缓存Key的过期时间
     * @return
     */
    @RequestMapping("getExpireTime")
    public Long getExpireTime(){
        return redisUtil.getExpire("DomeValue2");
    }

    /**
     * 判断key是否存在
     * @param params
     * @return
     */
    @RequestMapping("hasKey")
    public Boolean hasKey(String params){
        return redisUtil.hasKey(params);
    }

    /**
     * 删除key
     * @return
     */
    @RequestMapping("del")
    public String del(){
        redisUtil.del("DomeValue1");
        return "success";
    }

    /**
     * 获取缓存key信息
     * @return
     */
    @RequestMapping("get")
    public String get(String params){
        String getData = (String) redisUtil.get(params);
        return getData;
    }

    /**
     * 将List放入Redis缓存
     * @return
     */
    @RequestMapping("serRedisList")
    public Boolean serRedisList(){
        List<Object> objectList=new ArrayList<>();
        objectList.add("张三");
        objectList.add("李四");
        objectList.add("王五");

        Boolean b = redisUtil.lSet("DomeValue3",objectList);
        return b;
    }
}
