package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
public class Nodes {
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Integer nodeid;
	private String nodename;
	private String nodeurl;
	@ManyToMany(mappedBy="nodes")
	private List<Roles> roles;
	
	public Integer getNodeid() {
		return nodeid;
	}

	public void setNodeid(Integer nodeid) {
		this.nodeid = nodeid;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	public String getNodeurl() {
		return nodeurl;
	}

	public void setNodeurl(String nodeurl) {
		this.nodeurl = nodeurl;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Nodes [nodeid=" + nodeid + ", nodename=" + nodename + ", nodeurl=" + nodeurl + ", roles=" + roles + "]";
	}
	
}
