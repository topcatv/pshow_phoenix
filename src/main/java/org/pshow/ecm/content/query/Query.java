package org.pshow.ecm.content.query;

import java.util.List;

import org.pshow.ecm.content.model.QName;


public interface Query {
	public Query from(QName typeName);
	public Query select(List fields);
	public Condition where();
	public Query orderBy(List fileds, boolean isAsc);
	public Query setPageSize(int ps);
	public Query setStartPage(int sp);
}
