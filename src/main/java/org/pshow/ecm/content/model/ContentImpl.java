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
package org.pshow.ecm.content.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pshow.ecm.content.persistence.model.ContentModel;
import org.pshow.ecm.content.persistence.model.Property;

/**
 * @author topcat
 *
 */
public class ContentImpl implements Content {
	private ContentModel cm;
	
	public ContentImpl(ContentModel cm) {
		this.cm = cm;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getModified() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getModifier() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getCreated() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getCreator() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rename(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDescription(String desc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PropertyValue getProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Facet> getFacets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFolder() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, PropertyValue> getProperties() {
		List<Property> properties = cm.getProperties();
		HashMap<String, PropertyValue> map = new HashMap<String, PropertyValue>(properties.size());
		for (Property property : properties) {
			String name = property.getName();
		}
		return map;
	}

	@Override
	public void addProperty(String name, PropertyValue property) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProperties(Map<String, PropertyValue> properties) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeProperty(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Content> getChilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addContent(Content content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeContent(Content content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveTo(Content content) {
		// TODO Auto-generated method stub
		
	}

}
