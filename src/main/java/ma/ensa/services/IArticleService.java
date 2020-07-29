package ma.ensa.services;

import java.util.List;
import org.springframework.stereotype.Service;
import ma.ensa.entities.Article;
import ma.ensa.entities.InfoReduitesArticle;

@Service
public interface IArticleService {
	
	public List<Article> afficherArticles();
	public Article afficherArticleParId(Long idArticle);
	public Article ajouterArticle(Article article);
	public Article modifierArticle(Long idArticle, Article article);
	public boolean supprimerArticle(Long idArticle);

}