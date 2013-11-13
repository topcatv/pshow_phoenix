package org.pshow.ecm.content.model;

import java.util.Collection;


public interface Property {
	public Class getValueClass();
	public String getName();
	public boolean isMultiple();
	public boolean isIndexed();
	public boolean isMandatory();
	public String getTitle();
	public Collection getConstraintss();
	public PropertyValue getDefaultValue();
}
