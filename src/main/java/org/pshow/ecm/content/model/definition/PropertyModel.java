package org.pshow.ecm.content.model.definition;

public class PropertyModel {
	public String name;
	public String title;
	public DataType dataType;
	public ConstraintModel constraint;
	public boolean indexed;
	public boolean multipled;
	public boolean mandatory;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public ConstraintModel getConstraint() {
		return constraint;
	}

	public void setConstraint(ConstraintModel constraint) {
		this.constraint = constraint;
	}

	public boolean isIndexed() {
		return indexed;
	}

	public void setIndexed(boolean indexed) {
		this.indexed = indexed;
	}

	public boolean isMultipled() {
		return multipled;
	}

	public void setMultipled(boolean multipled) {
		this.multipled = multipled;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
}
