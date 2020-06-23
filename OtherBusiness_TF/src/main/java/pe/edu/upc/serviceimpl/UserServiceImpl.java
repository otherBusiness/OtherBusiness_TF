package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Users;
import pe.edu.upc.repository.IUserRepository;
import pe.edu.upc.serviceinterface.IUserService;


@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository uR;

	@Override
	public void insert(Users users) {
		// TODO Auto-generated method stub
		uR.save(users);
	}

	@Override
	public List<Users> list() {
		// TODO Auto-generated method stub
		return uR.findAll();
	}

}
