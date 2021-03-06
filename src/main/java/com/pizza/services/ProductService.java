package com.pizza.services;

import com.pizza.domain.dto.ProductFormDTO;
import com.pizza.domain.entities.Product;
import com.pizza.repository.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    private static final Logger LOG = Logger.getLogger(ProductService.class.getName());

    @Autowired
    private ProductRepository productRepository;

    public void addNewProduct(ProductFormDTO productFormDTO) {
        Product product = new Product();
        product.setName(productFormDTO.getName());
        product.setDetails(productFormDTO.getDetails());
        product.setActive(false);
        try {
            product.setPicture(productFormDTO.getFile().getBytes());
        } catch (IOException e) {
            LOG.error(e);
        }
        productRepository.create(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getProduct(long id) {
        return productRepository.read(id);
    }

    public ProductFormDTO getProductFormDTOFromProduct(Product product) {
        ProductFormDTO productFormDTO = new ProductFormDTO();
        productFormDTO.setId(product.getId());
        productFormDTO.setName(product.getName());
        productFormDTO.setDetails(product.getDetails());
        return productFormDTO;
    }

    public void updateProductFromProductFormDTO(ProductFormDTO productFormDTO, long productId) {
        Product product = productRepository.read(productId);
        product.setName(productFormDTO.getName());
        product.setDetails(productFormDTO.getDetails());
        product.setActive(false);
        if (!productFormDTO.getFile().isEmpty()) {
            try {
                product.setPicture(productFormDTO.getFile().getBytes());
            } catch (IOException e) {
                LOG.error(e);
            }
        }
        productRepository.update(product);
    }
}
