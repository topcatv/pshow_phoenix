package org.pshow.ecm.content.model;


public interface Constraint {
	public boolean validate(PropertyValue value);
}
