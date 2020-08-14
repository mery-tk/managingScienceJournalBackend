package ma.ensa.services;

import java.util.List;
import org.springframework.stereotype.Service;
import ma.ensa.entities.Auteur;

@Service
public interface IAuteurService {
	
	public List<Auteur> afficherAuteurs();
	public Auteur afficherAuteurParId(Long idAuteur);
	public Auteur ajouterAuteur(Auteur auteur);
	public Auteur modifierAuteur(Long idAuteur, Auteur auteur);
	public boolean supprimerAuteur(Long idAuteur);


}
