package com.example.bootcrudthymeleaf.repo;

import com.example.bootcrudthymeleaf.ds.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
}
