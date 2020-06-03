package pe.edu.upc.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Student;
import pe.edu.upc.repository.IStudentRepository;
import pe.edu.upc.serviceinterface.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{

	@Autowired
	private IStudentRepository sR;
	
	@Transactional
	@Override
	public int insert(Student student) {
		int rpta = sR.searchStudent(student.getEmailStudent());
		if(rpta==0) {
			sR.save(student);
		}
		return rpta;
	}

	@Override
	public List<Student> list() {
		return sR.findAll();
	}

	
}
