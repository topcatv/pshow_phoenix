package org.pshow.ecm.content.persistence.model;

import java.util.List;

import org.pshow.ecm.entity.IdEntity;

public class PVersionHistory extends IdEntity {
	private List<PVersion> versions;

	public List<PVersion> getVersions() {
		return versions;
	}

	public void setVersions(List<PVersion> versions) {
		this.versions = versions;
	}
}
