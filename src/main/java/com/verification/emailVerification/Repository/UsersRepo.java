package com.verification.emailVerification.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verification.emailVerification.Entities.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

	Users findByEmail(String email);

}
