package org.pshow.ecm.content.model;

import java.util.Collection;
import java.util.Date;


public interface Content {
	public String getName();
	public String getDescription();
	public Date getModified();
	public void getModifier();
	public Date getCreated();
	public void getCreator();
	public void rename(String name);
	public void setDescription(String desc);
	public PropertyValue getProperty();
	public Type getType();
	public Collection getFacets();
	public boolean isFolder();
	public Collection getProperties();
	public void addProperty(String name, PropertyValue property);
	public void addProperties(Collection properties);
	public void removeProperty(String name);
	public String getId();
	public Collection getChilder();
	public void addContent(Content content);
	public void removeContent(Content content);
	public void moveTo(Content content);
}
