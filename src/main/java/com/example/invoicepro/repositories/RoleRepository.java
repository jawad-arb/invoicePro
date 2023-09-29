package com.example.invoicepro.repositories;

import com.example.invoicepro.model.Role;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RoleRepository <T extends Role> {
    /* Basic CRUD Operations  */
    T create(T data);
    Collection<T> list(int page , int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

    /*  more complexe Operations */

    void addRoleToUser(Long userId,String roleName);
    Role getRoleByUserId(Long userId);
    // email is unique we can use it as a PK
    Role getRoleByEmail(String email);
    void updateUserRole(Long userId,String roleName);














}
