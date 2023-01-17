package com.example.springproject.security;

import com.example.springproject.security.utils.RolesHierarchyBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

import static com.example.springproject.security.SecurityRoles.*;

@Configuration
public class RoleHierarchyConfiguration {

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy(
                new RolesHierarchyBuilder()
                        .append(ROLE_ADMIN, CUSTOMER_ADMIN)
                        .append(CUSTOMER_ADMIN, CUSTOMER_CREATE)
                        .append(CUSTOMER_ADMIN, CUSTOMER_DELETE)
                        .append(CUSTOMER_ADMIN, CUSTOMER_READ)
                        .append(CUSTOMER_ADMIN, CUSTOMER_PAGE_VIEW)

                        .append(ROLE_ADMIN, EMPLOYEE_ADMIN)
                        .append(EMPLOYEE_ADMIN, EMPLOYEE_CREATE)
                        .append(EMPLOYEE_ADMIN, EMPLOYEE_DELETE)
                        .append(EMPLOYEE_ADMIN, EMPLOYEE_READ)
                        .append(EMPLOYEE_ADMIN, EMPLOYEE_PAGE_VIEW)

                        .append(ROLE_ADMIN, DEPARTMENT_ADMIN)
                        .append(DEPARTMENT_ADMIN, DEPARTMENT_CREATE)
                        .append(DEPARTMENT_ADMIN, DEPARTMENT_DELETE)
                        .append(DEPARTMENT_ADMIN, DEPARTMENT_READ)
                        .append(DEPARTMENT_ADMIN, DEPARTMENT_PAGE_VIEW)
                        .build()
        );
        return roleHierarchy;
    }
}
