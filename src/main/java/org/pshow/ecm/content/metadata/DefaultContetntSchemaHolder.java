/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pshow.ecm.content.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.pshow.ecm.content.exception.SchemaRegistException;
import org.pshow.ecm.content.model.QName;
import org.pshow.ecm.content.model.definition.ConstraintModel;
import org.pshow.ecm.content.model.definition.DataType;
import org.pshow.ecm.content.model.definition.FacetModel;
import org.pshow.ecm.content.model.definition.PSModel;
import org.pshow.ecm.content.model.definition.PSNamespace;
import org.pshow.ecm.content.model.definition.PropertyModel;
import org.pshow.ecm.content.model.definition.TypeModel;
import org.pshow.ecm.content.persistence.model.NamespaceModel;
import org.pshow.ecm.repository.NamespaceDao;
import org.pshow.ecm.utils.cache.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;

/**
 * @author roy TODO: 返回的数据不可被修改，需要给返回的数据提供副本
 */
@Service("contentSchemaHolder")
public class DefaultContetntSchemaHolder implements ContentSchemaHolder {

	private static final String ALL_NAMESPACE = "ps:all_namespace";

	private static final String ALL_CONSTRAINT = "ps:all_constraint";

	private static final String ALL_DATATYPE = "ps:all_datatype";

	private static final String ALL_FACET = "ps:all_facet";

	private static final String ALL_TYPE = "ps:all_type";

	@Autowired
	@Qualifier("memoryStore")
	private Store<QName, Object> store;

