package com.vysotski.funpay.entity;

public enum RoleType {
    USER(2), ADMIN(1);

    private long roleId;

    RoleType(long roleId){
        this.roleId = roleId;
    }

    public long getRoleId() {
        return roleId;
    }

    public static RoleType takeRole(long roleId) {
        return roleId == 2 ? USER : ADMIN;
    }
}
