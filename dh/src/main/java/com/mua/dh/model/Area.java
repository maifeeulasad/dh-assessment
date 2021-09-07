package com.mua.dh.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long areaId;
    // no description was provided
}
