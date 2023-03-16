package com.example.SpringJpaFirst.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.SpringJpaFirst.model.Employee;

import jakarta.transaction.Transactional;

@Repository

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
}
