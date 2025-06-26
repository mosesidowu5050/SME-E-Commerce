package org.mosesidowu.smeecommerce.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mosesidowu.smeecommerce.dtos.requests.CreateItemRequest;
import org.mosesidowu.smeecommerce.exception.UserException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private Cloudinary cloudinary;

    @Mock
    private Uploader uploader;
    @BeforeEach
    public void setUp() {
        when(cloudinary.uploader()).thenReturn(uploader);
    }
    @Test
    public void createProduct_throwsExceptionWhenUploadFails() throws IOException {
        CreateItemRequest request = new CreateItemRequest();
        request.setProductName("Samsung Galaxy");
        request.setDescription("Latest smartphone");
        request.setPrice(800_000.0);
        request.setQuantity(5);
        request.setCategory("ELECTRONICS");
        request.setImageUrl("https://example.com/sample-image.jpg");

        MockMultipartFile image = new MockMultipartFile(
                "image",
                "galaxy.jpg",
                "image/jpeg",
                "new image data".getBytes()
        );

        when(cloudinary.uploader().upload(any(byte[].class), any(Map.class)))
                .thenThrow(new UserException("Missing required parameter - file"));

        UserException exception = assertThrows(UserException.class, () -> {
            productService.createProduct(request, image);
        });

        assertEquals("Failed to upload image: Missing required parameter - file", exception.getMessage());
    }

}
