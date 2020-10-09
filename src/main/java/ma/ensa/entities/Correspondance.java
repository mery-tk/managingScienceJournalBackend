package ma.ensa.entities;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Correspondance {

	@Id
	private Correspondance_PK correspondance_PK;
	private boolean correspondance = false;
	
	
	
	public Correspondance_PK getCorrespondance_PK() {
		return correspondance_PK;
	}
	public void setCorrespondance_PK(Correspondance_PK correspondance_PK) {
		this.correspondance_PK = correspondance_PK;
	}
	public boolean isCorrespondance() {
		return correspondance;
	}
	public void setCorrespondance(boolean correspondance) {
		this.correspondance = correspondance;
	}
	public Correspondance(Correspondance_PK correspondance_PK, boolean correspondance) {
		super();
		this.correspondance_PK = correspondance_PK;
		this.correspondance = correspondance;
	}
	
	public Correspondance(Article article, Auteur auteur, boolean correspondance) { 
		this.correspondance_PK = new Correspondance_PK(article,auteur); 
		this.correspondance = correspondance;
	}
	public Correspondance() {
		super();
	}
	@Override
	public String toString() {
		return "Correspondance [correspondance_PK=" + correspondance_PK + ", correspondance=" + correspondance + "]";
	}
	
	
	
}
