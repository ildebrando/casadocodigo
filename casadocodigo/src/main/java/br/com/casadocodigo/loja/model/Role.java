package br.com.casadocodigo.loja.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	private static final String roleConcat = "ROLE_";

	@Id
	private String name;
	private String description;

	@Override
	public String getAuthority() {
		return getName();
	}

	public String getName() {
		return roleConcat + name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
