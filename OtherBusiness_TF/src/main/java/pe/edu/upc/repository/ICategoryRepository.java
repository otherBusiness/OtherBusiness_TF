package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.entity.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer>{

}
