package javafxplanner;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        DatabaseManager.initializeDatabase();
        new PlannerDashboard().display(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}