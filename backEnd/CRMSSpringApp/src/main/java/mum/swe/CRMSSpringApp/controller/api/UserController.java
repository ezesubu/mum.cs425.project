package mum.swe.CRMSSpringApp.controller.api;

import mum.swe.CRMSSpringApp.model.Customer;
import mum.swe.CRMSSpringApp.repository.api.CustomerRestRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    private CustomerRestRepository customerRestRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(CustomerRestRepository customerRestRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerRestRepository = customerRestRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping(value = "/sign-up")
    public void signUp(@RequestBody Customer user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        customerRestRepository.save(user);
    }


}
