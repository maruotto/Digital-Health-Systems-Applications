package it.unisa.diem.dhsa.group3.HIS_Project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public abstract class BasicController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		return;
	}
	
	 @FXML
		void SwitchToOpeningPage(ActionEvent event) throws IOException {
			App.setRoot("OpeningPage");
		}

}
