package ma.ensa.dao;

import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ma.ensa.entities.Article;

@Transactional
public interface ArticleJpaRepository extends JpaRepository<Article, Long>{
	
	@Query("from Article a where a.motCle like :x")
	public Page<Article> chercherArticleParMc(@Param("x")String mc,Pageable pageable);
	

}