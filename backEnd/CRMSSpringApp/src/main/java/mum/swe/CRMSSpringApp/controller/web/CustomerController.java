package mum.swe.CRMSSpringApp.controller.web;


import mum.swe.CRMSSpringApp.model.Category;
import mum.swe.CRMSSpringApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CustomerController {


    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/category/list", method = RequestMethod.GET)
    public ModelAndView list() {
        List<Category> category = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", category);
        modelAndView.setViewName("category/list");

        return modelAndView;
    }

    @RequestMapping(value = "/category/new", method = RequestMethod.GET)
    public String create(Model model) {


        model.addAttribute("category", new Category());


        return "category/edit";
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("category") Category category,
                       BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "category/edit";
        }
        category = categoryService.save(category);
        return "redirect:/category/list";
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, Model model) {

        model.addAttribute("category", categoryService.findById(id));
        return "category/edit";
    }

    @RequestMapping(value = "/category/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model) {
        categoryService.delete(id);
        return "redirect:/category";
    }
}
