package ma.ensa.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ma.ensa.entities.ComiteEditoriale;

@Transactional
public interface IComiteEditorialeDao extends JpaRepository<ComiteEditoriale, Long>{

}
