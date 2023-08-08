package com.blogproject.Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.ManyToAny;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User implements UserDetails{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String about;
	
	  
		@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
		private List<Post> posts = new ArrayList<>();
		
		@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
		@JoinTable(name = "user_role",
		joinColumns = @JoinColumn(name="user",referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name="role",referencedColumnName = "id"))
		private Set<Role> roles = new HashSet<>();

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
		   List<SimpleGrantedAuthority> authorities = this.roles.stream().map((role)->  new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
			return authorities;
		}

		@Override
		public String getUsername() {
			
			return this.email;
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
		
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			
			return true;
		}

		@Override
		public boolean isEnabled() {
			
			return true;
		}

		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return null;
		}
		
//		EXPLAINATION-->CascadeType FEtch
//		The fetch attribute is set to FetchType.EAGER, which means that the related entities will be loaded eagerly when the entity is retrieved. This means that all of the related entities will be loaded in memory, even if they are not being used.
//
//		In simple language, this means that when you create, update, or delete an entity, the corresponding changes will be made to the related entities. Additionally, when you retrieve an entity, all of the related entities will be loaded in memory.

//		
//		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//		 private List<Comment> comment = new ArrayList<>();
}
