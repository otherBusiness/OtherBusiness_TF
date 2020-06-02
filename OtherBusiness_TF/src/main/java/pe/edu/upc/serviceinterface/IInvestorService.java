package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Investor;

public interface IInvestorService {
	public int insert(Investor investor);
	
	List<Investor> list();
}
