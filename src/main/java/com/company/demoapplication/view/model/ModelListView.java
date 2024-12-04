package com.company.demoapplication.view.model;

import com.company.demoapplication.entity.Model;
import com.company.demoapplication.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "models", layout = MainView.class)
@ViewController(id = "Model.list")
@ViewDescriptor(path = "model-list-view.xml")
@LookupComponent("modelsDataGrid")
@DialogMode(width = "64em")
public class ModelListView extends StandardListView<Model> {
}