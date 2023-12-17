package likelion.togethermarket.global.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

//@Service
//@RequiredArgsConstructor
//public class RedisService {
//    private final RedisTemplate<String, String> redisTemplate;
//
//    //redis에 <key, value> 삽입, timeout 분 동안 유지
//    public void setValue(String key, String value, Long timeout){
//        //ValueOperations 는 Redis에서 값에 대한 기본적인 연산을 수행할 수 있는 메서드 제공
//        ValueOperations<String, String> values = redisTemplate.opsForValue();
//        values.set(key, value, Duration.ofMinutes(timeout));
//    }
//
//    // key를 value로 반환
//    public String getValue(String key){
//        ValueOperations<String, String> values = redisTemplate.opsForValue();
//        return values.get(key);
//    }
//
//    // key를 이용해 <key, value> 삭제, 삭제하는 key의 value를 return
//    public String deleteValue(String key){
//        ValueOperations<String, String> values = redisTemplate.opsForValue();
//        return values.getAndDelete(key);
//    }
//}
