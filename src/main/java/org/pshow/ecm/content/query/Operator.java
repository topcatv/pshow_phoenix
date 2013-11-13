package org.pshow.ecm.content.query;

import java.util.List;

import org.pshow.ecm.content.model.PropertyValue;


public interface Operator {
	public void setField(String field);
	public void setValues(List propertyValue);
	public void setValue(PropertyValue propertyValue);
}
