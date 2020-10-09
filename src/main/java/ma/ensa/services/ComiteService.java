package ma.ensa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ma.ensa.entities.Comite;

@Service
public interface ComiteService {
	
	public List<Comite> afficherComites();
	public Comite afficherComiteParId(Long idComite);
	public Comite ajouterComite(Comite comite);
	public Comite modifierComite(Long idComite, Comite comite);
	public boolean supprimerComite(Long idComite);

	

}
