package mum.swe.CRMSSpringApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface CartCustomerService {

    default boolean Add(){
        return true;
    };
}
