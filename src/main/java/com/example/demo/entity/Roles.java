package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
@Entity
public class Roles {
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Integer roleid;
	private String rolename;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="roles_nodes",
			joinColumns= {@JoinColumn(name="r_id",referencedColumnName="roleid")},
			inverseJoinColumns= {@JoinColumn(name="n_id",referencedColumnName="nodeid")}
			
			)
	private List<Nodes> nodes;
	
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public List<Nodes> getNodes() {
		return nodes;
	}
	public void setNodes(List<Nodes> nodes) {
		this.nodes = nodes;
	}
	@Override
	public String toString() {
		return "Roles [roleid=" + roleid + ", rolename=" + rolename + ", nodes=" + nodes + "]";
	}
}
