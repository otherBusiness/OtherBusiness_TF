package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.District;
import pe.edu.upc.repository.IDistrictRepository;
import pe.edu.upc.serviceinterface.IDistrictService;

@Service
public class DistrictServiceImpl implements IDistrictService{

	@Autowired
	private IDistrictRepository dR;
	
	@Override
	public List<District> list() {
		// TODO Auto-generated method stub
		return dR.findAll();
	}

	
}
