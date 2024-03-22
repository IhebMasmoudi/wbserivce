package com.pi.service;
import java.util.List;
import java.util.Optional;

import com.pi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pi.entities.Role;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> selectAll() {
        return roleRepository.findAll();
    }
    public Role selectOne(Long id) {
        Optional<Role> u=roleRepository.findById(Math.toIntExact(id));
        if(u.isPresent())
            return u.get();
        else
            return null;
    }
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role role) {
        Optional<Role> optionalRole = roleRepository.findById(Math.toIntExact(id));
        if (optionalRole.isPresent()) {
            Role roleToUpdate = optionalRole.get();
            roleToUpdate.setName(role.getName()); // Mettre à jour le nom du rôle
            roleToUpdate.setDescription(role.getDescription()); // Mettre à jour la description du rôle
            return roleRepository.save(roleToUpdate); // Enregistrer les modifications dans la base de données
        } else {
            // Gérer le cas où aucun rôle correspondant à l'ID n'est trouvé
            throw new IllegalArgumentException("Role not found with ID: " + id);
        }
    }


    public void deleteRole(Long id) {
        // Find the role by id
        Optional<Role> roleOptional = roleRepository.findById(Math.toIntExact(id));

        // Check if the role exists
        if (roleOptional.isPresent()) {
            Role roleToDelete = roleOptional.get();

            // Delete the role
            roleRepository.delete(roleToDelete);
        } else {
            throw new IllegalArgumentException("Role not found with id: " + id);
        }
    }

}
