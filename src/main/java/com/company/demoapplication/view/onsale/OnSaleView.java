package com.company.demoapplication.view.onsale;


import com.company.demoapplication.entity.Vehicle;
import com.company.demoapplication.view.main.MainView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Supply;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Route(value = "on-sale-view", layout = MainView.class)
@ViewController(id = "OnSaleView")
@ViewDescriptor(path = "on-sale-view.xml")
public class OnSaleView extends StandardView {
    private static final Logger log = LoggerFactory.getLogger(OnSaleView.class);

    private static final BigDecimal DISCOUNT = BigDecimal.valueOf(0.15d);
    @Autowired
    private UiComponents uiComponents;

    @Supply(to = "vehiclesDataGrid.sellingPrice", subject = "renderer")
    private Renderer<Vehicle> vehiclesDataGridSellingPriceCalculatedRenderer() {
        return new ComponentRenderer<>(vehicle -> {
            Span component = uiComponents.create(Span.class);
            component.setText(String.valueOf(vehicle.getSellingPrice().subtract(vehicle.getSellingPrice().multiply(DISCOUNT))));
            component.setClassName("font-bold");
            return component;
        });
    }

}