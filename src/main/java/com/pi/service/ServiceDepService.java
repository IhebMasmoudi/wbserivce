package com.pi.service;


import com.pi.entities.ServiceDep;
import com.pi.repositories.ServiceDepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceDepService {

    @Autowired
    private ServiceDepRepository serviceDepRepository;



    public List<ServiceDep> selectAll() {
        return serviceDepRepository.findAll();
    }
    public ServiceDep selectOne(Long id) {
        Optional<com.pi.entities.ServiceDep> u= serviceDepRepository.findById(Math.toIntExact(id));
        if(u.isPresent())
            return u.get();
        else
            return null;
    }
    public ServiceDep createServiceDep(ServiceDep servicedep) {

        return this.serviceDepRepository.save(servicedep);
    }

    public ServiceDep updateServiceDep(Long id, ServiceDep serviceDep) {
        Optional<ServiceDep> optionalService = serviceDepRepository.findById(Math.toIntExact(id));
        if (optionalService.isPresent()) {
            ServiceDep serviceToUpdate = optionalService.get();
            serviceToUpdate.setName(serviceDep.getName());
            serviceToUpdate.setDescription(serviceDep.getDescription());
            return serviceDepRepository.save(serviceToUpdate);
        } else {
            // Gérer le cas où aucun rôle correspondant à l'ID n'est trouvé
            throw new IllegalArgumentException("Service not found with ID: " + id);
        }
    }

    public void deleteServiceDep(Long id) {
        // Find the role by id
        Optional<ServiceDep> serviceOptional = serviceDepRepository.findById(Math.toIntExact(id));

        // Check if the service exists
        if (serviceOptional.isPresent()) {
            ServiceDep serviceToDelete = serviceOptional.get();

            // Delete the service
            serviceDepRepository.delete(serviceToDelete);
        } else {
            throw new IllegalArgumentException("Service not found with id: " + id);
        }
    }

}
