package com.electioncomission.ec.service;

import com.electioncomission.ec.model.SmsPojo;
import com.twilio.twiml.voice.Sms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;

import java.text.ParseException;

public interface SmsService {


    public void send(SmsPojo sms) throws ParseException;

    public void receive(MultiValueMap<String, String> smscallback);
}
