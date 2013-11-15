package org.pshow.ecm.content.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
	public List<Facet> getFacets();
	public boolean isFolder();
	public Map<String, PropertyValue> getProperties();
	public void addProperty(String name, PropertyValue property);
	public void addProperties(Map<String, PropertyValue> properties);
	public void removeProperty(String name);
	public String getId();
	public List<Content> getChilder();
	public void addContent(Content content);
	public void removeContent(Content content);
	public void moveTo(Content content);
}