	@Autowired
	private NamespaceDao namespaceDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pshow.repo.schema.ContentSchemaHolder#registContentSchemas(java.util
	 * .List)
	 */
	@Override
	public void registContentSchemas(List<PSModel> schemas) {
		for (PSModel psModel : schemas) {
			registContentSchema(psModel);
		}
	}

	private void registConstraints(List<ConstraintModel> constraints) {
		if (CollectionUtils.isNotEmpty(constraints)) {
			for (ConstraintModel constraintModel : constraints) {
				checkConstraint(constraintModel);
				store.put(createKey(constraintModel.getName()), constraintModel);
			}
			putStoreSafe(createKey(ALL_CONSTRAINT), constraints);
		}
	}

	private void checkConstraint(ConstraintModel constraintModel) {
		if (hasConstraint(createKey(constraintModel.getName()))) {
			throw new SchemaRegistException(String.format(
					"Constraint regist error: duplicate constraint[%s]",
					constraintModel.getName()));
		}
	}

	private QName createKey(String name) {
		return QName.createQName(QName.resolvePrefix(name),
				QName.resolveLocalName(name));
	}

	private void registDataTypes(List<DataType> propertyTypes) {
		if (CollectionUtils.isNotEmpty(propertyTypes)) {
			for (DataType dataType : propertyTypes) {
				checkDataType(dataType);
				store.put(createKey(dataType.getName()), dataType);
			}
			putStoreSafe(createKey(ALL_DATATYPE), propertyTypes);
		}
	}

	/**
	 * 检查要注册的数据类型是否可注册
	 * 
	 * @param dataType
	 */
	private void checkDataType(DataType dataType) {
		try {
			// 值类型是否可被加载
			getClass().getClassLoader().loadClass(dataType.getJavaClassName());
		} catch (ClassNotFoundException e) {
			throw new SchemaRegistException(
					String.format(
							"DataType regist error: can't load class '%s' for datatype[%s]",
							dataType.getJavaClassName(), dataType.getName()), e);
		}
		// 类型是否已注册
		if (store.contains(createKey(dataType.getName()))) {
			throw new SchemaRegistException(String.format(
					"DataType regist error: duplicate datatype[%s]",
					dataType.getName()));
		}
	}

	private void registFacets(List<FacetModel> facets) {
		if (CollectionUtils.isNotEmpty(facets)) {
			for (FacetModel facet : facets) {
				checkFacet(facet);
				store.put(createKey(facet.getName()), facet);
			}
			putStoreSafe(createKey(ALL_FACET), facets);
		}
	}

	private void checkFacet(FacetModel facet) {
		if (hasFacet(createKey(facet.getName()))) {
			throw new SchemaRegistException(String.format(
					"ContentFacet regist error: duplicate content facet[%s]",
					facet.getName()));
		}
		List<PropertyModel> properties = facet.getProperties();
		for (PropertyModel property : properties) {
			// 验证属性的值类型是否正确
			String datatype_name = property.getDataTypeName();
			if (!hasRegisteredObject(createKey(datatype_name))) {
				throw new SchemaRegistException(
						String.format(
								"ContentFacet regist error: can't find datatype[%s] for property['%s'] of facet[%s]",
								datatype_name, property.getName(),
								facet.getName()));
			}
			// 验证属性引用的约束是否正确
			List<String> constraintNames = property.getConstraints();
			if (constraintNames != null) {
				for (String constraintName : constraintNames) {
					if (!hasConstraint(createKey(constraintName))) {
						throw new SchemaRegistException(
								String.format(
										"ContentFacet regist error: can't find constraint[%s] for property['%s'] of facet[%s]",
										constraintName, property.getName(),
										facet.getName()));
					}
				}
			}
		}
	}

	private void registTypes(List<TypeModel> types) {
		if (CollectionUtils.isNotEmpty(types)) {
			for (TypeModel type : types) {
				checkContentType(type);
				store.put(createKey(type.getName()), type);
			}
			putStoreSafe(createKey(ALL_TYPE), types);
		}
	}

	private <E> void putStoreSafe(QName key, List<E> value) {
		if (store.contains(key)) {
			List<E> old_all = this.getRegisteredObject(key, new ArrayList<E>());
			List<E> new_all = new ArrayList<E>(value.size() + old_all.size());
			new_all.addAll(value);
			new_all.addAll(old_all);
			store.put(key, new_all);
		} else {
			store.put(key, value);
		}
	}

	private <K, V> void putStoreSafe(QName key, Map<K, V> value) {
		if (store.contains(key)) {
			Map<K, V> old_all = this.getRegisteredObject(key,
					Maps.synchronizedBiMap(HashBiMap.<K, V> create()));
			Map<K, V> new_all = Maps.synchronizedBiMap(HashBiMap
					.<K, V> create(value.size() + old_all.size()));
			new_all.putAll(value);
			new_all.putAll(old_all);
			store.put(key, new_all);
		} else {
			store.put(key, value);
		}
	}

	private void checkContentType(TypeModel type) {
		if (hasContentType(createKey(type.getName()))) {
			throw new SchemaRegistException(String.format(
					"ContentType regist error: duplicate content type[%s]",
					type.getName()));
		}
		List<PropertyModel> properties = type.getProperties();
		for (PropertyModel property : properties) {
			// 验证属性的值类型是否正确
			String datatype_name = property.getDataTypeName();
			if (!hasRegisteredObject(createKey(datatype_name))) {
				throw new SchemaRegistException(
						String.format(
								"ContentType regist error: can't find datatype[%s] for property['%s'] of type[%s]",
								datatype_name, property.getName(),
								type.getName()));
			}
			// 验证属性引用的约束是否正确
			List<String> constraintNames = property.getConstraints();
			if (constraintNames != null) {
				for (String constraintName : constraintNames) {
					if (!hasConstraint(createKey(constraintName))) {
						throw new SchemaRegistException(
								String.format(
										"ContentType regist error: can't find constraint[%s] for property['%s'] of facet[%s]",
										constraintName, property.getName(),
										type.getName()));
					}
				}
			}
		}
	}

	private void registNamespaces(List<PSNamespace> list) {
		// 加载已注册的namespace
		List<PSNamespace> exist_namespaces = loadNamespacesFromDB();
		if (list != null) {
			for (PSNamespace psNamespace : list) {
				if (!exist_namespaces.contains(psNamespace)) {
					// 如果从配置文件中加载的namespace不存在数据库中，写入数据库存并加入已存在namespace列表
					namespaceDao.save(new NamespaceModel(psNamespace
							.getUri(), psNamespace.getPrefix()));
					exist_namespaces.add(psNamespace);
				}
			}
		}
		BiMap<String, String> namespaces = Maps.synchronizedBiMap(HashBiMap
				.<String, String> create());
		for (PSNamespace psNamespace : exist_namespaces) {
			checkNamespace(namespaces, psNamespace);
			namespaces.put(psNamespace.getUri(), psNamespace.getPrefix());
		}
		putStoreSafe(createKey(ALL_NAMESPACE), namespaces);
	}

	/**
	 * 检查否有重复的namespace
	 * 
	 * @param namespaces
	 * @param psNamespace
	 */
	private void checkNamespace(BiMap<String, String> namespaces,
			PSNamespace psNamespace) {
		// 是否有重复的uri
		if (namespaces.containsKey(psNamespace.getUri())) {
			throw new SchemaRegistException(String.format(
					"Namespace regist error: duplicate namespace uri -> %s",
					psNamespace.toString()));
		}
		// 是否有重复的prefix
		if (namespaces.containsValue(psNamespace.getPrefix())) {
			throw new SchemaRegistException(String.format(
					"Namespace regist error: duplicate namespace prefix -> %s",
					psNamespace.toString()));
		}
	}

	private List<PSNamespace> loadNamespacesFromDB() {
		ArrayList<PSNamespace> namespaces = new ArrayList<PSNamespace>();
		namespaces.addAll(convertNamespace(namespaceDao.findAll()));
		return namespaces;
	}

	private Collection<? extends PSNamespace> convertNamespace(
			 Iterable<NamespaceModel> findAllNamespaces) {
		ArrayList<PSNamespace> result = new ArrayList<PSNamespace>();
		for (NamespaceModel namespaceModel : findAllNamespaces) {
			result.add(new PSNamespace(namespaceModel.getUri(), namespaceModel
					.getPrefix()));
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pshow.repo.schema.ContentSchemaHolder#registContentSchema(org.pshow
	 * .repo.datamodel.content.definition.PSModel)
	 */
	@Override
	public void registContentSchema(PSModel schema) {
		registNamespaces(schema.getNamespaces());
		registDataTypes(schema.getPropertyTypes());
		registConstraints(schema.getConstraints());
		registTypes(schema.getTypes());
		registFacets(schema.getFacets());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pshow.repo.schema.ContentSchemaHolder#getNamespace(org.pshow.repo
	 * .datamodel.namespace.QName)
	 */
	@Override
	public PSNamespace getNamespaceByUri(String uri) {
		BiMap<String, String> namespaces = getRegisteredObject(
				createKey(ALL_NAMESPACE), HashBiMap.<String, String> create());
		String prefix = namespaces.get(uri);
		if (prefix != null) {
			return new PSNamespace(uri, prefix);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pshow.repo.schema.ContentSchemaHolder#getAllNamespace()
	 */
	@Override
	public List<PSNamespace> getAllNamespace() {
		ArrayList<PSNamespace> namespace = new ArrayList<PSNamespace>();
		BiMap<String, String> namespaces = getRegisteredObject(
				createKey(ALL_NAMESPACE), HashBiMap.<String, String> create());
		Iterator<Entry<String, String>> iterator = namespaces.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) iterator
					.next();
			namespace.add(new PSNamespace(entry.getKey(), entry.getValue()));
		}
		return namespace;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pshow.repo.schema.ContentSchemaHolder#getContentType(org.pshow.repo
	 * .datamodel.namespace.QName)
	 */
	@Override
	public TypeModel getContentType(QName name) {
		return getRegisteredObject(name, new TypeModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pshow.repo.schema.ContentSchemaHolder#getAllContentType()
	 */
	@Override
	public List<TypeModel> getAllContentType() {
		return getRegisteredObject(createKey(ALL_TYPE),
				new ArrayList<TypeModel>());
	}

	@Override
	public boolean hasRegisteredObject(QName name) {
		return store.contains(name);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E> E getRegisteredObject(QName name, E e) {
		return (E) store.get(name);
	}

	@Override
	public FacetModel getFacet(QName name) {
		return getRegisteredObject(name, new FacetModel());
	}

	@Override
	public List<FacetModel> getAllFacet() {
		return getRegisteredObject(createKey(ALL_FACET),
				new ArrayList<FacetModel>());
	}

	@Override
	public ConstraintModel getConstraint(QName name) {
		return getRegisteredObject(name, new ConstraintModel());
	}

	@Override
	public List<ConstraintModel> getAllConstraint() {
		return getRegisteredObject(createKey(ALL_CONSTRAINT),
				new ArrayList<ConstraintModel>());
	}

	@Override
	public boolean hasNamespace(QName name) {
		if (store.contains(name)) {
			return ((store.get(name) instanceof PSNamespace) ? true : false);
		}
		return false;
	}

	@Override
	public boolean hasContentType(QName name) {
		if (store.contains(name)) {
			return ((store.get(name) instanceof TypeModel) ? true : false);
		}
		return false;
	}

	@Override
	public boolean hasFacet(QName name) {
		if (store.contains(name)) {
			return ((store.get(name) instanceof FacetModel) ? true : false);
		}
		return false;
	}

	@Override
	public boolean hasConstraint(QName name) {
		if (store.contains(name)) {
			return ((store.get(name) instanceof ConstraintModel) ? true : false);
		}
		return false;
	}

	public void setStore(Store<QName, Object> store) {
		this.store = store;
	}

	@Override
	public PSNamespace getNamespaceByPrefix(String prefix) {
		BiMap<String, String> namespaces = getRegisteredObject(
				createKey(ALL_NAMESPACE), HashBiMap.<String, String> create());
		String uri = namespaces.inverse().get(prefix);
		if (uri != null) {
			return new PSNamespace(uri, prefix);
		}
		return null;
	}

}
