package ma.ensa.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ensa.dao.IAdministrateurDao;
import ma.ensa.entities.Administrateur;
import ma.ensa.services.IAdministrateurService;

@Service
public class AdministrateurService implements IAdministrateurService{

	@Autowired private IAdministrateurDao administrateurDao;

	@Override
	public List<Administrateur> afficherAdministrateurs() {
		return administrateurDao.findAll();
	}

	@Override
	public Administrateur afficherAdminParId(Long idAdmin) {
		return administrateurDao.findById(idAdmin).get();
	}

	@Override
	public Administrateur ajouterAdmin(Administrateur administrateur) {
		return administrateurDao.save(administrateur);
	}

	@Override
	public Administrateur modifierAdmin(Long idAdmin, Administrateur administrateur) {
		Administrateur admin = this.afficherAdminParId(idAdmin);
		admin.setAdresse(administrateur.getAdresse());
		admin.setDebutfonction(administrateur.getDebutfonction());
		admin.setEmail(administrateur.getEmail());
		admin.setFinFonction(administrateur.getFinFonction());
		admin.setNom(administrateur.getNom());
		admin.setPassword(administrateur.getPassword());
		admin.setPrenom(administrateur.getPrenom());
		admin.setTelephone(administrateur.getTelephone());
		admin.setUsername(administrateur.getUsername());
		return administrateurDao.save(admin);
	}

	@Override
	public boolean supprimerAdministrateur(Long idAdmin) {
		if(administrateurDao.findById(idAdmin).isPresent()) {
			administrateurDao.deleteById(idAdmin);
			return true;
		}
		return false;
	}
	
	
	
}
