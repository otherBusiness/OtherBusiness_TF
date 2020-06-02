package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.entity.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Integer>{

}
