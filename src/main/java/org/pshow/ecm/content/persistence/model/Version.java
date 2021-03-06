package org.pshow.ecm.content.persistence.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.pshow.ecm.entity.IdEntity;

@Entity
@Table(name = "ps_version")
public class Version extends IdEntity {
	private String label;
	private String comments;
	private int index;

	@NotBlank
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
