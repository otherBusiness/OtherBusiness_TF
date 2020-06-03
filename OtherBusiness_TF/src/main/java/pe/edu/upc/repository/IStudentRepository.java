package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.entity.Student;

public interface IStudentRepository extends JpaRepository<Student, Integer>{
	@Query("select count (p.emailStudent) from Student p where p.emailStudent= LOWER(:emailStudent) or p.emailStudent=UPPER(:emailStudent)")
	public int searchStudent(@Param("emailStudent") String proyecto);
	
}
