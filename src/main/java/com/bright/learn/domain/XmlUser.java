package com.bright.learn.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "XmlUser")
public class XmlUser {

	@JacksonXmlProperty(localName = "name")
	private String name;
	@JacksonXmlProperty(localName = "age")
	private Integer age;

	public XmlUser() {
	}

	public XmlUser(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
