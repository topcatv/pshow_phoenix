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

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pshow.ecm.content.model.definition.DataType;
import org.pshow.ecm.content.model.definition.PSModel;
import org.pshow.ecm.content.model.definition.PropertyModel;
import org.pshow.ecm.content.model.definition.TypeModel;
import org.pshow.ecm.utils.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringContextTestCase;

/**
 * @author topcat
 * 
 */
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class DefaultContetntSchemaHolderTest extends SpringContextTestCase {

	@Autowired
	@Qualifier("contentSchemaHolder")
	private ContentSchemaHolder csh;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}
	
	public static void main(String[] args) throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("test.xml");
		File file = classPathResource.getFile();
		PSModel psmodel = getTestModel();
		XmlUtil.toXMLFile(psmodel, file);
	}

	private static PSModel getTestModel() {
		PSModel psModel = new PSModel();
		psModel.setAuthor("Roy");
		psModel.setName("Test");
		psModel.setDescription("None");
		psModel.createNamespace("http://pshow.org", "ps");
		DataType int_type = psModel.createPropertyType("ps:int");
		int_type.setJavaClassName("java.lang.Integer");
		TypeModel type = psModel.createType("ps:base");
		List<PropertyModel> properties = new ArrayList<PropertyModel>();
		PropertyModel pm = new PropertyModel();
		properties.add(pm);
		pm.setName("name");
		pm.setDataTypeName(int_type.getName());
		type.setProperties(properties);
		return psModel;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		List<TypeModel> type = csh.getAllContentType();
		assertNotNull(type);
		assertTrue(type.size() > 0);
	}

}
