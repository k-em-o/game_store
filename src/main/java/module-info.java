module com.game_store {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.game_store to javafx.fxml;
    opens com.game_store.screens to javafx.fxml;

    exports com.game_store;
    exports com.game_store.screens;

}
