package com.mazurak.lab3_uklon.service.impl;

import com.mazurak.lab3_uklon.dao.ClientCardDao;
import com.mazurak.lab3_uklon.dao.DriverDao;
import com.mazurak.lab3_uklon.domain.ClientCard;
import com.mazurak.lab3_uklon.domain.Driver;
import com.mazurak.lab3_uklon.service.ClientCardService;
import com.mazurak.lab3_uklon.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverDao driverDao;

    @Override
    public List<Driver> findAll() {
        return driverDao.findAll();
    }

    @Override
    public Optional<Driver> findById(Integer id) {
        return driverDao.findById(id);
    }

    @Override
    public int create(Driver driver) {
        return driverDao.create(driver);
    }

    @Override
    public int update(Integer id, Driver driver) {
        return driverDao.update(id, driver);
    }

    @Override
    public int delete(Integer id) {
        return driverDao.delete(id);
    }

}