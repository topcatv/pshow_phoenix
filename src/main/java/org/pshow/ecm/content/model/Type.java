package org.pshow.ecm.content.model;

import java.util.Collection;


public class Type implements ContentClass {
	public String getName() {
		return null;
	}
	public String getDescription() {
		return null;
	}
	public String getTitle() {
		return null;
	}
	public boolean hasProperty(String name) {
		return false;
	}
	public Property getProperty() {
		return null;
	}
	public Collection getProperties() {
		return null;
	}
	public ContentClass getParentType() {
		return null;
	}
	public boolean isFacet() {
		return false;
	}
}
