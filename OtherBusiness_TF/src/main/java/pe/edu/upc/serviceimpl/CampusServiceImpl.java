package pe.edu.upc.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Campus;
import pe.edu.upc.repository.ICampusRepository;
import pe.edu.upc.serviceinterface.ICampusService;

@Service
public class CampusServiceImpl implements ICampusService { // conectar con el repositoy para hacer uso de jpaRepository

	@Autowired // indica en spring mvn -> injecion de dependencias que necesita el programa
	private ICampusRepository cR;

	@Override
	public List<pe.edu.upc.entity.Campus> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

	@Transactional
	@Override
	public int insert(Campus campus) {
		int rpta = cR.searchCampus(campus.getNameCampus()); // tendra un valor entero para saber si ya extiste un campus
															// con ese nombre
		if (rpta == 0) {
			cR.save(campus);
		} // si es igual a cero no existe por lo tanto inserto  //saber si existe o no a travez de una varible entera
		return rpta;
	}

}
