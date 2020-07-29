package ma.ensa.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import ma.ensa.entities.ComiteEditoriale;

@Transactional
public interface IComiteEditorialeDao extends JpaRepository<ComiteEditoriale, Long>{

}
