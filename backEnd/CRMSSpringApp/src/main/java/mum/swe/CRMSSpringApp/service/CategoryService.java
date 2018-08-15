package mum.swe.CRMSSpringApp.service;

import mum.swe.CRMSSpringApp.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> findAll();

    Category save(Category car);

    //	 Car findOne(Long id);
    Category findById(Long id);

    void delete(Long id);
}
