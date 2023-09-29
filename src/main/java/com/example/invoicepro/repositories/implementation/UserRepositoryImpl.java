package com.example.invoicepro.repositories.implementation;

import com.example.invoicepro.exceptions.ApiException;
import com.example.invoicepro.model.Role;
import com.example.invoicepro.model.User;
import com.example.invoicepro.repositories.RoleRepository;
import com.example.invoicepro.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.example.invoicepro.Queries.UserQuery.*;
import static com.example.invoicepro.enumerations.RoleType.ROLE_USER;
import static com.example.invoicepro.enumerations.VerificationType.ACCOUNT;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl<T extends User> implements UserRepository<T> {

    private final NamedParameterJdbcTemplate jdbc;
    private final RoleRepository<Role> roleRepository;
    private final BCryptPasswordEncoder encoder;



    @Override
    public T create(T user) {
        // check if the email is unique
        if(getEmailCount(user.getEmail().trim().toLowerCase())>0) throw new ApiException("Email already in use , please use a different email and try again");
        // save new user
        try{
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(user);
            jdbc.update(INSERT_USER_QUERY,parameters,holder);
            user.setId(Objects.requireNonNull(holder.getKey()).longValue());
            // add role to the user  // default as ROLE_USER
            roleRepository.addRoleToUser(user.getId(),ROLE_USER.name());
            // send verification URL
            String verificationUrl=getVerificationUrl(UUID.randomUUID().toString(), ACCOUNT.getType());
            // save Url in verification table
            jdbc.update(INSERT_ACCOUNT_VERIFICATION_URL_QUERY,Map.of("userId",user.getId(),"url",verificationUrl));
            // send email to user with verification URL
            // return newly created user
            // if any errors , throw exceptions with proper message

        }
        catch (EmptyResultDataAccessException emptyResultDataAccessExceptionxception){}
        catch (Exception exception){}






        return null;
    }




    @Override
    public Collection<T> list(int page, int pageSize) {
        return null;
    }

    @Override
    public T get(Long id) {
        return null;
    }

    @Override
    public T update(T data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }


    /* create al the methods */

    private Integer getEmailCount(String email) {
        return jdbc.queryForObject(COUNT_USER_EMAIL_QUERY, Map.of("email",email),Integer.class);
    }

    private SqlParameterSource getSqlParameterSource(T user) {
        // we Add firstName lastName Email & password because they can not be empty
        return new MapSqlParameterSource()
                .addValue("firstName",user.getFirstName())
                .addValue("lastName",user.getLastName())
                .addValue("email",user.getEmail())
                .addValue("password",encoder.encode(user.getPassword()));
    }

    private String getVerificationUrl(String key,String type ){
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify/"+type+"/"+key).toUriString();
    }

}
