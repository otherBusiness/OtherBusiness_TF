package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Investor;

public interface IInvestorService {
	public int insert(Investor investor);
	
	List<Investor> list();
	
	public void delete(int idInvestor);
	
	Optional<Investor> searchId(int idInvestor);
	
	List<Investor> findRucInvestor(int rucInvestor);
}
