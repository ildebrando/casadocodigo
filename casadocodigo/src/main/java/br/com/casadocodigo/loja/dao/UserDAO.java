package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.model.User;

@Repository
public class UserDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		String jpql = "select u from User u " +
				"where upper(u.login) = :login ";
		
		TypedQuery<User> query = manager.createQuery(jpql, User.class);
		query.setParameter("login", username.toUpperCase());
		
		List<User> users = query.getResultList();
		
		if (users.isEmpty()) {
			throw new UsernameNotFoundException("O usuario "+ username +" nao existe");
		}
		return users.get(0);
	}

}
