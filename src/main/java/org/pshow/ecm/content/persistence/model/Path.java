package org.pshow.ecm.content.persistence.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.pshow.ecm.entity.IdEntity;

@Entity
@Table(name = "ps_path")
public class Path extends IdEntity {
	
	private String path;
	
	private Long parentId;
	
	private Path parent;
	
	private List<Path> children;
	
	private List<Content> contents;

	@NotBlank
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@ManyToOne
	@JoinColumn(name = "parent_id")
	public Path getParent() {
		return parent;
	}

	public void setParent(Path parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "id")
	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	@OrderBy("id")
	public List<Path> getChildren() {
		return children;
	}

	public void setChildren(List<Path> children) {
		this.children = children;
	}
	
	@Column(name = "parent_id", unique = false, nullable = true, insertable = false, updatable = false)
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
