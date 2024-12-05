package com.company.demoapplication.security;

import com.company.demoapplication.entity.*;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "SalesmanRole", code = SalesmanRole.CODE, scope = "UI")
public interface SalesmanRole {
    String CODE = "salesman-role";


    @MenuPolicy(menuIds = {"Model.list", "Manufacturer.list", "Storage.list", "Vehicle.list"})
    @ViewPolicy(viewIds = {"Model.list", "Manufacturer.list", "Storage.list", "Vehicle.list", "Manufacturer.detail", "Model.detail", "Storage.detail", "Vehicle.detail"})
    void screens();

    @EntityAttributePolicy(entityClass = Manufacturer.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Manufacturer.class, actions = EntityPolicyAction.ALL)
    void manufacturer();

    @EntityAttributePolicy(entityClass = Model.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Model.class, actions = EntityPolicyAction.ALL)
    void model();

    @EntityAttributePolicy(entityClass = Storage.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Storage.class, actions = EntityPolicyAction.READ)
    void storage();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    void user();

    @EntityAttributePolicy(entityClass = Vehicle.class, attributes = "storage", action = EntityAttributePolicyAction.VIEW)
    @EntityAttributePolicy(entityClass = Vehicle.class, attributes = {"id", "onSale", "model", "movedDate", "buyingPrice", "sellingPrice", "manufacturingYear", "mileage"}, action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Vehicle.class, actions = EntityPolicyAction.ALL)
    void vehicle();
}