package org.pshow.ecm.content.model;

import java.util.List;


public interface VersionHistory {
	public List getVersions();
	public Version getHead();
	public Version getLast();
	public void addVersion(Version version);
	public void remove(Version version);
}
