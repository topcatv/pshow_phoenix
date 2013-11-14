package org.pshow.ecm.content.model.definition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.pshow.ecm.utils.DateConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("model")
public class PSModel {
	@XStreamAsAttribute
	private String name;
	private String description;
	@XStreamAsAttribute
	private String author;
	@XStreamConverter(DateConverter.class)
	private Date published;
	@XStreamAsAttribute
	private String version;
	private List<PSNamespace> namespaces = new ArrayList<PSNamespace>();
	private List<PSNamespace> imports = new ArrayList<PSNamespace>();
	@XStreamAlias("data-types")
	private List<DataType> dataTypes = new ArrayList<DataType>();
	private List<TypeModel> types = new ArrayList<TypeModel>();
	private List<FacetModel> facets = new ArrayList<FacetModel>();
	private List<ConstraintModel> constraints = new ArrayList<ConstraintModel>();

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
		namespaces.add(psNamespace);
		return psNamespace;

	}

	public void removeNamespace(String uri) {
		for (PSNamespace psNamespace : namespaces) {
			if(StringUtils.equals(psNamespace.getUri(), uri)){
				namespaces.remove(psNamespace);
			}
		}
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
		imports.add(psNamespace);
		return psNamespace;

	}

	public void removeImport(String uri) {
		for (PSNamespace namespace : imports) {
			if(StringUtils.equals(uri, namespace.getUri())){
				imports.remove(namespace);
			}
		}
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
		constraints.add(constraintModel);
		return constraintModel;

	}

	public void removeConstraint(String name) {
		for (ConstraintModel constraint : constraints) {
			if(StringUtils.equals(name, constraint.getName())){
				constraints.remove(constraint);
			}
		}
	}

	public DataType createPropertyType(String name) {
		DataType dataType = new DataType();
		dataType.setName(name);
		dataTypes.add(dataType);
		return dataType;

	}

	public void removePropertyType(String name) {
		for (DataType dataType : dataTypes) {
			if(StringUtils.equals(name, dataType.getName())){
				dataTypes.remove(dataType);
			}
		}
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
		types.add(typeModel);
		return typeModel;

	}

	public void removeType(String name) {
		for (TypeModel type : types) {
			if (StringUtils.equals(type.getName(), name)) {
				types.remove(type);
			}
		}
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
		facets.add(facetModel);
		return facetModel;

	}

	public void removeFacet(String name) {
		for (FacetModel facet : facets) {
			if(StringUtils.equals(name, facet.getName())){
				facets.remove(facet);
			}
		}
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
