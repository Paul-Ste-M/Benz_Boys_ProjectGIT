package com.calmly;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.model.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class SongEditorController implements Initializable {

    @FXML
    private Button ChordMajor;

    @FXML
    private Button ChordMinor;

    @FXML
    private Button Duration_Eighth;

    @FXML
    private Button Duration_Half;

    @FXML
    private Button Duration_Quarter;

    @FXML
    private Button Duration_Whole;

    @FXML
    private HBox HorizontalBox;

    @FXML
    private Button Pitch_C;

    @FXML
    private Button Pitch_D;

    @FXML
    private Button Pitch_E;

    @FXML
    private Button Pitch_G;

    @FXML
    private Button Pitch_REST;

    @FXML
    private Button StringFive;

    @FXML
    private Button StringFour;

    @FXML
    private Button StringOne;

    @FXML
    private HBox stringOptionsBar;

    @FXML
    private Button StringSix;

    @FXML
    private Button StringThree;

    @FXML
    private Button StringTwo;

    @FXML
    private Button addButton;

    @FXML
    private MenuItem addChordButton;

    @FXML
    private MenuItem addMeasureButton;

    @FXML
    private ContextMenu addMenu;
    
    @FXML
    private MenuItem addNoteButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label currentProjectText;

    @FXML
    private HBox durationOptionsBar;

    @FXML
    private GridPane gridPane;

    @FXML
    private HBox majorMinorOptionsBar;

    @FXML
    private Rectangle measureBar;

    @FXML
    private Label measureText;
    
    @FXML
    private Button nextMeasure;
    
    @FXML
    private HBox pitchOptionsBar;

    @FXML
    private Polygon playButton;

    @FXML
    private Button prevMeasure;

    @FXML
    private Button printButton;

    @FXML
    private Label promptingText;

    @FXML
    private Button publishButton;

    @FXML
    private Button saveAndGoHomeButton;

    private SongApp songApp;
    private Author author;
    private Song selectedSong;
    private String selectedPitch;
    private String selectedDuration;
    private boolean isSingleNote;
    private boolean isMinor;
    private String octave;
    private String fretNumber;
    private int tabsLine;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        songApp = SongApp.getInstance();
        author = songApp.getAuthor();
        selectedSong = songApp.getSelectedSong();
        currentProjectText.setText(songApp.getSelectedSong().getTitle());
        measureText.setText("Measure " + (selectedSong.getMeasures().indexOf(selectedSong.getMeasures().get(0)) + 1));
        songApp.selectMeasure(0);

        // Rectangle bar = new Rectangle(300, 100, 100, 10);
        // bar.setFill(Color.WHITE);
        // Circle circle = new Circle(350, 100, 30, Color.WHITE);
        // Label letter = new Label("E");
        // letter.setTextFill(Color.WHITE);
        // anchorPane.getChildren().add(letter);
        // letter.setLayoutX(350);
        // letter.setFont(Font.font("Arial", 32));
        // letter.setLayoutY(100);
        // anchorPane.getChildren().add(bar);
        // anchorPane.getChildren().add(circle);
    }

    private void loadMeasure() {
        for(Chord chord : author.getSelectedMeasure().getChords()) {
            this.selectedDuration = chord.getType().toString();
            this.selectedPitch = chord.getLeadingNote().getPitch().toString();
            this.isSingleNote = chord.isSingleNote();
            this.isMinor = chord.isMinor();
            this.octave = chord.getLeadingNote().getOctave();
            this.fretNumber = chord.getLeadingNote().getFretNumber();
            this.tabsLine = chord.getLeadingNote().getTabsLine();
            loadChord();
        }
    }

    private void createChord() {
        Chord chord;
        chord = new Chord(selectedDuration, selectedPitch, isSingleNote, isMinor, octave, fretNumber, tabsLine);
        songApp.addChord(selectedDuration, selectedPitch, isSingleNote, isMinor, octave, fretNumber, tabsLine);
    

        try {
            // Create an AnchorPane for the new chord
            AnchorPane chordAnchor = new AnchorPane();

            // Create the 6 bars (representing guitar strings)
            for (int i = 0; i < 6; i++) {
                Rectangle bar = new Rectangle(100, 10);
                bar.setFill(Color.WHITE);
                // Position bars vertically (separate by 46px)
                bar.setLayoutY(i * 46);  // Each bar (string) is spaced 46px apart
                chordAnchor.getChildren().add(bar);
            }

            // Add the AnchorPane to the HorizontalBox (or another container)
            HorizontalBox.getChildren().add(chordAnchor);

            // Now add the circles and labels for the notes
            for (int i = 0; i < 6; i++) {
                // Get the corresponding bar (line) for each string
                Rectangle bar = (Rectangle) chordAnchor.getChildren().get(i);
                
                // Create and position the circle on top of each bar (string)
                Circle circle = new Circle(15, Color.WHITE);
                circle.setCenterX(bar.getX() + 50);  // Position circle horizontally centered on the bar
                circle.setCenterY(bar.getLayoutY() + 5);  // Position circle vertically

                // Add circle to the AnchorPane
                chordAnchor.getChildren().add(circle);

                // Create and position the label for the fret number
                
                Label number = new Label();
                boolean noteExistsOnLine = false;
                for(Note note : chord.getNotes()) {
                    if(note.getTabsLine() == i+1) {
                        noteExistsOnLine = true;
                        number.setText(note.getFretNumber());
                    }
                }

                if(!noteExistsOnLine || number.getText().equals("-")) {
                    //number.setVisible(false);
                    circle.setVisible(false);
                } else {
                    number.setFont(Font.font("Arial", 16));
                    number.setLayoutX(bar.getX() + 42);  // Adjust for centering the label
                    number.setLayoutY(bar.getLayoutY() - 5);  // Position label just above the bar

                    // Add label to the AnchorPane
                    chordAnchor.getChildren().add(number);
                }
            }
            Button removeButton = new Button("X");
            removeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    author.removeChord(HorizontalBox.getChildren().indexOf(chordAnchor));
                    HorizontalBox.getChildren().remove(HorizontalBox.getChildren().indexOf(chordAnchor));
                }
            });
            chordAnchor.getChildren().add(removeButton);
            //removeButton.setLayoutY(chordAnchor.getLayoutY() + chordAnchor.getHeight());
            removeButton.setLayoutY(5 * 46 + 20); // Appears below the last string
            //removeButton.setLayoutX(40); // Optional: center or align as you like

        } catch(Exception e) {
            e.printStackTrace();
        }

        addButton.setVisible(true);
    }

    private void loadChord() {
        Chord chord;
        chord = new Chord(selectedDuration, selectedPitch, isSingleNote, isMinor, octave, fretNumber, tabsLine);

        try {
            // Create an AnchorPane for the new chord
            AnchorPane chordAnchor = new AnchorPane();

            // Create the 6 bars (representing guitar strings)
            for (int i = 0; i < 6; i++) {
                Rectangle bar = new Rectangle(100, 10);
                bar.setFill(Color.WHITE);
                // Position bars vertically (separate by 46px)
                bar.setLayoutY(i * 46);  // Each bar (string) is spaced 46px apart
                chordAnchor.getChildren().add(bar);
            }

            // Add the AnchorPane to the HorizontalBox (or another container)
            HorizontalBox.getChildren().add(chordAnchor);

            // Now add the circles and labels for the notes
            for (int i = 0; i < 6; i++) {
                // Get the corresponding bar (line) for each string
                Rectangle bar = (Rectangle) chordAnchor.getChildren().get(i);
                
                // Create and position the circle on top of each bar (string)
                Circle circle = new Circle(15, Color.WHITE);
                circle.setCenterX(bar.getX() + 50);  // Position circle horizontally centered on the bar
                circle.setCenterY(bar.getLayoutY() + 5);  // Position circle vertically

                // Add circle to the AnchorPane
                chordAnchor.getChildren().add(circle);

                // Create and position the label for the fret number
                
                Label number = new Label();
                boolean noteExistsOnLine = false;
                for(Note note : chord.getNotes()) {
                    if(note.getTabsLine() == i+1) {
                        noteExistsOnLine = true;
                        number.setText(note.getFretNumber());
                    }
                }

                if(!noteExistsOnLine || number.getText().equals("-")) {
                    //number.setVisible(false);
                    circle.setVisible(false);
                } else {
                    number.setFont(Font.font("Arial", 16));
                    number.setLayoutX(bar.getX() + 42);  // Adjust for centering the label
                    number.setLayoutY(bar.getLayoutY() - 5);  // Position label just above the bar

                    // Add label to the AnchorPane
                    chordAnchor.getChildren().add(number);
                }
            }
            Button removeButton = new Button("X");
            removeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    author.removeChord(HorizontalBox.getChildren().indexOf(chordAnchor));
                    HorizontalBox.getChildren().remove(HorizontalBox.getChildren().indexOf(chordAnchor));
                }
            });
            chordAnchor.getChildren().add(removeButton);
            //removeButton.setLayoutY(chordAnchor.getLayoutY() + chordAnchor.getHeight());
            removeButton.setLayoutY(5 * 46 + 20); // Appears below the last string
            removeButton.setLayoutX(40); // Optional: center or align as you like

        } catch(Exception e) {
            e.printStackTrace();
        }

        addButton.setVisible(true);
    }

    @FXML
    private void clickedAddChord(ActionEvent event) {
        this.isSingleNote = false;
        addButton.setVisible(false);
        promptingText.setVisible(true);
        promptingText.setText("Which Pitch?");
        pitchOptionsBar.setVisible(true);
    }

    @FXML
    private void clickedAddMeasure(ActionEvent event) {
        //Rectangle endBar = new Rectangle(measureBar.getWidth(), measureBar.getHeight(), measureBar.getFill());
        //HorizontalBox.getChildren().add(endBar);
        songApp.addMeasure(new Measure());
        songApp.selectMeasure(selectedSong.getMeasures().size() - 1);
        measureText.setText("Measure " + selectedSong.getMeasures().size());
        HorizontalBox.getChildren().clear();
        
        if(nextMeasure.isVisible()) {
            nextMeasure.setVisible(false);
        }
        if(!prevMeasure.isVisible()) {
            prevMeasure.setVisible(true);
        }

    }

    @FXML
   private  void clickedAddNote(ActionEvent event) {
        this.isSingleNote = true;
        addButton.setVisible(false);
        promptingText.setVisible(true);
        promptingText.setText("Which String?");
        stringOptionsBar.setVisible(true);
    }

    @FXML
    private void clickedChordMajor(ActionEvent event) {
        this.isMinor = false;
        majorMinorOptionsBar.setVisible(false);
        promptingText.setText("What Duration?");
        durationOptionsBar.setVisible(true);
    }

    @FXML
    private void clickedChordMinor(ActionEvent event) {
        this.isMinor = true;
        majorMinorOptionsBar.setVisible(false);
        promptingText.setText("What Duration?");
        durationOptionsBar.setVisible(true);
    }

    @FXML
    private void clickedPitchC(ActionEvent event) {
        this.selectedPitch = "C";

        if(isSingleNote) {
            this.isMinor = false;
            switch (tabsLine) {
                case 1: {
                    this.octave = "6";
                    this.fretNumber = "8";
                    break;
                }
                case 2: {
                    this.octave = "5";
                    this.fretNumber = "1";
                    break;
                }
                case 3: {
                    this.octave = "5";
                    this.fretNumber = "5";
                    break;
                }
                case 4: {
                    this.octave = "5";
                    this.fretNumber = "10";
                    break; 
                }

                case 5: {
                    this.octave = "4";
                    this.fretNumber = "3";
                    break;
                }

                case 6: {
                    this.octave = "4";
                    this.fretNumber = "8";
                    break;
                }
            }
            pitchOptionsBar.setVisible(false);
            promptingText.setText("What Duration?");
            durationOptionsBar.setVisible(true);

        } else {
            this.octave = "4";  // Pre-built chord information
            this.fretNumber = "3";
            this.tabsLine = 5;
            pitchOptionsBar.setVisible(false);
            promptingText.setText("Major or Minor Chord?");
            majorMinorOptionsBar.setVisible(true);
        }
    }

    @FXML
    private void clickedPitchD(ActionEvent event) {

    }

    @FXML
    private void clickedPitchE(ActionEvent event) {

    }

    @FXML
    private void clickedDurationEighth(ActionEvent event) {

    }

    @FXML
    private void clickedPitchG(ActionEvent event) {

    }

    @FXML
    private void clickedDurationHalf(ActionEvent event) {

    }

    @FXML
    private void clickedDurationQuarter(ActionEvent event) {
        this.selectedDuration = "QUARTER";
        promptingText.setVisible(false);
        durationOptionsBar.setVisible(false);
        createChord();
    }

    @FXML
    private void clickedPitchREST(ActionEvent event) {

    }

    @FXML
    private void clickedDurationWhole(ActionEvent event) {

    }

    @FXML
    void clickedNextMeasure(ActionEvent event) {
        int currentIndex = selectedSong.getMeasures().indexOf(author.getSelectedMeasure());
        songApp.selectMeasure(++currentIndex);
        measureText.setText("Measure " + (currentIndex + 1));

        if(selectedSong.getMeasures().indexOf(author.getSelectedMeasure()) == selectedSong.getMeasures().size() - 1) {
            nextMeasure.setVisible(false);
        }

        if(currentIndex != 0) {
            prevMeasure.setVisible(true);
        }
        HorizontalBox.getChildren().clear();
        loadMeasure();
    }

    @FXML
    void clickedPlayButton(MouseEvent event) {
        songApp.playSong();
        //showSongChordTabDialog(selectedSong);
    }

