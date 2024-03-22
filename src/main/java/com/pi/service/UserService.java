package com.pi.service;

import java.util.List;
import java.util.Optional;

import com.pi.entities.Role;
import com.pi.entities.ServiceDep;
import com.pi.entities.User;
import com.pi.repositories.RoleRepository;
import com.pi.repositories.ServiceDepRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.repositories.UserRepository;

@Service
@Slf4j
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	ServiceDepRepository serviceDepRepository;
	
	public List<User> selectall(){
		return userRepository.findAll();
	}
	
	public User selectOne(int id) {
		Optional<User> u=userRepository.findById(id);
		if(u.isPresent())
			return u.get();
		else
			return null;
	}

	public User addUser(User user, Long roleId, Long serviceDepId) {
		System.out.println("Role id: " + roleId);
		Role role = roleRepository.findById(roleId);
		user.setRole(role);
		ServiceDep serviceDept = serviceDepRepository.findById(serviceDepId);
		System.out.println("ServiceDep id: " + serviceDepId);
		System.out.println("ServiceDep: " + serviceDept.getId() + " " + serviceDept.getName());
		user.setServiceDep(serviceDept);
		return userRepository.save(user);
	}
	public User updateUser(Long id, User user) {
        user.setId(id);
		return userRepository.save(user);
	}


	public void deleteUser(Long id) {
		userRepository.deleteById(Math.toIntExact(id));
	}

	public User addUser(User user) {
		return userRepository.save(user);
	}

	public boolean existsByUsername(String username ) {
        return userRepository.findByUsername(username).isPresent();
    }

	public boolean existsByEmail(String email) {
       		return userRepository.findByEmail(email) != null;
    }
	public User getByEmail (String email) {
		log.info("Email: " + email);
	      if (userRepository.findByEmail(email) != null) {
	          return userRepository.findByEmail(email);
	      } else {
	          return null;
	      }
	}
}


