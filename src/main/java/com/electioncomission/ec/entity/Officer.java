package com.electioncomission.ec.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Component
public class Officer {
    int id;
    String role;

}
