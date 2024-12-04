package com.company.demoapplication.view.model;

import com.company.demoapplication.entity.Model;
import com.company.demoapplication.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "models/:id", layout = MainView.class)
@ViewController(id = "Model.detail")
@ViewDescriptor(path = "model-detail-view.xml")
@EditedEntityContainer("modelDc")
public class ModelDetailView extends StandardDetailView<Model> {
}