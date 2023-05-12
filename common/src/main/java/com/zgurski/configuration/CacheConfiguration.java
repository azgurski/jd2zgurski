package com.zgurski.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfiguration {
    @Bean
    public CacheManager cacheManager() {
        //помещаются те данные, которые чаще всего вытаскиваются и имеют меньше шанс изменений
        // times, roles, billing_data.. справочные

        CaffeineCacheManager cacheManager = new CaffeineCacheManager("h_restaurants"); // var args ..
        cacheManager.setCaffeine(cacheProperties());
        return cacheManager;
    }

    public Caffeine<Object, Object> cacheProperties() {
        return Caffeine.newBuilder()
                .initialCapacity(30) // сколько хранить
                .maximumSize(200)
                .expireAfterAccess(20, TimeUnit.SECONDS) // через сколько удалить объекты из коллекции (на это время не добавляются новые записи в базе)
//                .expireAfterWrite()
                .weakKeys() // в зависимости от ссылки на объект, тот или иной объект будет попадать под сборщик мусора.Удаляются сначала weak, (strong, phantom, soft)
                .recordStats();
    }
}
