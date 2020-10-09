package ma.ensa.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import ma.ensa.dao.RoleJpaRepository;
import ma.ensa.entities.Role;
import ma.ensa.services.RoleService;
@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleJpaRepository roleJpaRepository;
	
	@Override
	public Role ajouterRole(Role role) {
		
		return roleJpaRepository.save(role);
	}

	

}
