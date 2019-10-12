package com.integreation.cms.entity.cms.cmsentity;

/**
 * Created by Admin on 2017/3/17.
 */
public class AuthorityByRoleNoForReceiveEntity extends AuthorityEntity {
    private String bindingRoleNo;
    private boolean hasAuthority;

    public String getBindingRoleNo() {
        return bindingRoleNo;
    }

    public void setBindingRoleNo(String bindingRoleNo) {
        this.bindingRoleNo = bindingRoleNo;
    }

    public boolean isHasAuthority() {
        return hasAuthority;
    }

    public void setHasAuthority(boolean hasAuthority) {
        this.hasAuthority = hasAuthority;
    }


}
