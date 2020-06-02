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
	
	

	@Override
	public List<Project> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}


	@Transactional
	@Override
	public int insert(Project project) {
		int rpta=pR.searchProject(project.getNameProject());
		if(rpta==0) {
			pR.save(project);
		}
		return rpta;
	}

/*
	@Override
	public List<Project> findNameProjectFull(String nameProject) {
		return pR.findBynameProject(nameProject);
	}


	*/


	

}
