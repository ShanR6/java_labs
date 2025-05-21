module javafxplanner {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;

    // Экспортируем главный пакет
    exports javafxplanner;

    // Разрешаем рефлексивный доступ для JavaFX
    opens javafxplanner to javafx.graphics, javafx.fxml;
}