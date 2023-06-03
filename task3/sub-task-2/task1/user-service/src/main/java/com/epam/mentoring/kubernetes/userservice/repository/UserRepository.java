package com.epam.mentoring.kubernetes.userservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.epam.mentoring.kubernetes.userservice.domain.User;

/**
 * @author Eduard_Sardyka
 */
@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long> {

}