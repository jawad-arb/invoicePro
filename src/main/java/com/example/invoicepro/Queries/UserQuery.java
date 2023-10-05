package com.example.invoicepro.Queries;

public class UserQuery {
    public static final String INSERT_USER_QUERY = "INSERT INTO users (first_name,last_name,email,password) VALUES(:firstName, :lastName, :email , :password)";
    public static final String COUNT_USER_EMAIL_QUERY = "SELECT COUNT(*) from users WHERE email= :email ";
    public static final String INSERT_ACCOUNT_VERIFICATION_URL_QUERY = "INSERT INTO AccountVerifications(user_id,url) VALUES(:userId , :url)";

    public static final String COUNT_USER_ID_QUERY = "SELECT COUNT(*) from users WHERE id= :id ";
    public static final String SELECT_USER_ID_QUERY = "SELECT first_name,last_name,email from users WHERE id= :id";
}
