package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for the randomization settings window
 *
 */
public class SettingController {
    @FXML
    public TextField randomMin;
    @FXML
    public TextField randomMax;
    @FXML
    public TextField randomSize;
    private boolean isInt(String s) {
        return s.matches("[-+]?\\d+");
    }

    /**
     * Pop up an error alert box for input error
     */
    private void alertError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Inputs are illegal. Please check your inputs.");
        alert.showAndWait();
    }

    private boolean validateInputs() {
        if (!this.isInt(randomMax.getText())) {
            return false;
        }
        if (!this.isInt(randomMin.getText())) {
            return false;
        }
        if (!this.isInt(randomSize.getText()) || Integer.parseInt(randomSize.getText()) < 0) {
            return false;
        }
        return true;
    }

    @FXML
    public void initialize() {
        this.randomMin.setText(Integer.toString(Controller.getRandomDataMin()));
        this.randomMax.setText(Integer.toString(Controller.getRandomDataMax()));
        this.randomSize.setText(Integer.toString(Controller.getRandomDataSize()));
    }

    @FXML
    public void onRandomCancelClick(Event e) {
        Stage stage = (Stage) ((Button) (e.getSource())).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onRandomSaveClick(Event e) {
        Stage stage = (Stage) ((Button) (e.getSource())).getScene().getWindow();
        if (validateInputs()) {
            int size = Integer.parseInt(this.randomSize.getText());
            int min = Integer.parseInt(this.randomMin.getText());
            int max = Integer.parseInt(this.randomMax.getText());
            Controller.setRandomDataMin(min);
            Controller.setRandomDataMax(max);
            Controller.setRandomDataSize(size);
            stage.close();
        } else {
            alertError();
        }
    }
}
