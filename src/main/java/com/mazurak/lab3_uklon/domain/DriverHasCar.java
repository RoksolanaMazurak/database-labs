package com.mazurak.lab3_uklon.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverHasCar {
    private Integer id;
    private Integer car;
    private Integer driver;

}
