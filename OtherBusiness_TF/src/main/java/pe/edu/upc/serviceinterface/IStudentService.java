package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Student;

public interface IStudentService {

	public int insert(Student student);

	List<Student> list();
}
