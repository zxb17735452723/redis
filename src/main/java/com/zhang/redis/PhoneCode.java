package com.zhang.redis;

import redis.clients.jedis.Jedis;

import java.util.Random;

public  class PhoneCode {
    public static void main(String[] args) {
        String code=PhoneCode.getCode();
        System.out.println(code);
    }
    //2.每个手机每天只能发送三次，验证码放到redis中，设置过期时间
    public static void  verifyCode(String phone,String code){
        Jedis jedis=new Jedis("8.140.158.200",6379);
         jedis.auth("zxb98571654321");

          //手机发送次数key
           String  countKey="VerifyCode"+phone+":count";

          //验证码key
        String codeKey="VerifyCode"+phone+":code";
        //每个手机每天只能发送三次
        String  count=jedis.get(countKey);
    }
     //1.生成六位数字验证码
    public  static String getCode(){
        Random random=new Random();
        String code="";
        for(int i=0;i<6;i++){
         int rand=random.nextInt(10);
            code+=rand;
        }
        return code;
    }
}
