package javafxplanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PlannerDashboard {
    private final EntryRepository repository = new EntryRepository();
    private ObservableList<JournalEntry> entries;
    private ListView<JournalEntry> entriesListView;
    private TextField searchInput;

    public void display(Stage primaryStage) {
        primaryStage.setTitle("Digital Planner");

        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(10));

        searchInput = new TextField();
        searchInput.setPromptText("Поиск по заголовку...");
        searchInput.textProperty().addListener((obs, oldVal, newVal) -> refreshEntries());

        entries = FXCollections.observableArrayList();
        entriesListView = new ListView<>(entries);
        entriesListView.setCellFactory(param -> new ListCell<JournalEntry>() {
            @Override
            protected void updateItem(JournalEntry item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getTitle());
            }
        });

        Button addButton = createButton("Новая запись", this::openEditor);
        Button editButton = createButton("Редактировать", this::handleEdit);
        Button deleteButton = createButton("Удалить", this::handleDelete);

        HBox controlPanel = new HBox(10, addButton, editButton, deleteButton);
        controlPanel.setPadding(new Insets(10, 0, 0, 0));

        mainLayout.setTop(searchInput);
        mainLayout.setCenter(entriesListView);
        mainLayout.setBottom(controlPanel);

        primaryStage.setScene(new Scene(mainLayout, 600, 400));
        refreshEntries();
        primaryStage.show();
    }

    private Button createButton(String text, Runnable action) {
        Button btn = new Button(text);
        btn.setOnAction(e -> action.run());
        return btn;
    }

    private void refreshEntries() {
        entries.setAll(repository.searchEntries(searchInput.getText()));
    }

    private void openEditor() {
        new EntryEditor(this).display(new Stage());
    }

    private void handleEdit() {
        JournalEntry selected = entriesListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            new EntryEditor(this, selected).display(new Stage());
        }
    }

    private void handleDelete() {
        JournalEntry selected = entriesListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            repository.deleteEntry(selected.getEntryId());
            refreshEntries();
        }
    }

    void notifyDataChanged() {
        refreshEntries();
    }
}