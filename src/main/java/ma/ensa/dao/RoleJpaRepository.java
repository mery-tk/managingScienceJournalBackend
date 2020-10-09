package ma.ensa.dao;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.ensa.entities.Role;

@Transactional
public interface RoleJpaRepository extends JpaRepository<Role, Long>{
	
	public Role findByNomRole(String nomRole);

}