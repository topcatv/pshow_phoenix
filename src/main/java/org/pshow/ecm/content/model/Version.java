package org.pshow.ecm.content.model;

import java.util.Date;


public interface Version {
	public String getLabel();
	public Content getContent();
	public boolean isHead();
	public Version previous();
	public Version next();
	public String getComment();
	public void getCreator();
	public Date getCreated();
	public void remove();
	public void restore();
	public String getId();
}
