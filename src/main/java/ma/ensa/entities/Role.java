package ma.ensa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {
	@Id
	@GeneratedValue
	private Long id;
	private String nomRole;
	
	
	public Role() {
		super();
	}
	public Role(Long id, String nomRole) {
		super();
		this.id = id;
		this.nomRole = nomRole;
	}
	public Role( String nomRole) {
		super();

		this.nomRole = nomRole;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomRole() {
		return nomRole;
	}
	public void setNomRole(String nomRole) {
		this.nomRole = nomRole;
	}
	
	
	
	

}
