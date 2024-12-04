package com.company.demoapplication.view.storage;

import com.company.demoapplication.entity.Storage;
import com.company.demoapplication.entity.Vehicle;
import com.company.demoapplication.view.main.MainView;
import com.company.demoapplication.view.vehicle.VehicleListView;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Route(value = "storages/:id", layout = MainView.class)
@ViewController(id = "Storage.detail")
@ViewDescriptor(path = "storage-detail-view.xml")
@EditedEntityContainer("storageDc")
public class StorageDetailView extends StandardDetailView<Storage> {

    @Autowired
    private DataManager dataManager;
    @ViewComponent
    private DataGrid<Vehicle> vehiclesDataGrid;
    @ViewComponent
    private CollectionContainer<Vehicle> vehiclesDc;
    @Autowired
    private DialogWindows dialogWindows;
    @ViewComponent
    private TypedTextField<Integer> availableSlotsField;
    @Autowired
    private Notifications notifications;

    @Subscribe("vehiclesDataGrid.add")
    public void onVehiclesDataGridAdd(final ActionPerformedEvent event) {
        Storage thisStorage = getEditedEntity();
        Storage targetStorage = getSecondStorage(thisStorage);

        dialogWindows.lookup(this, Vehicle.class)
                .withViewClass(VehicleListView.class)
                .withViewConfigurer(view -> view.setFilterStorage(targetStorage))
                .withSelectHandler(vehicles -> {
                    if (!enoughSlotsToMove(thisStorage, vehicles)) {
                        notifications.create("There are not enough available places!")
                                .withType(Notifications.Type.ERROR)
                                .show();
                    } else {
                        for (Vehicle vehicle : vehicles) {
                            vehicle.setStorage(thisStorage);
                            vehicle.setMovedDate(LocalDateTime.now());
                            vehiclesDc.getMutableItems().add(vehicle);
                            dataManager.save(vehicle);
                            updateSlotCounter();
                        }
                    }
                })
                .withLookupComponentMultiSelect(true)
                .open();


    }


    @Subscribe("vehiclesDataGrid.exclude")
    public void onVehiclesDataGridExclude(final ActionPerformedEvent event) {

        Set<Vehicle> selectedVehicles = vehiclesDataGrid.getSelectedItems();

        Storage thisStorage = getEditedEntity();
        Storage targetStorage = getSecondStorage(thisStorage);

        if (!enoughSlotsToMove(targetStorage, selectedVehicles)) {
            notifications.create("There are not enough available places!")
                    .withType(Notifications.Type.ERROR)
                    .show();
        } else {

            for (Vehicle vehicle : selectedVehicles) {

                vehiclesDc.getMutableItems().remove(vehicle);
                vehicle.setStorage(targetStorage);
                vehicle.setMovedDate(LocalDateTime.now());
                dataManager.save(vehicle);

                updateSlotCounter();
            }
        }
    }

    private boolean enoughSlotsToMove(Storage targetStorage, Collection<Vehicle> selectedVehicles) {
        return (targetStorage.getAvailableSlots() - selectedVehicles.size() >= 0);
    }

    private void updateSlotCounter() {
        availableSlotsField.setValue(String.valueOf(getEditedEntity().getNumberOfSlots() - vehiclesDc.getItems().size()));
    }

    private Storage getSecondStorage(Storage thisStorage) {
        return dataManager.load(Storage.class)
                .query("select s from Storage s where " +
                        "s.id <> :thisId")
                .parameter("thisId", thisStorage.getId())
                .one();
    }
}