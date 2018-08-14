package mum.swe.CRMSSpringApp.service;

import mum.swe.CRMSSpringApp.model.Customer;
import mum.swe.CRMSSpringApp.repository.api.CustomerRestRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private CustomerRestRepository customerRestRepository;


    public UserDetailsServiceImpl(CustomerRestRepository customerRestRepository) {
        this.customerRestRepository = customerRestRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer applicationUser = customerRestRepository.findByUsername(username);
        if(applicationUser == null){
            throw new UsernameNotFoundException(username);
        }

        return new User(applicationUser.getUsername(), applicationUser.getPassword(), Collections.emptyList());
    }
}
