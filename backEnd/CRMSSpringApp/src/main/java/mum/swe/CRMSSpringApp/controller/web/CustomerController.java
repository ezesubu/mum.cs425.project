package mum.swe.CRMSSpringApp.controller.web;


import mum.swe.CRMSSpringApp.model.Category;
import mum.swe.CRMSSpringApp.model.Customer;
import mum.swe.CRMSSpringApp.repository.CustomerRepository;
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
    private CustomerRepository customerRepository;


    @RequestMapping(value = "/customer/list", method = RequestMethod.GET)
    public ModelAndView list() {
        List<Customer> customers = customerRepository.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("customers", customers);
        modelAndView.setViewName("customer/list");

        return modelAndView;
    }

    @RequestMapping(value = "/customer/new", method = RequestMethod.GET)
    public String create(Model model) {


        model.addAttribute("customer", new Customer());


        return "customer/edit";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("customer") Customer customer,
                       BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "customer/edit";
        }
        customer = customerRepository.save(customer);
        return "redirect:/customer/list";
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, Model model) {

        model.addAttribute("customer", customerRepository.findById(id).get());
        return "customer/edit";
    }

    @RequestMapping(value = "/customer/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model) {
        customerRepository.delete(this.customerRepository.findById(id).get());
        return "redirect:/customer";
    }
}
