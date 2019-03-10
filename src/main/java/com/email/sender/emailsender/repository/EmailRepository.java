package com.email.sender.emailsender.repository;

import com.email.sender.emailsender.models.Email;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends CrudRepository<Email, Long> {

}
