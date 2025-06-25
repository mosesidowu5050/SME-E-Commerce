package org.mosesidowu.smeecommerce.services;

import com.cloudinary.Cloudinary;
import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.data.repository.ProductRepository;
import org.mosesidowu.smeecommerce.dtos.requests.CreateProductRequest;
import org.mosesidowu.smeecommerce.dtos.requests.ProductRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.AllProductsResponse;
import org.mosesidowu.smeecommerce.dtos.responses.CreateProductResponse;
import org.mosesidowu.smeecommerce.exception.ProductNotFoundException;
import org.mosesidowu.smeecommerce.exception.UnauthorizedActionException;
import org.mosesidowu.smeecommerce.exception.UserException;
import org.mosesidowu.smeecommerce.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
            ProductMapper.mapProduct(product,request);
            product.setProductImageUrl(imageUrl);
            productRepository.save(product);
            return ProductMapper.mapProductToResponse(new Product());
        } catch (UserException | IOException e) {
            throw new UserException("Failed to upload image: " + e.getMessage());
        }
    }


    @Override
    public Product updateProduct(String productId, ProductRequestDTO productDTO) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));

        existingProduct.setProductName(productDTO.getProductName());
        existingProduct.setProductDescription(productDTO.getProductDescription());
        existingProduct.setProductPrice(productDTO.getProductPrice());
        existingProduct.setProductQuantity(productDTO.getProductQuantity());
        existingProduct.setProductCategory(productDTO.getProductCategory());

        return productRepository.save(existingProduct);
    }


    @Override
    public void deleteProduct(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (!product.getProductName().equals(email)) {
            throw new UnauthorizedActionException("You are not allowed to delete this product");
        }


        productRepository.delete(product);
    }


    @Override
    public List<AllProductsResponse> getProductByCategory(ProductCategory category) {
        List<Product> products = productRepository.findByProductCategoryContainingIgnoreCase(category);
        return ProductMapper.toAllProductsResponse(products);
    }

    @Override
    public List<Product> searchProducts(String searchTerm) {
        return List.of();
    }
}