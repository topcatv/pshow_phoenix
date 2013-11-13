package org.pshow.ecm.content.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public interface PropertyValue {
	public Serializable getValue();

	public int getInt();

	public long getLong();

	public float getFloat();

	public double getDouble();

	public Date getDate();

	public String getString();

	public boolean getBoolean();

	public Collection getValues();
}
