package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> index() {
        var products = productRepository.findAll();
        var result = products.stream()
                	.map(productMapper::map)
                	.toList();

        return result;
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO show(@PathVariable Long id) {
        var product = productRepository.findById(id)
                	.orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        var productDTO = productMapper.map(product);
        return productDTO;
    }
    
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO productData) {
        var product = productMapper.map(productData);
        productRepository.save(product);
        var productDTO = productMapper.map(product);
        return productDTO;
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO update(@RequestBody ProductUpdateDTO productData, @PathVariable Long id) {
        var product = productRepository.findById(id)
                	.orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        productMapper.update(productData, product);
        productRepository.save(product);
        var productDTO = productMapper.map(product);
        return productDTO;
    }
    // END
}
