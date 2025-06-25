package org.mosesidowu.smeecommerce.services;

import com.cloudinary.Cloudinary;
import org.mosesidowu.smeecommerce.data.models.Item;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.data.repository.ItemRepository;
import org.mosesidowu.smeecommerce.dtos.requests.CreateItemRequest;
import org.mosesidowu.smeecommerce.dtos.requests.ItemRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.AllItemResponse;
import org.mosesidowu.smeecommerce.dtos.responses.CreateItemResponse;
import org.mosesidowu.smeecommerce.exception.InvalidCategoryException;
import org.mosesidowu.smeecommerce.exception.ItemNotFoundException;
import org.mosesidowu.smeecommerce.exception.UnauthorizedActionException;
import org.mosesidowu.smeecommerce.exception.UserException;
import org.mosesidowu.smeecommerce.utils.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository productRepository;
    @Autowired
    private Cloudinary cloudinary;



    @Override
    public CreateItemResponse createProduct(CreateItemRequest request, MultipartFile imageFile) {
        try {
            Map uploadResult = cloudinary.uploader().upload(imageFile.getBytes(),Map.of());
            String imageUrl = uploadResult.get("url").toString();

            Item product = new Item();
            ItemMapper.mapProduct(product,request);
            product.setProductImageUrl(imageUrl);
            productRepository.save(product);

            return ItemMapper.mapProductToResponse(product);
        } catch (UserException | IOException e) {
            throw new UserException("Failed to upload image: " + e.getMessage());
        }
    }


    @Override
    public Item updateProduct(String productId, ItemRequestDTO productDTO) {
        Item existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ItemNotFoundException("Product with ID " + productId + " not found"));

        ItemMapper.updateMapperProductResponse(productDTO, existingProduct);

        return productRepository.save(existingProduct);
    }



    @Override
    public void deleteProduct(String productId) {
        Item product = productRepository.findById(productId)
                .orElseThrow(() -> new ItemNotFoundException("Product with ID " + productId + " not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (!product.getProductName().equals(email)) {
            throw new UnauthorizedActionException("You are not allowed to delete this product");
        }

        productRepository.delete(product);
    }


    @Override
    public List<AllItemResponse> getProductByCategory(ProductCategory category) {
        List<Item> products = productRepository.findByProductCategoryContainingIgnoreCase(category);
        return ItemMapper.toAllProductsResponse(products);
    }

    @Override
    public List<Item> searchProducts(String searchTerm) {
        return productRepository.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
                searchTerm, searchTerm);
    }

    @Override
    public List<Item> searchProductsByCategoryAndName(String categoryStr, String name) {
        ProductCategory category = fromString(categoryStr);
        return productRepository.findByProductCategoryAndProductNameContainingIgnoreCase(category, name);
    }


    public static ProductCategory fromString(String input) {
        try {
            return ProductCategory.valueOf(input.trim().toUpperCase());
        } catch (UserException e) {
            throw new InvalidCategoryException("Invalid product category: " + input);
        }
    }

}