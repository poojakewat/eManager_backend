package com.emanager.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emanager.springboot.model.*;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

}
