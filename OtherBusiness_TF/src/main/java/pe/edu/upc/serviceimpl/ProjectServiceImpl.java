package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

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

	
	/*public void insert(Project project) {
		pR.save(project);
	}*/
	
	@Transactional
	@Override
	public int insert(Project project) {
		int rpta=pR.searchProject(project.getNameProject());
		if(rpta==0) {
			pR.save(project);
		}
		return rpta;
	}

	@Transactional
	@Override
	public void delete(int idProject) {
		pR.deleteById(idProject);
		
	}


	@Override
	public Optional<Project> searchId(int idProject) {
		// TODO Auto-generated method stub
		return pR.findById(idProject);
	}


	@Override
	public List<Project> search(String busqueda) {
		// TODO Auto-generated method stub
		return pR.search(busqueda);
	}


	






	

	

}
