package org.pshow.ecm.content.persistence.model;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.pshow.ecm.entity.IdEntity;

@Entity
@Table(name = "ps_content")
public class PContent extends IdEntity {
	private String uuid;
	private String name;
	private boolean checkouted;
	private boolean folder;
	private PPath path;
	private Map<String, PProperty> properties;
	private boolean versioned;
	private PVersionHistory versionHistory;

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

	public PPath getPath() {
		return path;
	}

	public void setPath(PPath path) {
		this.path = path;
	}

	public Map<String, PProperty> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, PProperty> properties) {
		this.properties = properties;
	}

	public boolean isVersioned() {
		return versioned;
	}

	public void setVersioned(boolean versioned) {
		this.versioned = versioned;
	}

	public PVersionHistory getVersionHistory() {
		return versionHistory;
	}

	public void setVersionHistory(PVersionHistory versionHistory) {
		this.versionHistory = versionHistory;
	}
}
