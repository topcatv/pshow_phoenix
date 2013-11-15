package org.pshow.ecm.content.persistence.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.pshow.ecm.entity.IdEntity;

@Entity
@Table(name = "ps_content")
public class ContentModel extends IdEntity {
	private String uuid;
	private String name;
	private boolean checkouted;
	private boolean folder;
	private boolean versioned;
	private String contentType;
	
	private List<Property> properties;
	
	private VersionHistory versionHistory;

	@NotBlank
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCheckouted() {
		return checkouted;
	}

	public void setCheckouted(boolean checkouted) {
		this.checkouted = checkouted;
	}

	public boolean isFolder() {
		return folder;
	}

	public void setFolder(boolean folder) {
		this.folder = folder;
	}

	@OneToMany(mappedBy = "id")
	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public boolean isVersioned() {
		return versioned;
	}

	public void setVersioned(boolean versioned) {
		this.versioned = versioned;
	}

	@OneToOne
	public VersionHistory getVersionHistory() {
		return versionHistory;
	}

	public void setVersionHistory(VersionHistory versionHistory) {
		this.versionHistory = versionHistory;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
