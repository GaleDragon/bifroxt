package bifrost.main;

import bifrost.database.db.DatabaseManager;
import bifrost.database.db.objects.KeyStore;
import bifrost.helpers.objects.LocalFile;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller{

    @FXML
    private TabPane main_pane;

    @FXML
    private GridPane root;

    @FXML
    private ComboBox key;

    @FXML
    private Button identities;

    @FXML
    private Tab remote;

    @FXML
    private Accordion remote_browser;

    @FXML
    private Tab local;

    @FXML
    private TableView<LocalFile> local_table;

    @FXML
    private Tab progress;

    @FXML
    private TableView progress_table;

    @FXML
    private Tab finished;

    @FXML
    private TableView finished_table;

    @FXML
    private Button add_remove;

    @FXML
    private TableColumn<LocalFile, String> filename;

    @FXML
    private TableColumn<LocalFile, String> path;

    @FXML
    private TableColumn<LocalFile, String> last_modified;

    @FXML
    private TableColumn<ChoiceBox<String>, String> destination;

    @FXML
    private void initialize(){
        setupKeyChooser();

        filename.setEditable(false);
        path.setEditable(false);
        last_modified.setEditable(false);
    }

    public void setupKeyChooser() {
        key.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean old_val, Boolean new_val) {
                if (!new_val){
                    prompt();
                }
            }
        });
        DatabaseManager manager = null;
        ObservableList<KeyStore> keyStores = null;
        try {
            manager = new DatabaseManager();
            if (manager != null) {
                keyStores = manager.getAll();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        key.setItems(keyStores);
        key.setValue(keyStores.get(0));

        key.setEditable(true);
    }

    @FXML
    public void showFileChooser(){
        FileChooser chooser = new FileChooser();
        List<File> fileList = chooser.showOpenMultipleDialog( main_pane.getScene().getWindow() );
        List<LocalFile> localFileList = LocalFile.make(fileList);
        ObservableList<LocalFile> observableList = FXCollections.observableList(localFileList);

        filename.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LocalFile, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LocalFile, String> localFileStringCellDataFeatures) {
                return localFileStringCellDataFeatures.getValue().getFilenameProperty();
            }
        });

        path.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LocalFile, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LocalFile, String> localFileStringCellDataFeatures) {
                return localFileStringCellDataFeatures.getValue().getPathProperty();
            }
        });

        last_modified.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LocalFile, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LocalFile, String> localFileStringCellDataFeatures) {
                StringProperty time = new SimpleStringProperty();
                Date last = new Date();
                last.setTime(localFileStringCellDataFeatures.getValue().getLast_modified());
                time.setValue( String.valueOf( last.toString() ) );
                return time;
            }
        });




        local_table.setItems(observableList);
    }

    @FXML
    protected void start(){
        KeyStore selected = (KeyStore) key.getValue();
        System.out.println( selected.getKey() );
    }

    public void prompt() {
        System.out.println("prompted");
    }
}
