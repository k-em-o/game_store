package com.game_store.screens;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game_store.components.GameCardController;
import com.game_store.models.Game;
import com.game_store.services.ApiClient;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class BrowseController implements Initializable {

    /* ================= FXML ================= */

    @FXML private StackPane contentArea;
    @FXML private FlowPane gameContainer;
    @FXML private HBox paginationBox;

    @FXML private ComboBox<String> categoryFilter;
    @FXML private ComboBox<String> priceFilter;
    @FXML private TextField searchField;

    /* ================= Data ================= */

    private final ObjectMapper mapper = new ObjectMapper();

    private List<Game> allGames = new ArrayList<>();
    private List<Game> filteredGames = new ArrayList<>();

    private int currentPage = 0;
    private static final int PAGE_SIZE = 20;

    /* ================= Init ================= */

    
    @FXML
    public void initialize() {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        NavbarController navbar = NavbarController.getInstance();
        if (navbar != null) {
            navbar.setContentArea(contentArea);
        }

        setupFilters();
        loadGamesFromApi();
        applyFilters();
    }

    /* ================= Filters ================= */

    private void setupFilters() {
        categoryFilter.getItems().addAll("All", "Action", "RPG", "Indie", "Adventure");
        priceFilter.getItems().addAll("All", "Free", "Paid");

        categoryFilter.setValue("All");
        priceFilter.setValue("All");

        categoryFilter.setOnAction(e -> applyFilters());
        priceFilter.setOnAction(e -> applyFilters());

        searchField.textProperty().addListener((a, b, c) -> applyFilters());
    }

    private void applyFilters() {
        filteredGames = allGames.stream()
            .filter(g ->
                categoryFilter.getValue().equals("All")
                || g.getCategoryId().equalsIgnoreCase(categoryFilter.getValue())
            )
            .filter(g ->
                priceFilter.getValue().equals("All")
                || (priceFilter.getValue().equals("Free") && g.getPrice() == 0)
                || (priceFilter.getValue().equals("Paid") && g.getPrice() > 0)
            )
            .filter(g ->
                g.getTitle().toLowerCase()
                 .contains(searchField.getText().toLowerCase())
            )
            .collect(Collectors.toList());

        currentPage = 0;
        renderPage();
        renderPagination();
    }

    /* ================= API ================= */

    private void loadGamesFromApi() {
        try {
            String json = ApiClient.getGames();
            if (json == null || json.equals("[]")) return;

            Game[] games = mapper.readValue(json, Game[].class);
            allGames = Arrays.asList(games);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* ================= Render ================= */

    private void renderPage() {
        gameContainer.getChildren().clear();

        int start = currentPage * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, filteredGames.size());

        for (int i = start; i < end; i++) {
            Game game = filteredGames.get(i);

            try {
                FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/game_store/components/GameCard.fxml")
                );

                AnchorPane card = loader.load();
                card.setPrefSize(325, 517);   // ✅ أبعاد الكارد

                GameCardController controller = loader.getController();
                controller.setData(
                    game.getId(),
                    game.getTitle(),
                    game.getPrice(),        // 0 هتتعالج Free في الكارد
                    game.getDiscount(),
                    game.getCoverImage()
                );

                gameContainer.getChildren().add(card);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* ================= Pagination ================= */

    private void renderPagination() {
        paginationBox.getChildren().clear();

        int pages = (int) Math.ceil((double) filteredGames.size() / PAGE_SIZE);

        for (int i = 0; i < pages; i++) {
            int index = i;
            Button btn = new Button(String.valueOf(i + 1));

            btn.getStyleClass().add(
                i == currentPage ? "pagination-btn-active" : "pagination-btn"
            );

            btn.setOnAction(e -> {
                currentPage = index;
                renderPage();
                renderPagination();
            });

            paginationBox.getChildren().add(btn);
        }
    }
}
