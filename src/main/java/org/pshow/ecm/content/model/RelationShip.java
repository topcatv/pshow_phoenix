package org.pshow.ecm.content.model;


public interface RelationShip {
	public Content getTarget();
	public Content getSource();
	public RelationType getType();
}
