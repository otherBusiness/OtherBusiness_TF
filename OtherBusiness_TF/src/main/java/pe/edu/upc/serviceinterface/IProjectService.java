package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Project;

public interface IProjectService {
public int insert(Project project);

List<Project> list();

public void delete(int idProject);

Optional<Project> searchId(int idProject);


public List<Project> search(String busqueda);
}
