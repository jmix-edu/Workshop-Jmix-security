package com.company.demoapplication.view.manufacturer;

import com.company.demoapplication.entity.Manufacturer;
import com.company.demoapplication.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "manufacturers", layout = MainView.class)
@ViewController(id = "Manufacturer.list")
@ViewDescriptor(path = "manufacturer-list-view.xml")
@LookupComponent("manufacturersDataGrid")
@DialogMode(width = "64em")
public class ManufacturerListView extends StandardListView<Manufacturer> {
}