package ma.ensa.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import ma.ensa.dao.RoleJpaRepository;
import ma.ensa.dao.UtilisateurJpaRepository;
import ma.ensa.entities.Role;
import ma.ensa.entities.Utilisateur;
import ma.ensa.services.UtilisateurService;


@Service
public class UtilisateurServiceImpl implements UtilisateurService{

	@Autowired private UtilisateurJpaRepository utilisateurJpaRepository;
	@Autowired private RoleJpaRepository roleJpaRepository;
	@Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public List<Utilisateur> afficherUtilisateurs() {
		return utilisateurJpaRepository.findAll();
	}

	@Override
	public Utilisateur afficherUtilisateurParId(Long idUtilisateur) {
		  Optional<Utilisateur> user=utilisateurJpaRepository.findById(idUtilisateur);
			
			if(!user.isPresent()) {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "l'utilisateur dont le id("+idUtilisateur+") est introuvable");
			}
		return utilisateurJpaRepository.findById(idUtilisateur).get();
	}

	@Override
	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
		String passHash=bCryptPasswordEncoder.encode(utilisateur.getPassword());
        utilisateur.setPassword(passHash);
		return utilisateurJpaRepository.save(utilisateur);
	}

	@Override
	public Utilisateur modifierUtilisateur(Long idUtilisateur, Utilisateur utilisateur) {
		if(!idUtilisateur.equals(utilisateur.getIdUtilisateur())) {
			throw new HttpClientErrorException(HttpStatus.CONFLICT, "le 'id' dans l'IRI ne correspond pas à celui de l'utilisateur à mettre à jour");
		}
		
		Optional<Utilisateur> ad=utilisateurJpaRepository.findById(idUtilisateur);
		
		if(!ad.isPresent()) {			
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "l'utilisateur dont le id est ' "+idUtilisateur+" ' est introuvable");	
		}
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
		return utilisateurJpaRepository.save(user);
	}

	@Override
	public boolean supprimerUtilisateur(Long idUtilisateur) {
		if(utilisateurJpaRepository.findById(idUtilisateur).isPresent()) {
			utilisateurJpaRepository.deleteById(idUtilisateur);
			return true;
		}
		return false;
	}

	@Override
	public Page<Utilisateur> chercherUtilisateurs(String mc, Pageable pageable) {
		return utilisateurJpaRepository.getPage(mc, pageable);
	}

	@Override
	public Utilisateur findUserByUsername(String username) {
		return utilisateurJpaRepository.findByUsername(username);	
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		Role role = roleJpaRepository.findByNomRole(roleName);
		Utilisateur utilisateur = this.findUserByUsername(username);
		utilisateur.getRoles().add(role);	
		this.modifierUtilisateur(utilisateur.getIdUtilisateur(), utilisateur);
	}

	
}
