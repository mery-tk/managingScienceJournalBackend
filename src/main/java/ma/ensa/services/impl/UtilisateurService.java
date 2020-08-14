package ma.ensa.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ma.ensa.dao.IRoleDao;
import ma.ensa.dao.IUtilisateurDao;
import ma.ensa.entities.Role;
import ma.ensa.entities.Utilisateur;
import ma.ensa.services.IUtilisateurService;


@Service
public class UtilisateurService implements IUtilisateurService{

	@Autowired private IUtilisateurDao utilisateurDao;
	@Autowired private IRoleDao roleDao;
	@Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public List<Utilisateur> afficherUtilisateurs() {
		return utilisateurDao.findAll();
	}

	@Override
	public Utilisateur afficherUtilisateurParId(Long idUtilisateur) {
		return utilisateurDao.findById(idUtilisateur).get();
	}

	@Override
	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
		String passHash=bCryptPasswordEncoder.encode(utilisateur.getPassword());
        utilisateur.setPassword(passHash);
		return utilisateurDao.save(utilisateur);
	}

	@Override
	public Utilisateur modifierUtilisateur(Long idUtilisateur, Utilisateur utilisateur) {
		Utilisateur user = this.afficherUtilisateurParId(idUtilisateur);
		user.setAdresse(utilisateur.getAdresse());
		user.setEmail(utilisateur.getEmail());
		user.setNom(utilisateur.getNom());
		String passHash=bCryptPasswordEncoder.encode(utilisateur.getPassword());
        user.setPassword(passHash);
		user.setPrenom(utilisateur.getPrenom());
		user.setTelephone(utilisateur.getTelephone());
		user.setUsername(utilisateur.getUsername());
		user.setRoles(utilisateur.getRoles());
		return utilisateurDao.save(user);
	}

	@Override
	public boolean supprimerUtilisateur(Long idUtilisateur) {
		if(utilisateurDao.findById(idUtilisateur).isPresent()) {
			utilisateurDao.deleteById(idUtilisateur);
			return true;
		}
		return false;
	}

	@Override
	public Page<Utilisateur> chercherUtilisateurs(String mc, Pageable pageable) {
		return utilisateurDao.getPage(mc, pageable);
	}

	@Override
	public Utilisateur findUserByUsername(String username) {
		return utilisateurDao.findByUsername(username);	
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		Role role = roleDao.findByNomRole(roleName);
		Utilisateur utilisateur = this.findUserByUsername(username);
		utilisateur.getRoles().add(role);	
		this.modifierUtilisateur(utilisateur.getIdUtilisateur(), utilisateur);
	}

	

}
