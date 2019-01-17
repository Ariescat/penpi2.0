package org.penpi.demo.search;

import java.util.List;

import org.penpi.demo.entity.Demo;

public interface DemoService {

	Demo findById(Integer id);

	Demo save(String name);

	List<Demo> findAll();
}
