package ma.ensa.entities;

import javax.persistence.Id;

public class Correspondance {

	@Id
	private Correspondance_PK correspondance_PK;
	private boolean correspondance;
	
	
	
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
	
	
	
}
