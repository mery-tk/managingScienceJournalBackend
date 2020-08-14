package ma.ensa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ma.ensa.entities.Role;

@Service
public interface IRoleService {
	
	//public List<Role> afficherRoles();
	//public Role afficheRoleParId(Long idRole);
	public Role ajouterRole(Role Role);///////////////////////////////////
	//public Role modifierRole(Long idRole, Role Role);
	//public boolean supprimerRole(Long idRole);
	

}
