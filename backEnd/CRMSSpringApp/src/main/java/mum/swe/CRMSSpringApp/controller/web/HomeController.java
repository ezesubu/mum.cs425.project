package mum.swe.CRMSSpringApp.controller.web;


import mum.swe.CRMSSpringApp.model.Booking;
import mum.swe.CRMSSpringApp.model.Category;
import mum.swe.CRMSSpringApp.service.BookingService;
import mum.swe.CRMSSpringApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController
{
    @Autowired
    private BookingService bookingServiceg;



    @RequestMapping(value = "/home", method = RequestMethod.GET)
    //"home/index"
    public ModelAndView home() {

        List<Booking> reservations = bookingServiceg.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("reservations", reservations);
        modelAndView.setViewName("home/index");
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "home/login";
    }
}

