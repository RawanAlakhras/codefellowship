package com.example.codefellowship;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser,Long> {

    public ApplicationUser findByUsername(String username);
}