//TODO
    @FXML
    private void clickedPlusButton(ActionEvent event) {
        addMenu.show(addButton, Side.BOTTOM, addButton.getWidth(), 0);
        //Make sure to remove certain options when criteria is met
    }

    @FXML
    void clickedPrevMeasure(ActionEvent event) {
        int currentIndex = selectedSong.getMeasures().indexOf(author.getSelectedMeasure());
        songApp.selectMeasure(--currentIndex);
        measureText.setText("Measure " + (currentIndex + 1));

        if(selectedSong.getMeasures().indexOf(author.getSelectedMeasure()) == 0) {
            prevMeasure.setVisible(false);
        }
        
        if(currentIndex + 1 != selectedSong.getMeasures().size()) {
            nextMeasure.setVisible(true);
        }

        HorizontalBox.getChildren().clear();
        loadMeasure();

    }

    @FXML
    void clickedPrintButton(ActionEvent event) {
        songApp.exportSong();
    }

    @FXML
    void clickedPublishProject(ActionEvent event) {
        songApp.publishSong(selectedSong);
    }

    @FXML
    void clickedSaveandGoHome(ActionEvent event) {
           try {
            App.setRoot("home");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void clickedStringFive(ActionEvent event) {

    }

    @FXML
    void clickedStringFour(ActionEvent event) {

    }

    @FXML
    void clickedStringOne(ActionEvent event) {

    }

    @FXML
    void clickedStringSix(ActionEvent event) {
        this.tabsLine = 6;
        stringOptionsBar.setVisible(false);
        promptingText.setText("What Pitch?");
        pitchOptionsBar.setVisible(true);
    }

    @FXML
    void clickedStringThree(ActionEvent event) {

    }

    @FXML
    void clickedStringTwo(ActionEvent event) {

    }

    @FXML
    private void showAddOptions(ActionEvent event) {
        addMenu.show(addButton, addButton.getLayoutX(), addButton.getLayoutY());
    }



}
