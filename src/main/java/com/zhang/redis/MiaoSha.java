package com.zhang.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
public class MiaoSha {

    public static void main(String[] args) {
        Jedis jedis=new Jedis("8.140.158.200",6379);
        jedis.auth("zxb98571654321");
        System.out.println(jedis.ping());
        jedis.close();
    }

//秒杀过程
    @Test
    public   void    doSecKill(String uid,String prodid){
        //1.uid和profid非空判断
        if(uid==null||prodid==null){
            return ;
        }
        //2.连接redis
        Jedis jedis=new Jedis("8.140.158.200",6379);
        jedis.auth("zxb98571654321");



        //3.1库存
        String keKey="ask:"+prodid+":qt";
        //3.2秒杀成功用户key
        String userKey="ask:"+prodid+":user";
       //4.获取库存，如果库存null,秒杀还没有开始
        String s = jedis.get(keKey);
        if(s==null){
            System.out.println("秒杀还未开始");
            jedis.close();
           // return false;
        }
        //5.判断用户是否重复秒杀
       if(jedis.sismember(userKey, uid)){
           System.out.println("已经秒杀成功了，不能重复秒杀");
           jedis.close();
          // return false;
       }
       //6.判断如果商品数量，库存数量小于1，秒杀结束
        if(Integer.parseInt(s)<=0){
            System.out.println("秒杀已经结束了");
            jedis.close();
           // return false;
        }
        //7.秒杀过程
        jedis.decr(s);
        jedis.sadd(userKey,uid);
        System.out.println("秒杀成功了");
        jedis.close();
      //  return true;
    }
}
