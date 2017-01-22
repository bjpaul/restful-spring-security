package org.basic.spring.security.rest.repository;

import org.basic.spring.security.rest.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

	Role findByAuthority(String authority);
}
