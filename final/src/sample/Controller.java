package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The model class of Queue in MVC
 * @author Claire Tagoe, Duo Tao and Yijun Wang
 */
public class Controller {
    private final static String CONF_PATH = "dataStructureList.txt";
    private final static int RANDOM_DATA_SIZE = 10;
    private final static int RANDOM_DATA_RANGE = 20;
    @FXML
    private ComboBox selector;
    @FXML
    private Button randomButton;
    @FXML
    private TextArea dataField;
    @FXML
    private Button enterButton;
    @FXML
    private AnchorPane displayBoard;
    @FXML
    private Label serialTextBox;
    @FXML
    private VBox buttonVBox;
    @FXML
    private VBox textFieldVBox;
    @FXML
    public Label outputLabel;

    private boolean initialized = false;
    private HashMap<String, Class> nameClassMap = new HashMap<String, Class>();
    private VisualizedDataStructure visualizedDataStructure = null;

    /**
     * The method to initialize the application
     */
    public void initialize() {
        try {
            this.loadClassIntoMap(CONF_PATH);
        } catch (Exception e) {
            System.out.println("Error reading configuration file:" + CONF_PATH);
            System.exit(0);
        }
    }

    /**
     * Refresh the output (displayBoard, outputLabel and serialTextBox) with new data
     * @param outputOfOperation the text to put into the operation result text box
     */
    private void refreshOutput(String outputOfOperation) {
        List<Node> listOfNodes = this.visualizedDataStructure.getNodes();
        displayBoard.getChildren().clear();
        for (Node node : listOfNodes) {
            displayBoard.getChildren().add(node);
        }
        outputLabel.setText(outputOfOperation);
        serialTextBox.setText(this.visualizedDataStructure.serialize());
    }

    /**
     * Load the classes from config file input into the map
     * @param filePath the path to the config file
     * @throws Exception thrown when the file is not found or the file is misformatted or contains absent class
     */
    private void loadClassIntoMap(String filePath) throws Exception {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine()) != null) {
            String[] split = line.split(" ");
            Class curClass = Class.forName(this.getClass().getPackage().getName() + "." + split[1]);
            this.nameClassMap.put(split[0], curClass);
            selector.getItems().add(split[0]);
        }
        bufferedReader.close();
    }

    /**
     * Handles the selector selecting event
     * @throws Exception throws exception when contructor is not found, which should not happen
     */
    public void onDataStructureSelected() throws Exception {
        // for the first time of selection
        if (!this.initialized) {
            this.initialized = true;
            randomButton.setDisable(false);
            dataField.setDisable(false);
            enterButton.setDisable(false);
        }
        // initialize the data and status
        String selected = selector.getValue().toString();
        assert (this.nameClassMap.containsKey(selected));
        Class curClass = this.nameClassMap.get(selected);

        this.visualizedDataStructure = (VisualizedDataStructure) (curClass.getConstructor(Label.class).newInstance(this.outputLabel));

        // add the controls
        List<ViewableDataStructure.ControlWrapper> controlList = this.visualizedDataStructure.getControls();
        //added this code to clear before drawing new buttons.
        buttonVBox.getChildren().clear();
        textFieldVBox.getChildren().clear();
        for (ViewableDataStructure.ControlWrapper controlWrapper : controlList) {
            buttonVBox.getChildren().add(controlWrapper.button);
            controlWrapper.button.setOnAction(controlWrapper.handler);
            textFieldVBox.getChildren().add(controlWrapper.textField);
        }
        // clear up the outputs
        this.refreshOutput("Output");
    }

    /**
     * Initialize the data structure with random data
     */
    public void onRandomButtonClick() {
        this.visualizedDataStructure.randomize(RANDOM_DATA_SIZE, RANDOM_DATA_RANGE);
        this.refreshOutput("");
    }

    /**
     * Handles the initialize button click event
     * Reads in the input in the textarea and use the result to initialize or reinitialize the data
     * If the input is illegal, pops up a message and does nothing
     */
    public void onEnterButtonClick() {
        String inputString = dataField.getText();
        VisualizedDataStructure result = this.visualizedDataStructure.deserialize(inputString);
        if (result == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("The input can not be deserialized");
            alert.setContentText(inputString);
            alert.showAndWait();
            return;
        }
        this.visualizedDataStructure = result;
        this.refreshOutput("");
    }
}