package com.company.demoapplication.view.manufacturer;

import com.company.demoapplication.entity.Manufacturer;
import com.company.demoapplication.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "manufacturers/:id", layout = MainView.class)
@ViewController(id = "Manufacturer.detail")
@ViewDescriptor(path = "manufacturer-detail-view.xml")
@EditedEntityContainer("manufacturerDc")
public class ManufacturerDetailView extends StandardDetailView<Manufacturer> {
}