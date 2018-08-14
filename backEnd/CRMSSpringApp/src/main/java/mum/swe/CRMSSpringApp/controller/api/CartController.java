package mum.swe.CRMSSpringApp.controller.api;

import mum.swe.CRMSSpringApp.model.Car;
import mum.swe.CRMSSpringApp.service.CarService;
import mum.swe.CRMSSpringApp.service.CartCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartCustomerService cartCustomerService;

    @RequestMapping(value = "/carts", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    public boolean Add() {
        return cartCustomerService.Add();
    }
}
