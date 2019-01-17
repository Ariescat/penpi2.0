package org.penpi.demo.service;

import java.util.List;

import org.penpi.demo.entity.Demo;
import org.penpi.demo.repository.DemoDao;
import org.penpi.demo.search.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
    DemoDao userDao;

	@Override
	public Demo findById(Integer id) {
		return userDao.findById(id);
	}

	@Override
	public Demo save(String name) {
		return userDao.save(new Demo(name));
	}

	@Override
	public List<Demo> findAll() {
		return userDao.findAll();
	}

}
