package com.workshop.demojwt.repository;

import com.workshop.demojwt.pojo.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    @Query("select u from User u where u.username = ?1")
    Optional<User> findByUsername(String username);
}
