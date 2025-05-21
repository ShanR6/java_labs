package javafxplanner;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EntryEditor {
    private final PlannerDashboard dashboard;
    private JournalEntry currentEntry;
    private final EntryRepository repository = new EntryRepository();

    public EntryEditor(PlannerDashboard dashboard, JournalEntry entry) {
        this.dashboard = dashboard;
        this.currentEntry = entry;
    }

    public EntryEditor(PlannerDashboard dashboard) {
        this(dashboard, null);
    }

    public void display(Stage stage) {
        stage.setTitle(currentEntry == null ? "Новая запись" : "Редактирование");

        VBox editorLayout = new VBox(10);
        editorLayout.setPadding(new Insets(15));

        TextField titleField = new TextField();
        titleField.setPromptText("Заголовок записи");

        TextArea contentArea = new TextArea();
        contentArea.setPromptText("Содержание записи");
        contentArea.setPrefRowCount(12);

        if (currentEntry != null) {
            titleField.setText(currentEntry.getTitle());
            contentArea.setText(currentEntry.getContent());
        }

        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(e -> {
            if (titleField.getText().trim().isEmpty()) {
                showError("Нельзя сохранить запись без заголовка");
                return;
            }

            JournalEntry entryToSave = currentEntry != null ? currentEntry : new JournalEntry(
                    0, 0, "", "", ""
            );
            entryToSave.setTitle(titleField.getText().trim());
            entryToSave.setContent(contentArea.getText().trim());

            repository.saveEntry(entryToSave);
            dashboard.notifyDataChanged();
            stage.close();
        });

        editorLayout.getChildren().addAll(
                new Label("Заголовок:"), titleField,
                new Label("Содержание:"), contentArea,
                saveButton
        );

        stage.setScene(new Scene(editorLayout, 400, 450));
        stage.show();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}