package mum.swe.CRMSSpringApp.controller.api;

import mum.swe.CRMSSpringApp.model.Customer;
import mum.swe.CRMSSpringApp.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("api/users")
public class UserController {

    private CustomerRepository customerRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(CustomerRepository customerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping(value = "/sign-up")
    public void signUp(@RequestBody Customer user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        customerRepository.save(user);
    }


}
