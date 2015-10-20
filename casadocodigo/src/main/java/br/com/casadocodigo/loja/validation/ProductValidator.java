package br.com.casadocodigo.loja.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.dao.ProductDAO;
import br.com.casadocodigo.loja.model.Product;

@Component
public class ProductValidator implements Validator {

	@Autowired
	private ProductDAO productDao;

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Product product = (Product) target;
		
		if (product.getNumberOfPages() != null && product.getNumberOfPages() == 0) {
			errors.rejectValue("numberOfPages", "field.required");
		}
		
		if (product.getTitle() != null && productDao.existsTitle(product)) {
			errors.rejectValue("title", "title.unique");
		}
	}

	
}
