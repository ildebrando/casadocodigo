package br.com.casadocodigo.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.casadocodigo.loja.builders.ProductBuilder;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.dao.ProductDAO;
import br.com.casadocodigo.loja.model.BookType;
import br.com.casadocodigo.loja.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, ProductDAO.class,
		DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class ProductDAOTest {

	@Autowired
	private ProductDAO productDAO;

	@Transactional
	@Test
	public void shouldSumAllPricesOfEachBookPerType() {
		
		List<Product> printedBooks = ProductBuilder
				.newProduct(BookType.PRINTED, BigDecimal.TEN)
				.more(4).buildAll();
		printedBooks.stream().forEach(productDAO::save);
		
		List<Product> ebooks = ProductBuilder
				.newProduct(BookType.EBOOK, BigDecimal.TEN)
				.more(4).buildAll();
		ebooks.stream().forEach(productDAO::save);
		
		BigDecimal valuePrinted = productDAO.sumPricesPerType(BookType.PRINTED);
		BigDecimal valueEbook = productDAO.sumPricesPerType(BookType.EBOOK);
		
//		Assert.assertEquals(new BigDecimal(50).setScale(2), valuePrinted);
//		Assert.assertEquals(new BigDecimal(50).setScale(2), valueEbook);
		
		BigDecimal[] typeValues = { valuePrinted, valueEbook };
		BigDecimal[] testValues = { new BigDecimal(50).setScale(2), new BigDecimal(50).setScale(2) };
		Assert.assertArrayEquals(typeValues, testValues);
	}

	@Transactional
	@Test
	public void shouldSumAllPricesOfEbook() {
		
		List<Product> printedBooks = ProductBuilder
				.newProduct(BookType.PRINTED, BigDecimal.TEN)
				.more(4).buildAll();
		printedBooks.stream().forEach(productDAO::save);
		
		List<Product> ebooks = ProductBuilder
				.newProduct(BookType.EBOOK, new BigDecimal(5))
				.more(4).buildAll();
		ebooks.stream().forEach(productDAO::save);
		
		BigDecimal valuePrinted = productDAO.sumPricesPerType(BookType.PRINTED);
		Assert.assertEquals(new BigDecimal(25).setScale(2), valuePrinted);
		
//		BigDecimal valueEbook = productDAO.sumPricesPerType(BookType.EBOOK);
//		Assert.assertEquals(new BigDecimal(50).setScale(2), valueEbook);
	}

}
