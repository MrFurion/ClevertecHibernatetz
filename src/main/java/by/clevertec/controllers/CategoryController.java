package by.clevertec.controllers;

import by.clevertec.dto.CategoryDtoRequest;
import by.clevertec.services.CategoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/app/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServices categoryServices;

    @PostMapping
    public ResponseEntity<String> creat(@Validated @RequestBody CategoryDtoRequest categoryDtoRequest) {
        categoryServices.create(categoryDtoRequest);
        return ResponseEntity.created(URI.create("/app/categories")).body("Categories successfully created : " + categoryDtoRequest);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> delete(@PathVariable Long id) {
//        try {
//            carShowroomsServices.delete(id);
//            return ResponseEntity.ok("CarShowroom successfully deleted with id: " + id);
//        } catch (CarNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CarShowroom not found with id: " + id);
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id,
//                                                      @Validated @RequestBody CarShowroomDtoRequest carShowroomDtoRequest) {
//
//        CarShowroomDtoResponse carShowroomDtoResponse = carShowroomsServices.update(carShowroomDtoRequest, id);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "Car showroom successfully updated.");
//        response.put("updatedShowroom", carShowroomDtoResponse);
//
//        return ResponseEntity.ok(response);
//    }
}
