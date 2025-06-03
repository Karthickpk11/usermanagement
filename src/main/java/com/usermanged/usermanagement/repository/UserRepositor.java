package com.usermanged.usermanagement.repository;

import com.usermanged.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositor extends JpaRepository<User, Long> {

}
