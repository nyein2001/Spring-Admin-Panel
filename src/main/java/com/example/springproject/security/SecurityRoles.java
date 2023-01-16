package com.example.springproject.security;

public interface SecurityRoles {
    String ROLES_PREFIX = "ROLE_";
    String ROLE_ADMIN = "SUPER_ADMIN";
    String CUSTOMER_ADMIN = "CUSTOMER_ADMIN";
    String CUSTOMER_READ = "CUSTOMER_READ";
    String CUSTOMER_CREATE = "CUSTOMER_CREATE";
    String CUSTOMER_DELETE = "CUSTOMER_DELETE";
    String CUSTOMER_PAGE_VIEW = "CUSTOMER_PAGE_VIEW";
}
