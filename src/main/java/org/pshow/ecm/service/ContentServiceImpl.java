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
package org.pshow.ecm.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.pshow.ecm.content.ContentService;
import org.pshow.ecm.content.metadata.ContentSchemaHolder;
import org.pshow.ecm.content.model.Content;
import org.pshow.ecm.content.model.ContentImpl;
import org.pshow.ecm.content.model.Facet;
import org.pshow.ecm.content.model.PropertyValue;
import org.pshow.ecm.content.model.QName;
import org.pshow.ecm.content.model.Type;
import org.pshow.ecm.content.model.Workspace;
import org.pshow.ecm.content.persistence.model.ContentModel;
import org.pshow.ecm.content.persistence.model.Path;
import org.pshow.ecm.content.persistence.model.WorkspaceModel;
import org.pshow.ecm.repository.ContentDao;
import org.pshow.ecm.repository.PathDao;
import org.pshow.ecm.repository.WorkspaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author topcat
 * 
 */
@Service
@Transactional
public class ContentServiceImpl implements ContentService {

	private WorkspaceDao workspaceDao;
	private ContentDao contentDao;
	private PathDao pathDao;
	
	private ContentSchemaHolder csh;

	@Override
	public String getRoot(String workspace) {
		WorkspaceModel workspaceModel = null;
		if (StringUtils.isBlank(workspace)) {
			// 如果workspace是空返回默认workspace的root
			workspace = "default";
			workspaceModel = new WorkspaceModel(workspace);
			if (workspaceDao.countByName(workspace) == 0) {
				Path path = new Path();
				path.setParent(null);
				path.setPath("/");
				pathDao.save(path);
				// 如果不存在默认worspace则创建一个
				workspaceModel.setRoot(UUID.randomUUID().toString());
				workspaceDao.save(workspaceModel);
			}
		}
		workspaceModel = workspaceDao.findByName(workspace);
		if (workspaceModel != null) {
			return workspaceModel.getRoot();
		}
		return null;
	}

	@Override
	public Map<String, PropertyValue> getProperties(String contentId) {
		ContentModel contentModel = contentDao.findByUuid(contentId);
		ContentImpl contentImpl = new ContentImpl(contentModel);
		return contentImpl.getProperties();
	}

	@Override
	public PropertyValue getProperty(String contentId, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperty(String contentId, String name, PropertyValue value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProperites(String contentId,
			Map<String, PropertyValue> values) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addProperty(String contentId, String name, PropertyValue value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeProperty(String contentId, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public Type getType(String contentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Facet> getFacets(String contentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Content> getChild(String contentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createContent(String type, String parentId, String name) {
		ContentModel contentModel = new ContentModel();
		contentModel.setName(name);
		contentModel.setContentType(type);
		contentModel.setUuid(UUID.randomUUID().toString());
		ContentModel saved = contentDao.save(contentModel);
		Path path = new Path();
		ContentModel parentContent = contentDao.findByUuid(parentId);
		path.setPath("/"+name);
		pathDao.save(path);
		return null;
	}

	@Override
	public String createContent(String type, String parentId, String name,
			Map<String, PropertyValue> properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeContent(String contentId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Workspace createWorkspace(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Workspace findWorkspace(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Autowired
	public void setWorkspaceDao(WorkspaceDao workspaceDao) {
		this.workspaceDao = workspaceDao;
	}

	@Autowired
	public void setContentDao(ContentDao contentDao) {
		this.contentDao = contentDao;
	}

	@Autowired
	public void setPathDao(PathDao pathDao) {
		this.pathDao = pathDao;
	}

	@Autowired
	@Qualifier("contentSchemaHolder")
	public void setCsh(ContentSchemaHolder csh) {
		this.csh = csh;
	}

}
