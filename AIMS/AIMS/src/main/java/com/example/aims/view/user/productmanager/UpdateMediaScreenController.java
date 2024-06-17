package com.example.aims.view.user.productmanager;

import com.example.aims.controller.CRUDMediaController;
import com.example.aims.controller.CRUDUserController;
import com.example.aims.entity.media.Media;
import com.example.aims.entity.delivery.DeliveryInfo;
import com.example.aims.exception.user.login.EmptyInputException;
import com.example.aims.utils.ImagePath;
import com.example.aims.utils.PathConfig;
import com.example.aims.utils.Utils;
import com.example.aims.view.BaseScreenController;
import com.example.aims.view.media.BookScreenController;
import com.example.aims.view.media.CDScreenController;
import com.example.aims.view.media.DVDScreenController;
import com.example.aims.view.media.HomeScreenController;
import com.example.aims.view.user.admin.ResetPasswordController;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateMediaScreenController extends BaseScreenController {
    @FXML
    private TableColumn<Media, List<Button>> action;

    @FXML
    private TableColumn<Media, ImageView> mediaImage;

    @FXML
    private TableColumn<Media, String> title;

    @FXML
    private TableColumn<Media, Integer> id;

    @FXML
    private TableColumn<Media, String> category;

    @FXML
    private TableColumn<Media, Integer> quantity;

    @FXML
    private TableColumn<Media, Integer> price;

    @FXML
    private TableColumn<Media, String> rushOrderAvailable;

    @FXML
    private TableColumn<Media, Double> weight;

    @FXML
    private TableView<Media> mediasTable;
    @FXML
    private TextField searchBox;
    private CRUDMediaController crudMediaController;

    private int userId;

    private ObservableList<Media> mediaObservableList = FXCollections.observableArrayList();

    public UpdateMediaScreenController() {
    }

    public <T> UpdateMediaScreenController(Scene currentScene, String currentScreenPath, T... data) {
        super(currentScene, currentScreenPath, data);
    }

    @Override
    public <T> void initData(T... data) {
        this.userId = (Integer) data[0];

        crudMediaController = new CRUDMediaController();

        List<Media> medias = crudMediaController.getAllMedia();

        mediaObservableList.addAll(medias);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        mediaImage.setCellValueFactory(cellData -> {
            Media media = cellData.getValue();
            Image image = null;
            try {
                image = ImagePath.getMediaImage(media);
            } catch (Exception e) {
                System.out.println(media.getImageURL() + "error");
            }
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(173);
            imageView.setPreserveRatio(true);

            return new SimpleObjectProperty<>(imageView);
        });

        title.setCellValueFactory(new PropertyValueFactory<>("title"));

        category.setCellValueFactory(new PropertyValueFactory<>("category"));

        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        rushOrderAvailable.setCellValueFactory(cellData -> {
            Media media = cellData.getValue();
            SimpleStringProperty s;
            if (media.isRushOrderAvailable()) {
                s = new SimpleStringProperty("Yes");
            } else {
                s = new SimpleStringProperty("No");
            }
            return s;
        });

        weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        // Assuming you have a method to create action buttons for each user
        action.setCellFactory(createActionButtonCellFactory());
        // Set up the status column with a custom cell factory

        // Populate the TableView with the users list
        mediasTable.setItems(mediaObservableList);

        mediasTable.setRowFactory(tableView -> {
            TableRow<Media> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Media media = row.getItem();
                    new MediaDetailScreenController(currentScene, PathConfig.MEDIA_DETAIL, media);
                }
            });
            return row;
        });
    }

    private Callback<TableColumn<Media, List<Button>>, TableCell<Media, List<Button>>> createActionButtonCellFactory() {
        return new Callback<>() {
            @Override
            public TableCell<Media, List<Button>> call(final TableColumn<Media, List<Button>> param) {
                return new TableCell<>() {
                    private final Button updateButton = new Button("Update");
                    private final Button deleteButton = new Button("Delete");

                    {
                        updateButton.setOnAction((ActionEvent event) -> {
                            Media media = getTableView().getItems().get(getIndex());
                            new EditMediaScreenController
                                    (currentScene, PathConfig.EDIT_MEDIA_SCREEN,
                                            media, crudMediaController, UpdateMediaScreenController.this);
                        });

                        deleteButton.setOnAction((ActionEvent event) -> {
                            try {
                                Media media = getTableView().getItems().get(getIndex());
                                crudMediaController.deleteMedia(media);
                                refreshTableView();
                            } catch (RollbackException e) {
                                Utils.showAlert("Error",
                                        "Something went wrong!\nPlease try again later",
                                        Alert.AlertType.ERROR);
                            }
                        });
                    }

                    @Override
                    protected void updateItem(List<Button> item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            // Create a layout to hold all buttons
                            HBox buttons = new HBox(
                                    updateButton,
                                    deleteButton);
                            buttons.setSpacing(5);
                            setGraphic(buttons);
                        }
                    }
                };
            }
        };
    }

    @FXML
    void refresh(ActionEvent event) {
        refreshTableView();
    }

    public void refreshTableView() {
        mediaObservableList.clear();
        List<Media> Medias = crudMediaController.getAllMedia();
        mediaObservableList.addAll(Medias);
        mediasTable.setItems(mediaObservableList);
        mediasTable.refresh();
    }

    @FXML
    void logOut(ActionEvent event) {
        new HomeScreenController(currentScene, PathConfig.HOME_PATH, "", "", 0);
    }

    @FXML
    void pendingOrders(ActionEvent event) {
        new PendingOrdersScreenController(currentScene, PathConfig.PENDING_ORDERS, userId);
    }

    @FXML
    void createMedia(ActionEvent e) {
        new CreateMediaScreenController(currentScene, PathConfig.CREATE_MEDIA_SCREEN, crudMediaController);
    }

    @FXML
    void changePassword(ActionEvent event) {
        ResetPasswordController.showResetPasswordPopUp(new CRUDUserController(), userId);
    }

    @FXML
    void searchQuery(ActionEvent event) {
        try {
            List<Media> Medias = crudMediaController.searchMedia(searchBox.getText());
            mediaObservableList.clear();
            mediaObservableList.addAll(Medias);
            mediasTable.setItems(mediaObservableList);
            mediasTable.refresh();
        } catch (EmptyInputException e) {
            Utils.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
