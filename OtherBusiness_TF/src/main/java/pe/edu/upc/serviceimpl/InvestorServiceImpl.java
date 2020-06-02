package pe.edu.upc.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Investor;
import pe.edu.upc.repository.IInvestorRepository;
import pe.edu.upc.serviceinterface.IInvestorService;

@Service
public class InvestorServiceImpl implements IInvestorService{

	@Autowired
	private IInvestorRepository iR;
	
	@Transactional
	@Override
	public int insert(Investor investor) {
		// TODO Auto-generated method stub
		int rpta=iR.searchInvestor(investor.getRucInvestor());
		if(rpta==0){
			iR.save(investor);
		}
		return rpta;
	}

	@Override
	public List<Investor> list() {
		// TODO Auto-generated method stub
		return iR.findAll();
	}

}
