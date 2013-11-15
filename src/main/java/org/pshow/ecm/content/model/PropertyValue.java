package org.pshow.ecm.content.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface PropertyValue {
	public Serializable getValue();

	public Integer getIntValue();

	public Long getLongValue();

	public Float getFloatValue();

	public Double getDoubleValue();

	public Date getDateValue();

	public String getStringValue();

	public Boolean getBooleanValue();

	public List<Serializable> getValues();
}
