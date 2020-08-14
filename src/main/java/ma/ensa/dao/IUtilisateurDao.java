package ma.ensa.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import ma.ensa.entities.Utilisateur;

@Transactional
public interface IUtilisateurDao extends JpaRepository<Utilisateur, Long>{
	
	@Query("select u from Utilisateur u where nom like :mc or prenom like :mc")
	public Page<Utilisateur> getPage(@Param("mc") String mc, Pageable pageable);


	public Utilisateur findByUsername(String username);

}
