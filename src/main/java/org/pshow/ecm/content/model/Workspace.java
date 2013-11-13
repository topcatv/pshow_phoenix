package org.pshow.ecm.content.model;


public interface Workspace {
	public Content getRoot();
	public String getName();
	public String getDescription();
	public void setDescription(String description);
}
