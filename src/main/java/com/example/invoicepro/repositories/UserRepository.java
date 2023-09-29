package com.example.invoicepro.repositories;

import com.example.invoicepro.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository<T extends User> {
     /* Basic CRUD Operations  */
    T create(T data);
    Collection<T> list(int page , int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

    /*  more complexe Operations */














}
