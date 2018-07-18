package de.perdian.apps.qifgenerator.fxnew.modules.transactions;

import de.perdian.apps.qifgenerator.fxnew.model.TransactionGroup;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;

class TransactionGroupTab extends Tab {

    TransactionGroupTab(TransactionGroup transactionGroup, ObservableList<TransactionGroup> transactionGroups) {
        this.textProperty().bind(transactionGroup.titleProperty());
        this.setOnCloseRequest(event -> {
            if (transactionGroups.size() > 1) {
                Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Delete transaction group");
                confirmationAlert.setHeaderText("Delete transaction group");
                confirmationAlert.setContentText("Really delete the transaction group?");
                if (confirmationAlert.showAndWait().get().equals(ButtonType.OK)) {
                    transactionGroups.remove(transactionGroup);
                } else {
                    event.consume();
                }
            } else {
                event.consume();
            }
        });
        this.setContent(new TransactionGroupPane(transactionGroup));
    }

}