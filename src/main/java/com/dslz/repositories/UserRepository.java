package com.dslz.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dslz.beans.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
