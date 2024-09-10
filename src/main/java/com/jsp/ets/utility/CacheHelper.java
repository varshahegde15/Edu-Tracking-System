package com.jsp.ets.utility;

import com.jsp.ets.batch.Batch;
import com.jsp.ets.exception.InvalidOtpException;
import com.jsp.ets.exception.RegistrationSessionExpiredException;
import com.jsp.ets.rating.Rating;
import com.jsp.ets.user.User;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheHelper {

    @CachePut(cacheNames ="non_verified_users", key = "#user.email")
    public User userCache(User user){
        return user;
    }

    @CachePut(cacheNames = "otps",key = "#email")
    public Integer otpCache(Integer otp, String email){
        return otp;
    }

    @Cacheable(cacheNames = "otps", key = "#email")
    public Integer getCachedOtp(String email) {
        System.out.println("Returning new otp 0");
        return 0;
    }

    @Cacheable(cacheNames = "non_verified_users", key = "#email")
    public User getRegisteringUser(String email) {
        System.out.println("Returning new user");
        return new User();
    }

    @CachePut(cacheNames ="batch_cache", key = "#batch.title")
    public Batch batchCache(Batch batch){
        return batch;
    }

    @CachePut(cacheNames ="batch_cache", key = "#title")
    public Batch getBatchCache(String title){return null;}
}
