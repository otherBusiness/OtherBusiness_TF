package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Country;
import pe.edu.upc.repository.ICountryRepository;
import pe.edu.upc.serviceinterface.ICountryService;

@Service
public class CountryServiceImpl implements ICountryService{

	@Autowired
	private ICountryRepository cR;

	@Override
	public List<Country> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}
	
	
}
