package ma.ensa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ma.ensa.entities.Utilisateur;


@Service
public interface IUtilisateurService {
	
	public List<Utilisateur> afficherUtilisateurs();
	public Utilisateur afficherUtilisateurParId(Long idUtilisateur);
	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur);
	public Utilisateur modifierUtilisateur(Long idUtilisateur, Utilisateur utilisateur);
	public boolean supprimerUtilisateur(Long idUtilisateur);


}
