package com.mazurak.lab3_uklon.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {
    private Integer id;
    private String carNumber;
    private Integer carModelId;
}
