package com.example.legomysqlapi.Webservice.Api;

import com.example.legomysqlapi.Model.CategoryModel;
import com.example.legomysqlapi.Webservice.Service.Interface.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lego/category")
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryModel>> getAllCategories()
    {
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity createCategory(@RequestBody CategoryModel category)
    {
        if(categoryService.categoryExists(category)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Category already exists");
        }
        else {
            categoryService.createCategory(category);
            return ResponseEntity.ok().build();
        }
    }
}
