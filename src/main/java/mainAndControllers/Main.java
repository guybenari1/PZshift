package mainAndControllers;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataBaseManager;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        if (DataBaseManager.getDBInstance().noUsers()) {
            try {
                String pathName = "AddNewEmployee.fxml";
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(pathName));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    String pathName = "FirstScene.fxml";
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(pathName));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            try {
                String pathName = "FirstScene.fxml";
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(pathName));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

