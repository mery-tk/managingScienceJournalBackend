package ma.ensa.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import ma.ensa.dao.ArticleJpaRepository;
import ma.ensa.entities.Article;
import ma.ensa.services.ArticleService;


@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleJpaRepository articleJpaRepository;

	@Override
	public List<Article> afficherArticles() {
		return articleJpaRepository.findAll();
	}

	@Override
	public Article afficherArticleParId(Long idArticle) {
		Optional<Article> Article=articleJpaRepository.findById(idArticle);
		
		if(!Article.isPresent()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "l'Article dont le id("+idArticle+") est introuvable");
		}
		return articleJpaRepository.findById(idArticle).get();}

	@Override
	public Article ajouterArticle(Article article) {
		
		return articleJpaRepository.save(article);
	}

	@Override
	public Article modifierArticle(Long idArticle, Article article) {
		if(!idArticle.equals(article.getIdArticle())) {
			throw new HttpClientErrorException(HttpStatus.CONFLICT, "le 'id' dans l'IRI ne correspond pas à celui de l'article à mettre à jour");
		}
		
		Optional<Article> ad=articleJpaRepository.findById(idArticle);
		
		if(!ad.isPresent()) {			
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "l'article dont le id est ' "+idArticle+" ' est introuvable");	
		}
       Article art=this.afficherArticleParId(idArticle);
       art.setAffiliations(article.getAffiliations());
       art.setContenu(article.getContenu());
       art.setEtat(article.getEtat());
       art.setMotCle(article.getMotCle());
       art.setResume(article.getResume());
       art.setTitre(article.getTitre());
       return articleJpaRepository.save(art);
	}

	@Override
	public boolean supprimerArticle(Long idArticle) {
		if(articleJpaRepository.findById(idArticle).isPresent()) {
			articleJpaRepository.deleteById(idArticle);
			return true;
		}
		return false;
	}

	@Override
	public Page<Article> chercherArticles(String mc, int p, int s) {
		 return articleJpaRepository.chercherArticleParMc("%"+mc+"%", (org.springframework.data.domain.Pageable) PageRequest.of(p, s));
	
	}	
	
		
}