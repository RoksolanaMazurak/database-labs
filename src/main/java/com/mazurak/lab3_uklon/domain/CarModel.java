package com.mazurak.lab3_uklon.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarModel {
    private Integer id;
    private String name;
    private String company;
    private String carClass;
    private Integer seatNum;
}
