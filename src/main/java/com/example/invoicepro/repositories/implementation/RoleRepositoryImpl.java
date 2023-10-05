package com.example.invoicepro.repositories.implementation;

import com.example.invoicepro.exceptions.ApiException;
import com.example.invoicepro.model.Role;
import com.example.invoicepro.repositories.RoleRepository;
import com.example.invoicepro.rowmapper.RoleRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import static com.example.invoicepro.Queries.RoleQuery.*;
import static com.example.invoicepro.enumerations.RoleType.ROLE_USER;


@Repository
@RequiredArgsConstructor
@Slf4j
public class RoleRepositoryImpl implements RoleRepository<Role> {

    private final NamedParameterJdbcTemplate jdbc;
    @Override
    public Role create(Role data) {
        return null;
    }

    @Override
    public Collection<Role> list(int page, int pageSize) {
        return null;
    }

    @Override
    public Role get(Long id) {
        return null;
    }

    @Override
    public Role update(Role data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public void addRoleToUser(Long userId, String roleName) {
        log.info("Adding role {} to user id :{}",roleName,userId);
        try{
            // get the role and put it in the RoleRowMapper object by the roleName
            Role role=jdbc.queryForObject(SELECT_ROLE_BY_NAME_QUERY, Map.of("roleName",roleName),new RoleRowMapper());
            // update the UserRole by inserting userId , and RoleId
            jdbc.update(INSERT_ROLE_TO_USER_QUERY,Map.of("userId",userId , "roleId", Objects.requireNonNull(role).getId()));

        }
        catch (EmptyResultDataAccessException emptyResultDataAccessExceptionxception){
            throw new ApiException("no Role found by name :"+ROLE_USER.name());
        }
        catch (Exception exception){
            throw new ApiException("An Error occurred , please try again");
        }

    }

    @Override
    public Role getRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public Role getRoleByEmail(String email) {
        return null;
    }

    @Override
    public void updateUserRole(Long userId, String roleName) {

    }
}
