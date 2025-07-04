package org.mosesidowu.smeecommerce.services;

import com.cloudinary.Cloudinary;
import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.data.repository.ProductRepository;
import org.mosesidowu.smeecommerce.dtos.requests.CreateProductRequest;
import org.mosesidowu.smeecommerce.dtos.requests.ProductRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.AllProductResponse;
import org.mosesidowu.smeecommerce.dtos.responses.CreateProductResponse;
import org.mosesidowu.smeecommerce.exception.InvalidCategoryException;
import org.mosesidowu.smeecommerce.exception.ItemNotFoundException;
import org.mosesidowu.smeecommerce.exception.UnauthorizedActionException;
import org.mosesidowu.smeecommerce.exception.UserException;
import org.mosesidowu.smeecommerce.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mosesidowu.smeecommerce.utils.ProductMapper.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Cloudinary cloudinary;



    @Override
    public CreateProductResponse createProduct(CreateProductRequest request, MultipartFile imageFile) {
        try {
            Map uploadResult = cloudinary.uploader().upload(imageFile.getBytes(),Map.of());
            String imageUrl = uploadResult.get("url").toString();

            Product product = new Product();
            product.setProductImageUrl(imageUrl);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String sellerEmail = auth.getName();
            product.setCreatedBy(sellerEmail);

            Product saveProduct = mapProduct(product,request);
            productRepository.save(saveProduct);

            return mapProductToResponse(saveProduct);
        } catch (UserException | IOException e) {
            throw new UserException("Failed to upload image: " + e.getMessage());
        }
    }



    @Override
    public Product updateProduct(String productId, ProductRequestDTO productDTO) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ItemNotFoundException("Product with ID " + productId + " not found"));

         Product updateProduct = ProductMapper.updateMapperProductResponse(productDTO, existingProduct);

        return productRepository.save(updateProduct);
    }



    @Override
    public void deleteProduct(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ItemNotFoundException("Product with ID " + productId + " not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String currentSellerEmail  = authentication.getName();
       if (!product.getCreatedBy().equals(currentSellerEmail )) {
            throw new UnauthorizedActionException("You are not authorized to delete this product");
        }
        
        productRepository.delete(product);
    }


    @Override
    public List<AllProductResponse> viewAllProducts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentSellerEmail  = authentication.getName();

        List<Product> products = productRepository.findByCreatedBy(currentSellerEmail);
        if (products.isEmpty()) {
            return Collections.emptyList();
        }
        return products.stream()
                .map(ProductMapper::mapToResponse)
                .collect(Collectors.toList());

    }


    @Override
    public List<AllProductResponse> getProductByCategory(ProductCategory category) {
        List<Product> products = productRepository.findByProductCategoryContainingIgnoreCase(category);
        return ProductMapper.toAllProductsResponse(products);
    }



    @Override
    public List<AllProductResponse> searchProducts(String searchTerm) {

        List<Product> products;
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            products = productRepository.findAll();
        } else {
            products = productRepository.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
                    searchTerm.trim(), searchTerm.trim());
        }
        return products.stream()
                .map(ProductMapper::mapToResponse)
                .collect(Collectors.toList());
    }



    @Override
    public List<AllProductResponse> searchProductsByCategoryAndName(String categoryStr, String name) {
        ProductCategory category = ProductMapper.safeParseCategory(categoryStr);
        List<Product> products = productRepository.findByProductCategoryAndProductNameContainingIgnoreCase(category, name);
        return products.stream()
                .map(ProductMapper::mapToResponse)
                .collect(Collectors.toList());
    }




    public static ProductCategory fromString(String input) {
        try {
            return ProductCategory.valueOf(input.trim().toUpperCase());
        } catch (UserException e) {
            throw new InvalidCategoryException("Invalid product category: " + input);
        }
    }

}