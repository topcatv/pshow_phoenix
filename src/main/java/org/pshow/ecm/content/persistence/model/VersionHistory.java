package org.pshow.ecm.content.persistence.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.pshow.ecm.entity.IdEntity;

@Entity
@Table(name = "ps_version_history")
public class VersionHistory extends IdEntity {
	
	private List<Version> versions;

	@OneToMany
	public List<Version> getVersions() {
		return versions;
	}

	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}
}
