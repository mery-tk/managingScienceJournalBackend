package ma.ensa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ma.ensa.entities.ComiteEditoriale;

@Service
public interface IComiteEditorialeService {
	
	public List<ComiteEditoriale> afficherComiteEditoriales();
	public ComiteEditoriale afficherComiteEditorialeParId(Long idComiteEditoriale);
	public ComiteEditoriale ajouterComiteEditoriale(ComiteEditoriale comiteEditoriale);
	public ComiteEditoriale modifierComiteEditoriale(Long idComiteEditoriale, ComiteEditoriale comiteEditoriale);
	public boolean supprimerComiteEditoriale(Long idComiteEditoriale);

	

}
