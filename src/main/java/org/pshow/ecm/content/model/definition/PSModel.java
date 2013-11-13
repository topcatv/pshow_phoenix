package org.pshow.ecm.content.model.definition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class PSModel {
	private String name;
	private String description;
	private String author;
	private Date published;
	private String version;
	private List<PSNamespace> namespaces = new ArrayList<PSNamespace>();
	private List<PSNamespace> imports = new ArrayList<PSNamespace>();
	private List<DataType> dataTypes = new ArrayList<DataType>();
	private List<TypeModel> types = new ArrayList<TypeModel>();
	private List<FacetModel> facets = new ArrayList<FacetModel>();
	private List<ConstraintModel> constraints = new ArrayList<ConstraintModel>();

	private void PSModel() {

	}

	private static List createList() {
		return new ArrayList();

	}

	public String getName() {
		return name;

	}

	public void setName(String name) {

	}

	public String getDescription() {
		return description;

	}

	public void setDescription(String description) {

	}

	public String getAuthor() {
		return author;

	}

	public void setAuthor(String author) {

	}

	public Date getPublished() {
		return published;

	}

	public void setPublished(Date published) {

	}

	public String getVersion() {
		return version;

	}

	public void setVersion(String version) {

	}

	public PSNamespace createNamespace(String uri, String prefix) {
		PSNamespace psNamespace = new PSNamespace();
		psNamespace.setPrefix(prefix);
		psNamespace.setUri(uri);
		return psNamespace;

	}

	public void removeNamespace(String uri) {

	}

	public List<PSNamespace> getNamespaces() {
		return namespaces;

	}

	public PSNamespace getNamespace(String uri) {
		for (PSNamespace namespace : namespaces) {
			if(StringUtils.equals(uri, namespace.getUri())){
				return namespace;
			}
		}
		return null;

	}

	public PSNamespace createImport(String uri, String prefix) {
		PSNamespace psNamespace = new PSNamespace();
		psNamespace.setPrefix(prefix);
		psNamespace.setUri(uri);
		return psNamespace;

	}

	public void removeImport(String uri) {

	}

	public List<PSNamespace> getImports() {
		return imports;

	}

	public PSNamespace getImport(String uri) {
		for (PSNamespace namespace : imports) {
			if(StringUtils.equals(uri, namespace.getUri())){
				return namespace;
			}
		}
		return null;

	}

	public List<ConstraintModel> getConstraints() {
		return constraints;

	}

	public ConstraintModel getConstraint(String name) {
		for (ConstraintModel constraint : constraints) {
			if(StringUtils.equals(name, constraint.getName())){
				return constraint;
			}
		}
		return null;

	}

	public ConstraintModel createConstraint(String name, String type) {
		ConstraintModel constraintModel = new ConstraintModel();
		constraintModel.setName(name);
		constraintModel.setJavaClassName(type);
		return constraintModel;

	}

	public void removeConstraint(String name) {

	}

	public DataType createPropertyType(String name) {
		DataType dataType = new DataType();
		dataType.setName(name);
		return dataType;

	}

	public void removePropertyType(String name) {

	}

	public List<DataType> getPropertyTypes() {
		return dataTypes;

	}

	public DataType getPropertyType(String name) {
		for (DataType dataType : dataTypes) {
			if(StringUtils.equals(name, dataType.getName())){
				return dataType;
			}
		}
		return null;

	}

	public TypeModel createType(String name) {
		TypeModel typeModel = new TypeModel();
		typeModel.setName(name);
		return typeModel;

	}

	public void removeType(String name) {

	}

	public List<TypeModel> getTypes() {
		return types;

	}

	public TypeModel getType(String name) {
		for (TypeModel type : types) {
			if(StringUtils.equals(name, type.getName())){
				return type;
			}
		}
		return null;

	}

	public FacetModel createFacet(String name) {
		FacetModel facetModel = new FacetModel();
		facetModel.setName(name);
		return facetModel;

	}

	public void removeFacet(String name) {

	}

	public List<FacetModel> getFacets() {
		return facets;

	}

	public FacetModel getFacet(String name) {
		for (FacetModel facet : facets) {
			if(StringUtils.equals(name, facet.getName())){
				return facet;
			}
		}
		return null;
	}
}
