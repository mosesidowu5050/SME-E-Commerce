package org.mosesidowu.smeecommerce.services;

import com.cloudinary.Cloudinary;
import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.data.repository.ProductRepository;
import org.mosesidowu.smeecommerce.dtos.requests.CreateProductRequest;
import org.mosesidowu.smeecommerce.dtos.requests.ProductRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.AllProductsResponse;
import org.mosesidowu.smeecommerce.dtos.responses.CreateProductResponse;
import org.mosesidowu.smeecommerce.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
            return ProductMapper.mapProductToResponse(product);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product updateProduct(String productId, ProductRequestDTO product) {
        return null;
    }

    @Override
    public void deleteProduct(String productId) {
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
