package com.company.demoapplication.security;

import com.company.demoapplication.entity.Manufacturer;
import com.company.demoapplication.entity.Model;
import com.company.demoapplication.entity.Storage;
import com.company.demoapplication.entity.Vehicle;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "TransporterRole", code = TransporterRole.CODE, scope = "UI")
public interface TransporterRole {
    String CODE = "transporter-role";

    @MenuPolicy(menuIds = {"Storage.list", "Vehicle.list"})
    @ViewPolicy(viewIds = {"Storage.list", "Vehicle.list", "Storage.detail", "Vehicle.detail"})
    void screens();

    @EntityAttributePolicy(entityClass = Model.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Model.class, actions = EntityPolicyAction.READ)
    void model();

    @EntityAttributePolicy(entityClass = Manufacturer.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Manufacturer.class, actions = EntityPolicyAction.READ)
    void manufacturer();

    @EntityAttributePolicy(entityClass = Storage.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Storage.class, actions = EntityPolicyAction.ALL)
    void storage();

    @EntityAttributePolicy(entityClass = Vehicle.class, attributes = "storage", action = EntityAttributePolicyAction.MODIFY)
    @EntityAttributePolicy(entityClass = Vehicle.class, attributes = {"id", "model", "movedDate", "manufacturingYear", "mileage"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Vehicle.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    void vehicle();
}