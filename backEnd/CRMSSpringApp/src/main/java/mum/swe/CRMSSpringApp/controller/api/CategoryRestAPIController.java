package mum.swe.CRMSSpringApp.controller.api;

import mum.swe.CRMSSpringApp.model.Category;
import mum.swe.CRMSSpringApp.service.CarService;
import mum.swe.CRMSSpringApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryRestAPIController {

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Category> getAllCategorys() {
        return categoryService.findAll();
    }

    @PostMapping(value = "/categories")
    public Category addCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @GetMapping(value = "/categories/{id}")
    public @ResponseBody
    Category findCategoryById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @DeleteMapping(value = "/categories/{id}")
    public @ResponseBody
    boolean deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return true;
    }

	@PutMapping(value = "/categories")
	public boolean updateCategory(@RequestBody Category category) {
		categoryService.save(category);
		return true;
	}
}
