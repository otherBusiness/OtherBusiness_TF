package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.entity.Student;

public interface IStudentRepository extends JpaRepository<Student, Integer>{

}
