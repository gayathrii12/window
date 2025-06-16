package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.form.ActionBar;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableWindow;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableView;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.model.ModelData;
import com.google.gwt.user.client.Window;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class AppConfigButtonHandlers {
    private final TableWindow window;
    private final TableView tableView;
    private final AppConfigForm form;
    private final ActionBar actionBar;

    public AppConfigButtonHandlers(TableWindow window, AppConfigForm form, ActionBar actionBar) {
        this.window = window;
        this.tableView = window.getTableView();
        this.form = form;
        this.actionBar = actionBar;
        initializeHandlers();
    }

    private void initializeHandlers() {
        // Submit Button Handler
        actionBar.getSubmitButton().addSelectHandler(new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                handleSubmit();
            }
        });

        // Delete Button Handler
        actionBar.getDeleteButton().addSelectHandler(new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                handleDelete();
            }
        });

        // Clear Button Handler
        actionBar.getClearButton().addSelectHandler(new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                handleClear();
            }
        });

        // New Entry Button Handler
        actionBar.getNewButton().addSelectHandler(new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                handleNewEntry();
            }
        });
    }

    private void handleSubmit() {
        if (!form.isValid()) {
            Window.alert("Please fill in all required fields correctly.");
            return;
        }

        ModelData<String> selectedModel = tableView.getSelectionModel().getSelectedItem();
        if (selectedModel == null) {
            // Create new record
            form.getFormFieldsConfig().setDefaults();
            tableView.add(form.getValues());
        } else {
            // Update existing record
            form.getFormFieldsConfig().setUpdateDefaults();
            tableView.update(selectedModel, form.getValues());
        }
        
        form.reset();
        tableView.refresh();
    }

    private void handleDelete() {
        ModelData<String> selectedModel = tableView.getSelectionModel().getSelectedItem();
        if (selectedModel == null) {
            Window.alert("Please select a record to delete.");
            return;
        }

        if (Window.confirm("Are you sure you want to delete this record?")) {
            form.getFormFieldsConfig().setDeleteDefaultValues();
            tableView.update(selectedModel, form.getValues());
            tableView.refresh();
        }
    }

    private void handleClear() {
        form.reset();
        tableView.getSelectionModel().deselectAll();
    }

    private void handleNewEntry() {
        form.reset();
        tableView.getSelectionModel().deselectAll();
        form.getFormFieldsConfig().setDefaults();
    }
} 