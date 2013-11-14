package org.pshow.ecm.content.metadata;

import java.util.List;

import org.pshow.ecm.content.model.QName;
import org.pshow.ecm.content.model.definition.ConstraintModel;
import org.pshow.ecm.content.model.definition.FacetModel;
import org.pshow.ecm.content.model.definition.PSModel;
import org.pshow.ecm.content.model.definition.PSNamespace;
import org.pshow.ecm.content.model.definition.TypeModel;


public interface ContentSchemaHolder {
	public void registContentSchema(PSModel schema);
	public void registContentSchemas(List<PSModel> models);
	public boolean hasRegisteredObject(QName name);
	public <E> E getRegisteredObject(QName name, E e);
	public boolean hasNamespace(QName name);
	public PSNamespace getNamespaceByUri(String uri);
	public PSNamespace getNamespaceByPrefix(String prefix);
	public List<PSNamespace> getAllNamespace();
	public boolean hasContentType(QName name);
	public TypeModel getContentType(QName name);
	public List<TypeModel> getAllContentType();
	public boolean hasFacet(QName name);
	public FacetModel getFacet(QName name);
	public List<FacetModel> getAllFacet();
	public boolean hasConstraint(QName name);
	public ConstraintModel getConstraint(QName name);
	public List<ConstraintModel> getAllConstraint();
}
