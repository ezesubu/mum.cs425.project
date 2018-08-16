package mum.swe.CRMSSpringApp.controller.api;

import mum.swe.CRMSSpringApp.model.Car;
import mum.swe.CRMSSpringApp.model.Category;
import mum.swe.CRMSSpringApp.service.CarService;
import mum.swe.CRMSSpringApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CarRestAPIController {
    @Autowired
    private CarService carService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Car> getAllCars() {
        return carService.findAll().stream().filter(car -> car.getStatus().equals("0")).collect(Collectors.toList());
    }

    @PostMapping(value = "/cars")
    public Car addCar(@RequestBody Car car) {
        return carService.save(car);
    }

    @GetMapping(value = "/cars/{id}")
    public @ResponseBody
    Car findCarById(@PathVariable Long id) {
    	Car car = carService.findById(id);
    	if(car == null) {
    		return  new Car();
    	}
        return car;
    }

    @DeleteMapping(value = "/cars/{id}")
    public @ResponseBody
    boolean deleteCar(@PathVariable Long id) {
        carService.delete(id);
        return true;
    }
    
	@PutMapping(value = "/cars")
	public boolean updateCar(@RequestBody Car car) {
		carService.save(car);
		return true;
	}
}
