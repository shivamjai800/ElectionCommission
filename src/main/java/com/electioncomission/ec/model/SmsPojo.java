package com.electioncomission.ec.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Getter
@Service
@ToString
public class SmsPojo {

    private String mobileNumber;
}
