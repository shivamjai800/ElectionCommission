package com.electioncomission.ec.service.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.electioncomission.ec.model.SmsPojo;
import com.electioncomission.ec.service.SmsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@Service
public class SmsServiceImpl implements SmsService {
    private final String ACCOUNT_SID ="AC1272336dfaf920cf19319a0b06280b7f";

    private final String AUTH_TOKEN = "043f16a4bb78aae55f3b1e424d03d651";

    private final String FROM_NUMBER = "+14043417638";

    public void send(SmsPojo sms) throws ParseException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);


        int min = 100000;
        int max = 999999;
        int number=(int)(Math.random()*(max-min+1)+min);


        String msg ="Your OTP - "+number+ " please verify this OTP in your Application by Er Prince kumar Technoidentity.com";


        Message message = Message.creator(new PhoneNumber(sms.getMobileNumber()), new PhoneNumber(FROM_NUMBER), msg)
                .create();

      /*  Date myDate=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = sdf. parse(myDate.toString());
        long millis = date. getTime();
        System.out.println(millis);
       OTPpojo.setOtp(number);
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction
*/
    }

    public void receive(MultiValueMap<String, String> smscallback) {

    }

}
