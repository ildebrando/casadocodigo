package br.com.casadocodigo.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.model.BookType;
import br.com.casadocodigo.loja.model.Product;

@Repository
public class ProductDAO {

	@PersistenceContext
	private EntityManager em;

	public List<Product> list() {
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct(p) ");
		sb.append("from Product p ");
		sb.append("join fetch p.prices ");
		
		return em.createQuery(sb.toString(), Product.class).getResultList();
	}

	public void save(Product product) {
		em.persist(product);
	}

	public boolean existsTitle(Product product) {
		StringBuilder sb = new StringBuilder();
		sb.append("select p ");
		sb.append("from Product p ");
		sb.append("where upper(p.title) = :title ");
		
		Query query = em.createQuery(sb.toString());
		query.setParameter("title", product.getTitle().toUpperCase());
		
		return !query.getResultList().isEmpty();
	}

	public Product find(Integer id) {
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct(p) ");
		sb.append("from Product p ");
		sb.append("join fetch p.prices ");
		sb.append("where p.id = :id ");
		
		Query query = em.createQuery(sb.toString(), Product.class);
		query.setParameter("id", id);
		
		return (Product) query.getSingleResult();
	}

	public BigDecimal sumPricesPerType(BookType bookType) {
		TypedQuery<BigDecimal> query = em.createQuery(
				"select sum(price.value) from Product p " +
				"join p.prices price " +
				"where price.bookType = :bookType ", BigDecimal.class);
		query.setParameter("bookType", bookType);
		return query.getSingleResult();
	}

}
