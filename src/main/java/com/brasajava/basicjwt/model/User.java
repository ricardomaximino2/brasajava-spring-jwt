package com.brasajava.basicjwt.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonInclude;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="users")
public class User implements UserDetails, Serializable{
	private static final long serialVersionUID = 5071543417957713295L;
			
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String username;
	private String password;
	private String email; 
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
            name = "USERS_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
	private Collection<Role> roles;
	@Column(name = "ACCOUNTNONEXPIRED")
	private boolean accountNonExpired;
	@Column(name = "ACCOUNTNONLOCKED")
	private boolean accountNonLocked;
	@Column(name = "CREDENTIALSNONEXPIRED")
	private boolean credentialsNonExpired;
	private boolean enabled;
	@Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordResetDate;


	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public Collection<Role> getAuthorities() {
		return this.roles;
	}
	
	public void setAutorities(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}
	
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}
	
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", roles=" + roles + ", accountNonExpired=" + accountNonExpired + ", accountNonLocked="
				+ accountNonLocked + ", credentialsNonExpired=" + credentialsNonExpired + ", enabled=" + enabled + "]";
	}

}
