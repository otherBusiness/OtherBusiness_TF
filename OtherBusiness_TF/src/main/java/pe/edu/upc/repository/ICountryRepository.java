package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.entity.Country;

public interface ICountryRepository extends JpaRepository<Country, Integer>{

}
