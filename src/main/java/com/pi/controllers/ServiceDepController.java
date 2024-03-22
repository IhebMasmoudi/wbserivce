package com.pi.controllers;


import com.pi.entities.ServiceDep;
import com.pi.service.ServiceDepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@Slf4j
@CrossOrigin("*")
public class ServiceDepController {

    @Autowired
    private ServiceDepService serviceDepService;
    @CrossOrigin(origins = "http://localhost:4200")
    @QueryMapping
    public List<ServiceDep> getAllServiceDep(){
        return serviceDepService.selectAll();

    }
    @QueryMapping
    public ServiceDep getServiceDep(@Argument Long id){
        return serviceDepService.selectOne(id);

    }

    @MutationMapping
    public ServiceDep createServiceDep(@Argument ServiceDep servicedep) {
        return serviceDepService.createServiceDep(servicedep);
    }


    @MutationMapping
    public ServiceDep updateServiceDep(@Argument Long id, @Argument ServiceDep servicedep) {
      return  serviceDepService.updateServiceDep(id, servicedep);

    }

    @MutationMapping
    public ServiceDep deleteServiceDep(@Argument  Long id) {
        serviceDepService.deleteServiceDep(id);
        return null; // Since deleteRole returns void, return null here

    }

}
