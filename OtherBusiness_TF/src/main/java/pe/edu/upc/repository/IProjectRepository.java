package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.entity.Project;

public interface IProjectRepository extends JpaRepository<Project, Integer>{

}
