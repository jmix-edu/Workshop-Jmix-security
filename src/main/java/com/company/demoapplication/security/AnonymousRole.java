package com.company.demoapplication.security;

import com.company.demoapplication.entity.Vehicle;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "AnonymousRole", code = AnonymousRole.CODE, scope = "UI")
public interface AnonymousRole {
    String CODE = "anonymous-role";

    @MenuPolicy(menuIds = "OnSaleView")
    @ViewPolicy(viewIds = {"OnSaleView", "MainView"})
    void screens();

    @EntityAttributePolicy(entityClass = Vehicle.class, attributes = {"id", "model", "sellingPrice", "manufacturingYear", "mileage"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Vehicle.class, actions = EntityPolicyAction.READ)
    void vehicle();
}