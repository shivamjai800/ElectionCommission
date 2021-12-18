package com.electioncomission.ec.service;

import com.electioncomission.ec.entity.Users;


public interface SmsService {

    public String generateRandomOtp(int len);
    public String send(Users users);


}
