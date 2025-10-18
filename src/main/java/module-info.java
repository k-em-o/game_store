module com.game_store {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires java.net.http; // ✅ أضف هذا للـ HTTP Client
    requires com.fasterxml.jackson.databind; // ✅ أضف هذا للـ JSON
    

    opens com.game_store to javafx.fxml;
    opens com.game_store.screens to javafx.fxml;
    opens com.game_store.models to javafx.fxml;
    opens com.game_store.services to javafx.fxml; // ✅ أضف هذا


    exports com.game_store;
    exports com.game_store.screens;
    exports com.game_store.models;
    exports com.game_store.services; // ✅ أضف هذا


}
