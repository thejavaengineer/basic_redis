package org.thejavaengineer;

import redis.clients.jedis.Jedis;

public class RedisExample {
    public static void main(String[] args) {
        try (Jedis jedis = new Jedis("localhost", 6379) ) {
            long startSet = System.nanoTime();
            jedis.set("mykey", "hello, redis!");
            long endSet = System.nanoTime();

            long startGet = System.nanoTime();
            String mykey = jedis.get("mykey");
            long endGet = System.nanoTime();

            System.out.println((endSet - startSet)/1000000.0);
            System.out.println((endGet - startGet)/1000000.0);
            System.out.println(mykey);
        }
    }
}
