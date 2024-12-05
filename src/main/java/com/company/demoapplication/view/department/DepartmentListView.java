package com.company.demoapplication.view.department;

import com.company.demoapplication.entity.Department;
import com.company.demoapplication.view.main.MainView;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.jmix.flowui.view.*;


@AnonymousAllowed
@Route(value = "departments", layout = MainView.class)
@ViewController(id = "Department.list")
@ViewDescriptor(path = "department-list-view.xml")
@LookupComponent("departmentsDataGrid")
@DialogMode(width = "64em")
public class DepartmentListView extends StandardListView<Department> {
}