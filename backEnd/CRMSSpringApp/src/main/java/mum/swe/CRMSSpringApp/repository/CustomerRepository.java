package mum.swe.CRMSSpringApp.repository;

import mum.swe.CRMSSpringApp.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Customer findByUsername(String username);
    List<Customer> findByFirstName(@Param("firstName") String firstName);

}
