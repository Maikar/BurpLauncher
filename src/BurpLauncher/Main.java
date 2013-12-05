package BurpLauncher;

import BurpLauncher.Core.SpringFxmlLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final SpringFxmlLoader loader = new SpringFxmlLoader();

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = (Parent) loader.load("/BurpLauncher/Shell/Views/ShellView.fxml");
        primaryStage.setTitle("BurpLauncher");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
