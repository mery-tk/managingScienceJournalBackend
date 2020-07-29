package ma.ensa.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.ensa.dao.IArticleDao;
import ma.ensa.entities.Article;
import ma.ensa.services.IArticleService;


@Service
public class ArticleService implements IArticleService{
	@Autowired
	private IArticleDao articleDao;

	@Override
	public List<Article> afficherArticles() {
		return articleDao.findAll();
	}

	@Override
	public Article afficherArticleParId(Long idArticle) {
		return articleDao.findById(idArticle).get();}

	@Override
	public Article ajouterArticle(Article article) {
		return articleDao.save(article);
	}

	@Override
	public Article modifierArticle(Long idArticle, Article article) {
       Article art=this.afficherArticleParId(idArticle);
       art.setAffiliations(article.getAffiliations());
       art.setContenu(article.getContenu());
       art.setEtat(article.getEtat());
       art.setMotCle(article.getMotCle());
       art.setResume(article.getResume());
       art.setTitre(article.getTitre());
       return articleDao.save(art);
	}

	@Override
	public boolean supprimerArticle(Long idArticle) {
		if(articleDao.findById(idArticle).isPresent()) {
			articleDao.deleteById(idArticle);
			return true;
		}
		return false;
	}


		
}