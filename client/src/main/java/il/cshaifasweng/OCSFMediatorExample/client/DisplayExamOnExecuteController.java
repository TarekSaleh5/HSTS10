package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.mapping.Array;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DisplayExamOnExecuteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signoutbtn;

    @FXML
    private Button backbtn;

    @FXML
    private ListView<String> listViewid;

    @FXML
    private Button addtimebtn;

    @FXML
    private TextField timevaluetxt;
    
    @FXML
    private Button submitbtn;
    
    @FXML
    private Label noexamisselctedlabel;

    @FXML
    private Label invalidtimelabel;

    
    static Object [] examonexecutationinfoarray = new Object[5];

    static List<Object[]> examOnExecutationInfo = new ArrayList<Object[]>();
    

    public DisplayExamOnExecuteController(List<Object[]> allExamsOnExecute, String whatiam) {
    	
    	this.examOnExecutationInfo = allExamsOnExecute;
    	
			        	
	}
    
    public DisplayExamOnExecuteController() {
	}

	@FXML
    void addtimeac(ActionEvent event) {
		
    	
    	timevaluetxt.setVisible(true);
    	submitbtn.setVisible(true);


    }
 
	@FXML
	void submitac(ActionEvent event) throws IOException {

		String selectedString = "";
    	ObservableList<String> exam;
    	exam = listViewid.getSelectionModel().getSelectedItems();
    	for (String mString : exam) 
    	{
    		selectedString += mString;
    	}
    	if(selectedString.equalsIgnoreCase(""))
    	{
        	noexamisselctedlabel.setVisible(true);
        	invalidtimelabel.setVisible(false);
    	}

    	else if(!timevaluetxt.getText().matches("[0-9]+"))
    	{
        	noexamisselctedlabel.setVisible(false);
        	invalidtimelabel.setVisible(true);
    	}
    	else {
    	String [] examonExecuteinfo = new String[3];

    	String chosen = selectedString.substring(10, 16);
    	String timevalue = timevaluetxt.getText();
    	
    	examonExecuteinfo[0] = chosen;
    	examonExecuteinfo[1] = timevalue;
    	App.getInstance().addTimeforExamTeacher(examonExecuteinfo);
    	}
	    }
	 
    @FXML
    void backac(ActionEvent event) {
    	String [] msg =null;
		try {
			App.getInstance().showTeacherView(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void signoutac(ActionEvent event) throws IOException {
    	App.getInstance().LogOut();
    	App.getInstance().showBackToPrimaryView();
    }
    
    
 
    @FXML
    void initialize() {
    	
    	noexamisselctedlabel.setVisible(false);
    	invalidtimelabel.setVisible(false);
    	submitbtn.setVisible(false);
    	timevaluetxt.setVisible(false);
    	ObservableList<String> itemStrings;
		listViewid.setOrientation(Orientation.VERTICAL);
		
        assert signoutbtn != null : "fx:id=\"signoutbtn\" was not injected: check your FXML file 'displayexamonexecutaion.fxml'.";
        assert backbtn != null : "fx:id=\"backbtn\" was not injected: check your FXML file 'displayexamonexecutaion.fxml'.";
        assert listViewid != null : "fx:id=\"listViewid\" was not injected: check your FXML file 'displayexamonexecutaion.fxml'.";
        assert addtimebtn != null : "fx:id=\"addtimebtn\" was not injected: check your FXML file 'displayexamonexecutaion.fxml'.";
        assert timevaluetxt != null : "fx:id=\"timevaluetxt\" was not injected: check your FXML file 'displayexamonexecutaion.fxml'.";
        assert submitbtn != null : "fx:id=\"submitbtn\" was not injected: check your FXML file 'displayexamonexecutaion.fxml'.";
        assert noexamisselctedlabel != null : "fx:id=\"noexamisselctedlabel\" was not injected: check your FXML file 'displayexamonexecutaion.fxml'.";
        assert invalidtimelabel != null : "fx:id=\"invalidtimelabel\" was not injected: check your FXML file 'displayexamonexecutaion.fxml'.";


        for (int i=0;i<examOnExecutationInfo.size();i++)
		  { 
        	
	
        	System.out.println(i);
        	  examonexecutationinfoarray = examOnExecutationInfo.get(i);
			  
			  int examid = (int) examonexecutationinfoarray[0];
			  String examidString = Integer.toString(examid);
			  
			
			  
			  Object obj = examonexecutationinfoarray[1];
			  String str = obj.toString();
			  double d = Double.valueOf(str).doubleValue();
			  double duration = d;
			  
			  String durationString = Double.toString(duration);
			  
			//  String coursename = (String) examonexecutationinfoarray[3];			  
			  
			  int executaion = (int) examonexecutationinfoarray[2];
			  String executaionString;
			  if (executaion == 1)
			  {
				  executaionString = "Computerized";
			  }
			  
			  else {
				  executaionString ="Manual";
	
			}	  
		


		    	
			 			  																					//+"Course : " + coursename + "\n" 																	
			  String fullInfoString = "Exam ID : " + examidString + "\n" + "Duration : " + durationString +"\n" + "Executaion Type : " + executaionString;
			  System.out.println(fullInfoString);
			  listViewid.getItems().addAll(fullInfoString);

		  }
    }
}