package pe.edu.upc.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.InvesmentDetails;
import pe.edu.upc.repository.IInvesmentDetailRepository;
import pe.edu.upc.serviceinterface.IInvesmentDetailService;

@Service
public class InvesmentDetailServiceImpl implements IInvesmentDetailService{
	
	@Autowired
	private IInvesmentDetailRepository ideR;

	@Override
	public Integer insert(InvesmentDetails invd) {
		InvesmentDetails invde = ideR.save(invd);
		if (invde == null) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public void delete(Long idInvesmentDetails) {
		ideR.deleteById(idInvesmentDetails);
		
	}

}
