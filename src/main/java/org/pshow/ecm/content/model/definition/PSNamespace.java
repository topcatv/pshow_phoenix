package org.pshow.ecm.content.model.definition;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("namespace")
public class PSNamespace {
	@XStreamAsAttribute
	public String uri;
	@XStreamAsAttribute
	public String prefix;
	
	public PSNamespace() {
	}

	public PSNamespace(String uri, String prefix) {
		this.uri = uri;
		this.prefix = prefix;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
