package ma.ensa.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ensa.dao.IRoleDao;
import ma.ensa.entities.Role;
import ma.ensa.services.IRoleService;
@Service
public class RoleService implements IRoleService{

	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public Role ajouterRole(Role role) {
		return roleDao.save(role);
	}
	
	

}
