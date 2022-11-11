package com.mazurak.lab3_uklon.controller.impl;

import com.mazurak.lab3_uklon.controller.ClientController;
import com.mazurak.lab3_uklon.controller.DriverController;
import com.mazurak.lab3_uklon.domain.Client;
import com.mazurak.lab3_uklon.domain.Driver;
import com.mazurak.lab3_uklon.service.ClientService;
import com.mazurak.lab3_uklon.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverControllerImpl implements DriverController {

    @Autowired
    private DriverService driverService;

    @Override
    public List<Driver> findAll() {
        return driverService.findAll();
    }

    @Override
    public Optional<Driver> findById(Integer id) {
        return driverService.findById(id);
    }

    @Override
    public int create(Driver driver) {
        return driverService.create(driver);
    }

    @Override
    public int update(Integer id, Driver driver) {
        return driverService.update(id, driver);
    }

    @Override
    public int delete(Integer id) {
        return driverService.delete(id);
    }

}
