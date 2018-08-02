package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Nodes;
import com.example.demo.entity.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer>{


}
