package com.hasandayan.todoapp.repository;

import java.util.Optional;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.hasandayan.todoapp.model.AppUser;

@Repository
public interface AppUserRepository extends CouchbaseRepository<AppUser, String> {
	
	Optional<AppUser> findByUsername(String username);
	
	Optional<AppUser> findByEmail(String email);
}
