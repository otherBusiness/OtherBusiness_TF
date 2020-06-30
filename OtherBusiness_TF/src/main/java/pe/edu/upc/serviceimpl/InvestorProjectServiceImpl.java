package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.InvestorProject;
import pe.edu.upc.repository.IInvestorProjectRepository;
import pe.edu.upc.serviceinterface.IInvestorProjectService;

@Service
public class InvestorProjectServiceImpl implements IInvestorProjectService{
	
	@Autowired
	private IInvestorProjectRepository ipR;

	@Override
	public boolean insert(InvestorProject investorproject) {
		InvestorProject ip = ipR.save(investorproject);
		if (ip == null) {
			return false;
		}else {
			return true;
		}
		
	}

	@Override
	public InvestorProject listarId(Long idInvestorProjects) {
		Optional<InvestorProject> op = ipR.findById(idInvestorProjects);
		return op.isPresent() ? op.get() : new InvestorProject();
	}

	@Override
	public List<InvestorProject> listar() {
		
		return ipR.findAll();
	}

	@Override
	public Optional<InvestorProject> fetchByInvestorProjectsIdWhithInvesmentsDetailsWithProject(Long id) {
		return ipR.fetchByInvestorProjectsIdWhithInvesmentsDetailsWithProject(id);
	}

}
