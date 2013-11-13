package org.pshow.ecm.content.metadata;

import java.util.List;

import org.pshow.ecm.content.model.Constraint;
import org.pshow.ecm.content.model.Facet;
import org.pshow.ecm.content.model.QName;
import org.pshow.ecm.content.model.Type;
import org.pshow.ecm.content.model.definition.PSModel;
import org.pshow.ecm.content.model.definition.PSNamespace;


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
	public Type getContentType(QName name);
	public List<Type> getAllContentType();
	public boolean hasFacet(QName name);
	public Facet getFacet(QName name);
	public List<Facet> getAllFacet();
	public boolean hasConstraint(QName name);
	public Constraint getConstraint(QName name);
	public List<Constraint> getAllConstraint();
}
