package com.zhang.redis;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import java.util.List;
import java.util.Set;
public class JedisDemo1 {
    public static void main(String[] args) {
        //创建Jedis对象
        Jedis jedis = new Jedis("8.140.158.200", 6379);
        jedis.auth("zxb98571654321");
        //测试
        String value = jedis.ping();
        System.out.println(value);
    }
        @Test //操作String
        public void demo1(){
        Jedis jedis=new Jedis("8.140.158.200",6379);
        jedis.auth("zxb98571654321");
        //测试
            jedis.set("name","lucy");
            String name = jedis.get("name");
            System.out.println(name);
            jedis.mset("k1","v1","k2","v2");
            List<String> mget = jedis.mget("k1", "k2");
            System.out.println(mget);
            Set<String> keys = jedis.keys("*");
            for(String key:keys){
                System.out.println(key);
            }
        }

        @Test //操做list
        public void demo2(){
            Jedis jedis=new Jedis("8.140.158.200",6379);
            jedis.auth("zxb98571654321");
            //测试
            jedis.lpush("key1","lucy","marry","jack");
            List<String> key1 = jedis.lrange("key1", 0, -1);
            for(int i=0;i<key1.size();i++){
                System.out.println(key1.get(i));
            }
}

   @Test
    public void demo3(){//操作set
       Jedis jedis = new Jedis("8.140.158.200",6379);
       jedis.auth("zxb98571654321");

       jedis.sadd("names", "marry", "lucy");
       Set<String> names = jedis.smembers("names");
       System.out.println(names);
   }

   @Test //操作hash
    public  void demo4(){
       Jedis jedis = new Jedis("8.140.158.200",6379);
       jedis.auth("zxb98571654321");

       jedis.hset("users","age","20");
       String hget=jedis.hget("users","age");
       System.out.println(hget);


   }
    @Test //操作zset
    public  void demo5() {
        Jedis jedis = new Jedis("8.140.158.200", 6379);
        jedis.auth("zxb98571654321");
        jedis.zadd("china", 100d, "shanghai");
        Set<String> china = jedis.zrange("china", 0, -1);
       //sout
        System.out.println(china);
    }

}

