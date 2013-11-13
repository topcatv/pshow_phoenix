package org.pshow.ecm.content;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.pshow.ecm.content.model.PropertyValue;
import org.pshow.ecm.content.model.QName;
import org.pshow.ecm.content.model.Type;
import org.pshow.ecm.content.model.Workspace;


public interface ContentService {
	public String getRoot(String workspace);
	public Map getProperties(String contentId);
	public PropertyValue getProperty(String contentId, String name);
	public void setProperty(String contentId, String name, PropertyValue value);
	public void setProperites(String contentId, Map values);
	public void addProperty(String contentId, String name, PropertyValue value);
	public void removeProperty(String contentId, String name);
	public Type getType(String contentId);
	public Collection getFacets(String contentId);
	public List getChild(String contentId);
	public String createContent(QName type, String parentId, String name);
	public String createContent(QName type, String parentId, String name, Map properties);
	public void removeContent(String contentId);
	public Workspace createWorkspace(String name);
	public Workspace findWorkspace(String name);
}
