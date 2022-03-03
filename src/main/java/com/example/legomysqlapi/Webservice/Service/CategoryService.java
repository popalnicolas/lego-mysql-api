package com.example.legomysqlapi.Webservice.Service;

import com.example.legomysqlapi.Model.CategoryModel;
import com.example.legomysqlapi.Repository.ICategoryRepository;
import com.example.legomysqlapi.Webservice.Service.Interface.ICategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    @Override
    public void createCategory(CategoryModel category) {
        log.info("Creating new category {}", category.getCategoryName());
        categoryRepository.save(category);
    }

    @Override
    public boolean categoryExists(CategoryModel category) {
        return categoryRepository.exists(Example.of(category));
    }

    @Override
    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }
}
