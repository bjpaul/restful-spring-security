package org.basic.spring.security.rest.repository;

import org.basic.spring.security.rest.domain.UserDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends CrudRepository<UserDetail, Long>{

}
