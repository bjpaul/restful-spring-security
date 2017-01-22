package org.basic.spring.security.rest.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Authentication {

	@Id
	private String username;
	private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
    public Authentication(){}
    
	public Authentication(String username, String password, Set<Role> roles){
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    public void addRole(Role role) {
        Set<Role> roles = this.getRoles();
        if (roles == null) {
            roles = new HashSet<Role>();
            this.setRoles(roles);
        }
        roles.add(role);

        Set<Authentication> users = role.getUsers();
        if (users == null) {
            users = new HashSet<Authentication>();
            role.setUsers(users);
        }
        users.add(this);

    }
    
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
	
}
