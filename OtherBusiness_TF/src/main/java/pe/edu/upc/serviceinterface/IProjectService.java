package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Project;

public interface IProjectService {
public void insert(Project project);

List<Project> list();
}
