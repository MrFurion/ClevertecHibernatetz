package by.clevertec.controllers;

import by.clevertec.dto.CategoryDtoRequest;
import by.clevertec.dto.CategoryDtoResponse;
import by.clevertec.exception.CarNotFoundException;
import by.clevertec.services.CategoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServices categoryServices;

    @PostMapping
    public ResponseEntity<String> creat(@Validated @RequestBody CategoryDtoRequest categoryDtoRequest) {
        categoryServices.create(categoryDtoRequest);
        return ResponseEntity.created(URI.create("/app/categories")).body("Category successfully created : " + categoryDtoRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            categoryServices.delete(id);
            return ResponseEntity.ok("Category successfully deleted with id: " + id);
        } catch (CarNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found with id: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id,
                                                      @Validated @RequestBody CategoryDtoRequest categoryDtoRequest) {

        CategoryDtoResponse categoryDtoResponse = categoryServices.update(categoryDtoRequest, id);


        Map<String, Object> response = new HashMap<>();
        response.put("message", "Category successfully updated.");
        response.put("updatedCategory", categoryDtoResponse);

        return ResponseEntity.ok(response);
    }
}
