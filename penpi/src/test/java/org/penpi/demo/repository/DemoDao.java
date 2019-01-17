package org.penpi.demo.repository;

import java.io.Serializable;

import org.penpi.demo.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoDao extends JpaRepository<Demo, Serializable> {
	
	Demo findById(Integer id);
}
