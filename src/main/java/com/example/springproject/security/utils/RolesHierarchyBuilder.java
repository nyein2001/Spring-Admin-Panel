package com.example.springproject.security.utils;


public class RolesHierarchyBuilder {

    StringBuilder stringBuilder = new StringBuilder();

    public RolesHierarchyBuilder append(String upLineRole, String downLineRole) {

        stringBuilder.append(
                String.format("ROLE_%s > ROLE_%s\n",     //   \n must  include in new version
                        upLineRole, downLineRole)
        );
        return this;
    }


    public String build() {
        return stringBuilder.toString();
    }
}
