package pe.edu.upc.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Project;
import pe.edu.upc.repository.IProjectRepository;
import pe.edu.upc.serviceinterface.IProjectService;

@Service
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private IProjectRepository pR;
	
	@Transactional
	@Override
	public void insert(Project project) {
	pR.save(project);
		
	}

	@Override
	public List<Project> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}

}
