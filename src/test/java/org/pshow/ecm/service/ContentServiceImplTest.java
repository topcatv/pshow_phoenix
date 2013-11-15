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

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pshow.ecm.content.ContentService;
import org.pshow.ecm.content.exception.DataTypeUnSupportExeception;
import org.pshow.ecm.content.model.PropertyValue;
import org.pshow.ecm.content.model.PropertyValueImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

/**
 * @author topcat
 *
 */
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class ContentServiceImplTest extends SpringTransactionalTestCase{
	
	private ContentService contentService;
	private String contentId;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	private void createTestData() throws DataTypeUnSupportExeception {
		String root = contentService.getRoot(null);
		contentId = contentService.createContent("ps:base", root, "test");
		PropertyValue pv = new PropertyValueImpl(32);
		contentService.addProperty(contentId, "age", pv );
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.pshow.ecm.service.ContentServiceImpl#getRoot(java.lang.String)}.
	 */
	@Test
	public void testGetRoot() {
		String root = contentService.getRoot(null);
		assertNotNull(root);
		String noneRoot = contentService.getRoot("noreturn");
		assertNull(noneRoot);
	}

	/**
	 * Test method for {@link org.pshow.ecm.service.ContentServiceImpl#getProperties(java.lang.String)}.
	 */
	@Test
	public void testGetProperties() {
		try {
			createTestData();
			Map<String, PropertyValue> properties = contentService.getProperties(contentId);
			assertTrue(properties.keySet().contains("age"));
			assertEquals(32, properties.get("age").getIntValue().intValue());
		} catch (DataTypeUnSupportExeception e) {
			e.printStackTrace();
			fail("not to here");
		}
	}

	/**
	 * Test method for {@link org.pshow.ecm.service.ContentServiceImpl#getProperty(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetProperty() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pshow.ecm.service.ContentServiceImpl#setProperty(java.lang.String, java.lang.String, org.pshow.ecm.content.model.PropertyValue)}.
	 */
	@Test
	public void testSetProperty() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pshow.ecm.service.ContentServiceImpl#setProperites(java.lang.String, java.util.Map)}.
	 */
	@Test
	public void testSetProperites() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pshow.ecm.service.ContentServiceImpl#addProperty(java.lang.String, java.lang.String, org.pshow.ecm.content.model.PropertyValue)}.
	 */
	@Test
	public void testAddProperty() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pshow.ecm.service.ContentServiceImpl#removeProperty(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRemoveProperty() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pshow.ecm.service.ContentServiceImpl#getType(java.lang.String)}.
	 */
	@Test
	public void testGetType() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pshow.ecm.service.ContentServiceImpl#getFacets(java.lang.String)}.
	 */
	@Test
	public void testGetFacets() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pshow.ecm.service.ContentServiceImpl#getChild(java.lang.String)}.
	 */
	@Test
	public void testGetChild() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pshow.ecm.service.ContentServiceImpl#createContent(org.pshow.ecm.content.model.QName, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testCreateContentQNameStringString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pshow.ecm.service.ContentServiceImpl#createContent(org.pshow.ecm.content.model.QName, java.lang.String, java.lang.String, java.util.Map)}.
	 */
	@Test
	public void testCreateContentQNameStringStringMapOfStringPropertyValue() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pshow.ecm.service.ContentServiceImpl#removeContent(java.lang.String)}.
	 */
	@Test
	public void testRemoveContent() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pshow.ecm.service.ContentServiceImpl#createWorkspace(java.lang.String)}.
	 */
	@Test
	public void testCreateWorkspace() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pshow.ecm.service.ContentServiceImpl#findWorkspace(java.lang.String)}.
	 */
	@Test
	public void testFindWorkspace() {
		fail("Not yet implemented");
	}

	@Autowired
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

}
