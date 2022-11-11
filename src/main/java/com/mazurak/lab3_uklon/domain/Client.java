package com.mazurak.lab3_uklon.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
    private Integer id;
    private String surname;
    private String name;
    private String phone;
    private String email;
    private String city;
    private String streetAddress;
    private Integer clientCard;
}
