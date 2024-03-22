package com.pi.controllers;

import org.springframework.stereotype.Controller;
import com.pi.entities.Role;
import com.pi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@CrossOrigin("*")
public class RoleController {




    @Autowired
    private RoleService roleService;
    @QueryMapping
    public List<Role> getAllRoles(){
        return roleService.selectAll();
    }
    @QueryMapping
    public Role getRole(@Argument Long id){
        return roleService.selectOne(id);
    }
    @MutationMapping
    public Role createRole(@Argument Role role) {
        return roleService.createRole(role);
    }
    @MutationMapping
    public Role updateRole(@Argument Long id, @Argument Role role) {
        return roleService.updateRole(id, role);
    }
    @MutationMapping
    public Role deleteRole(@Argument Long id) {
        roleService.deleteRole(id);
        return null; // Since deleteRole returns void, return null here
    }

}
