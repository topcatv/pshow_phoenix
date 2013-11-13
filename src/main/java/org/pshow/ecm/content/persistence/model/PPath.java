package org.pshow.ecm.content.persistence.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.pshow.ecm.entity.IdEntity;

@Entity
@Table(name = "ps_path")
public class PPath extends IdEntity {
	private String path;
	private PPath parent;

	@NotBlank
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@ManyToOne
	@JoinColumn(name = "path_id")
	public PPath getParent() {
		return parent;
	}

	public void setParent(PPath parent) {
		this.parent = parent;
	}
}
