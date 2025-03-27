package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Sort;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping(path = "")
    public List<Product> index(@RequestParam (name = "min", required = false) Integer startPrice, @RequestParam (name = "max", required = false) Integer endPrice) {
    	var sort = Sort.by(Sort.Order.asc("price"));
    	
    	if (startPrice != null && endPrice != null) {
        	return productRepository.findByPriceBetween(startPrice, endPrice, sort);
        }
    	else if (startPrice != null) {
    		return productRepository.findByPriceGreaterThanEqual(startPrice, sort);
    	}
    	else if (endPrice != null) {
    		return productRepository.findByPriceLessThanEqual(endPrice, sort);
    	}
        else {
        	return productRepository.findAll(sort);
        }
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product =  productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
