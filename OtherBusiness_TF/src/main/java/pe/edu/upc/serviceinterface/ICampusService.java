package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Campus;

public interface ICampusService {

	public int insert(Campus campus);
	
	List<Campus> list();
}
