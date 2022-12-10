package it.unisa.diem.dhsa.group3.HIS_Project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class PatientAdmissionController implements Initializable {

	@FXML
	private TextField LONField;

	@FXML
	private TextField LastNameField;

	@FXML
	private TextField RacePicker;

	@FXML
	private DatePicker BirthDatePicker;

	@FXML
	private TextField LATField;

	@FXML
	private TextField PrefixField;

	@FXML
	private DatePicker DeathDatePicker;

	@FXML
	private TextField CountryField;

	@FXML
	private TextField StateField;

	@FXML
	private TextField CoverageField;

	@FXML
	private TextField DriversField;

	@FXML
	private TextField MaidenField;

	@FXML
	private TextField ZIPField;

	@FXML
	private Button SubmitButton;
	
	@FXML
    private Button LoadPatient;

	@FXML
	private TextField SSNField;

	@FXML
	private TextField AddressField;

	@FXML
	private TextField IDField;

	@FXML
	private TextField FirstNameField;

	@FXML
	private TextField BirthPlaceField;

	@FXML
	private RadioButton MgenderButton;

	@FXML
	private RadioButton FgenderButton;

	@FXML
	private TextField searchPatientField;

	@FXML
	private RadioButton NOgenderButton;

	@FXML
	private TextField CityField;

	@FXML
	private TextField ExpensesField;

	@FXML
	private TextField SuffixField;

	@FXML
	private TextField EthnicityField;

	@FXML
	private TextField PassportField;

	@FXML
	private TabPane TabPane;

	@FXML
	private Label patientID;

	@FXML
	private MenuButton MaritalMenuButton;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		MgenderButton.selectedProperty().addListener((p, o, n) -> {
			if (n == true) {
				FgenderButton.setSelected(false);
				NOgenderButton.setSelected(false);
			}
		});

		FgenderButton.selectedProperty().addListener((p, o, n) -> {
			if (n == true) {
				MgenderButton.setSelected(false);
				NOgenderButton.setSelected(false);
			}
		});

		NOgenderButton.selectedProperty().addListener((p, o, n) -> {
			if (n == true) {
				FgenderButton.setSelected(false);
				MgenderButton.setSelected(false);
			}
		});

		NOgenderButton.setSelected(true);

		disableFields();
		SubmitButton.disableProperty().bind(Bindings.isEmpty(searchPatientField.textProperty())
				.and(Bindings.isEmpty(FirstNameField.textProperty())));

	}

	@FXML
	void backPressed(ActionEvent event) throws IOException {
		App.setRoot("OpeningPage");
	}

	@FXML
	void searchCode(ActionEvent event) {
		enableFields();
	}

	@FXML
	void submitPressed(ActionEvent event) throws IOException {
		if (TabPane.isDisabled())
			searchCode(event);

	}
	 
	@FXML
	 void LoadPatientPressed(ActionEvent event) {

	 }

	@FXML
	void maritalSelected(ActionEvent event) {
		MenuItem e = (MenuItem) event.getSource();
		MaritalMenuButton.setText(e.getText());
	}

	private void enableFields() {
		TabPane.setDisable(false);
		IDField.setDisable(false);
		patientID.setDisable(false);
		searchPatientField.clear();
		searchPatientField.setDisable(true);
	}

	private void disableFields() {
		searchPatientField.clear();
		TabPane.setDisable(true);
		IDField.setDisable(true);
		patientID.setDisable(true);
	}
}