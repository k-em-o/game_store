module com.game_store {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires java.net.http; 
    requires com.fasterxml.jackson.databind; 
    requires jbcrypt;

    opens com.game_store to javafx.fxml;
    opens com.game_store.screens to javafx.fxml;
    opens com.game_store.models to javafx.fxml, com.fasterxml.jackson.databind; // 🔹 صححت
    opens com.game_store.services to javafx.fxml; 
    opens com.game_store.components to javafx.fxml;

    exports com.game_store;
    exports com.game_store.screens;
    exports com.game_store.models;
    exports com.game_store.services; 
    exports com.game_store.components; 
}
