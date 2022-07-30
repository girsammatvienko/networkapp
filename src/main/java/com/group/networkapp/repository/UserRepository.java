package com.group.networkapp.repository;

import com.group.networkapp.domain.entity.NetworkUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<NetworkUser, Long> {
    Optional<NetworkUser> findByEmail(String email);
}
