package com.example.flexitrack3fx;

import com.example.flexitrack3fx.data.Config;
import com.example.flexitrack3fx.data.Users;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Register class.
 * This class contains unit tests for adding, removing, initializing, and saving users in the Register application.
 */
public class RegisterTest {
    private Register registerController;
    private ListView<Users> registerListView;
    private TextField nameField;
    private TextField emailField;
    private PasswordField passwordField;

    /**
     * Initializes the JavaFX toolkit before running any tests.
     * This method ensures that the JavaFX platform is started before executing tests that interact with JavaFX components.
     *
     * @throws InterruptedException if the initialization is interrupted
     */
    @BeforeAll
    public static void initToolkit() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        if (!latch.await(5, TimeUnit.SECONDS)) {
            throw new IllegalStateException("JavaFX toolkit did not initialize");
        }
    }

    /**
     * Sets up the test environment before each test.
     * This method initializes the Register controller and its associated UI components.
     *
     * @throws InterruptedException if the setup is interrupted
     */
    @BeforeEach
    void setUp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            registerController = new Register();
            registerListView = new ListView<>(FXCollections.observableArrayList(Config.getInstance().getUsers()));
            nameField = new TextField();
            emailField = new TextField();
            passwordField = new PasswordField();

            // Inject the fields into the controller
            registerController.registerListView = registerListView;
            registerController.namefield = nameField;
            registerController.emailfield = emailField;
            registerController.passwordField = passwordField;

            latch.countDown();
        });
        if (!latch.await(5, TimeUnit.SECONDS)) {
            throw new IllegalStateException("JavaFX initialization did not complete");
        }
    }

    /**
     * Tests the addition of a new user.
     * This method simulates user input and verifies that a new user is correctly added to the ListView and the configuration.
     *
     * @throws InterruptedException if the test is interrupted
     */
    @Test
    void onAddClick() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            // Simulate user input
            nameField.setText("TestUser");
            emailField.setText("test@user.com");
            passwordField.setText("password");

            // Simulate click on the "Add" button
            registerController.onAddClick(new ActionEvent());

            // Verify that the user has been added to the list
            Users addedUser = registerListView.getItems().stream()
                    .filter(user -> user.getEmail().equals("test@user.com"))
                    .findFirst()
                    .orElse(null);

            assertNotNull(addedUser, "User should be added to the list");
            assertEquals("TestUser", addedUser.getName());
            assertEquals("test@user.com", addedUser.getEmail());
            assertEquals("password", addedUser.getPassword());

            latch.countDown();
        });
        if (!latch.await(5, TimeUnit.SECONDS)) {
            throw new IllegalStateException("Test did not complete");
        }
    }

    /**
     * Tests the removal of an existing user.
     * This method adds a user to the ListView, simulates selecting and removing the user, and verifies the removal.
     *
     * @throws InterruptedException if the test is interrupted
     */
    @Test
    void onRemoveClick() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            // Add a user to remove
            Users user = new Users("ToRemove", "remove@user.com", "password");
            registerListView.getItems().add(user);

            // Select the user in the ListView
            registerListView.getSelectionModel().select(user);

            // Simulate click on the "Remove" button
            registerController.onRemoveClick(new ActionEvent());

            // Verify that the user has been removed from the list
            Users removedUser = registerListView.getItems().stream()
                    .filter(u -> u.getEmail().equals("remove@user.com"))
                    .findFirst()
                    .orElse(null);

            assertNull(removedUser, "User should be removed from the list");

            latch.countDown();
        });
        if (!latch.await(5, TimeUnit.SECONDS)) {
            throw new IllegalStateException("Test did not complete");
        }
    }

    /**
     * Tests the initialization of the Register controller.
     * This method verifies that the ListView is initialized with users from the configuration file.
     *
     * @throws InterruptedException if the test is interrupted
     */
    @Test
    void initialize() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            // Initialize the controller
            registerController.initialize(null, null);

            // Verify that the ListView is initialized with users from Config
            assertEquals(Config.getInstance().getUsers().size(), registerListView.getItems().size(), "ListView should be initialized with users from Config");

            latch.countDown();
        });
        if (!latch.await(5, TimeUnit.SECONDS)) {
            throw new IllegalStateException("Test did not complete");
        }
    }

    /**
     * Tests updating an existing user's information.
     * This method modifies the user details, simulates saving the changes, and verifies the updates in the ListView and configuration.
     *
     * @throws InterruptedException if the test is interrupted
     */
    @Test
    void onSaveClick() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            // Add a user to update
            Users user = new Users("ToSave", "save@user.com", "password");
            registerListView.getItems().add(user);

            // Select the user in the ListView
            registerListView.getSelectionModel().select(user);

            // Modify user details
            nameField.setText("UpdatedName");
            emailField.setText("updated@user.com");
            passwordField.setText("newpassword");

            // Simulate click on the "Save" button
            registerController.onSaveClick(new ActionEvent());

            // Verify that the user has been updated
            Users updatedUser = registerListView.getItems().get(registerListView.getSelectionModel().getSelectedIndex());

            assertEquals("UpdatedName", updatedUser.getName(), "User name should be updated");
            assertEquals("updated@user.com", updatedUser.getEmail(), "User email should be updated");
            assertEquals("newpassword", updatedUser.getPassword(), "User password should be updated");

            // Verify that the text fields have been cleared
            assertTrue(nameField.getText().isEmpty(), "Name field should be cleared");
            assertTrue(emailField.getText().isEmpty(), "Email field should be cleared");
            assertTrue(passwordField.getText().isEmpty(), "Password field should be cleared");

            latch.countDown();
        });
        if (!latch.await(5, TimeUnit.SECONDS)) {
            throw new IllegalStateException("Test did not complete");
        }
    }
}
