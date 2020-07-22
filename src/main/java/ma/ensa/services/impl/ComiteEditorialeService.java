package ma.ensa.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ensa.dao.IComiteEditorialeDao;
import ma.ensa.entities.ComiteEditoriale;
import ma.ensa.services.IComiteEditorialeService;

@Service
public class ComiteEditorialeService implements IComiteEditorialeService{
	
	@Autowired private IComiteEditorialeDao comiteDao;

	@Override
	public List<ComiteEditoriale> afficherComiteEditoriales() {
		return comiteDao.findAll();
	}

	@Override
	public ComiteEditoriale afficherComiteEditorialeParId(Long idComiteEditoriale) {
		return comiteDao.findById(idComiteEditoriale).get();
	}

	@Override
	public ComiteEditoriale ajouterComiteEditoriale(ComiteEditoriale comiteEditoriale) {
		return comiteDao.save(comiteEditoriale);
	}

	@Override
	public ComiteEditoriale modifierComiteEditoriale(Long idComiteEditoriale, ComiteEditoriale comiteEditoriale) {
		ComiteEditoriale comite = this.afficherComiteEditorialeParId(idComiteEditoriale);
		comite.setNom(comiteEditoriale.getNom());
		return comiteDao.save(comite);
	}

	@Override
	public boolean supprimerComiteEditoriale(Long idComiteEditoriale) {
		if(comiteDao.findById(idComiteEditoriale).isPresent()) {
			comiteDao.deleteById(idComiteEditoriale);
			return true;
		}
		return false;
	}

}
