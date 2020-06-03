package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Campus;

public interface ICampusService {

	public int insert(Campus campus);
	
	List<Campus> list();
	
	
	public void delete(int idCampus);  //ir al implemento
	
	
	
	Optional<Campus> searchId(int idProduct); //busca el registro q quiero edithar y llevarlo al formulario
}
