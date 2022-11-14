package com.mazurak.lab3_uklon.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Driver {
    private Integer id;
    private String surname;
    private String name;
    private String gender;
    private Integer rating;
    private Integer carId;
}
