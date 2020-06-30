package pe.edu.upc.serviceinterface;

import pe.edu.upc.entity.InvesmentDetails;

public interface IInvesmentDetailService {

	public Integer insert(InvesmentDetails invd);

	public void delete(Long idInvesmentDetails);
	
}
