package com.company.demoapplication.view.vehicle;

import com.company.demoapplication.entity.Storage;
import com.company.demoapplication.entity.Vehicle;
import com.company.demoapplication.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;


@Route(value = "vehicles", layout = MainView.class)
@ViewController(id = "Vehicle.list")
@ViewDescriptor(path = "vehicle-list-view.xml")
@LookupComponent("vehiclesDataGrid")
@DialogMode(width = "64em")
public class VehicleListView extends StandardListView<Vehicle> {

    private Storage filterStorage;
    @ViewComponent
    private CollectionLoader<Vehicle> vehiclesDl;
    @ViewComponent
    private DataGrid<Vehicle> vehiclesDataGrid;

    public void setFilterStorage(Storage filterStorage) {
        this.filterStorage = filterStorage;
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        if (filterStorage != null) {
            vehiclesDl.setParameter("filterStorage", filterStorage);
            vehiclesDl.load();
            vehiclesDataGrid.getColumnByKey("storageKey").setVisible(false);
        }
    }

}