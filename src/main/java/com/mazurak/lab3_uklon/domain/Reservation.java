package com.mazurak.lab3_uklon.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation {
    private Integer id;
    private Integer client;
    private String time;
    private Integer payment;
    private String startAddress;
    private String finalAddress;
    private Integer car;
}
