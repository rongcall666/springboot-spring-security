package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.NodesRepository;
import com.example.demo.dao.RolesRepository;
import com.example.demo.dao.UsersRepository;
import com.example.demo.entity.Nodes;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Users;

/**
 *	验证用户名密码，并获取用户的角色，并保存用户信息 
 *
 */

@Service
public class CustomUserService implements UserDetailsService{
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private RolesRepository rolesRepository;
	@Autowired
	private NodesRepository nodesRepository;
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = usersRepository.findByUsername(username);
		if(users == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}else {
		BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
		List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
		for(Roles roles:users.getRoles()) {
			List<Nodes> nodes = roles.getNodes();
			for(Nodes node:nodes) {
				grantedAuthorities.add(new SimpleGrantedAuthority(node.getNodename()));
			}
		}
		return new User(users.getUsername(),"{bcrypt}"+encoder.encode(users.getPassword()),grantedAuthorities);
		}
	}
}
