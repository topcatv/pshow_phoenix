package org.pshow.ecm.content.model;

import java.util.Collection;


public interface ContentClass {
	public String getName();
	public String getDescription();
	public String getTitle();
	public boolean hasProperty(String name);
	public Property getProperty();
	public Collection getProperties();
	public ContentClass getParentType();
	public boolean isFacet();
}
