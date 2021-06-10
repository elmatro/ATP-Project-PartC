package View;


import ViewModel.MyViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import static View.OptionsWindowController.isMusic;


public class MyViewController implements IView, Initializable, Observer {

    public MyViewModel myViewModel;

    public static MediaPlayer mediaPlayer;
    public static boolean playing;

    public Button Start_button;
    public Button Button_Exit;
    public ImageView mainImageView;
    public AnchorPane mainPane;

    StringProperty updatePlayerRow = new SimpleStringProperty();
    StringProperty updatePlayerCol = new SimpleStringProperty();

    public static void music() {
        if (isMusic()){
            playing = true;
            Media mediaMusic = new Media(Paths.get("./resources/music/HeroesGrassTheme.mp3").toUri().toString());
            mediaPlayer = new MediaPlayer(mediaMusic);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
            mediaPlayer.setVolume(0.3);
        }

    }

    public static void mouseAudio(){
        if (isMusic()){
            Media mouseClicked = new Media((Paths.get("./resources/music/Click.mp3").toUri().toString()));
            MediaPlayer mediaPlayer2 = new MediaPlayer(mouseClicked);
            mediaPlayer2.setCycleCount(1);
            mediaPlayer2.play();
            mediaPlayer2.setVolume(0.3);
        }
    }


    public void setMyViewModel(MyViewModel myViewModel) {
        this.myViewModel = myViewModel;
        this.myViewModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
    }



    public void setUpdatePlayerRow(int updatePlayerRow) {
        this.updatePlayerRow.set(updatePlayerRow + "");
    }

    public void setUpdatePlayerCol(int updatePlayerCol) {
        this.updatePlayerCol.set(updatePlayerCol + "");
    }


    public void buttonStart(ActionEvent event) throws IOException {
        mouseAudio();
        Parent root2 = FXMLLoader.load(getClass().getResource("MiddleScene.fxml"));
        Start_button.getScene().setRoot(root2);
    }

    public void buttonLoad(ActionEvent event) throws IOException {
        mouseAudio();
        Parent root2 = FXMLLoader.load(getClass().getResource("LoadWindow.fxml"));
        Start_button.getScene().setRoot(root2);
    }

    public void buttonAbout(ActionEvent event) throws IOException {
        mouseAudio();
        Parent root2 = FXMLLoader.load(getClass().getResource("AboutWindow.fxml"));
        Start_button.getScene().setRoot(root2);
    }

    public void buttonOptions(ActionEvent event) throws IOException {
        mouseAudio();
        Parent root2 = FXMLLoader.load(getClass().getResource("OptionsWindow.fxml"));
        Start_button.getScene().setRoot(root2);
    }

    public void buttonExit(ActionEvent event) throws IOException {
        mouseAudio();
        Stage stage = (Stage) Button_Exit.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (mediaPlayer != null) {
            if (!playing)
                music();
        }
        mainImageView.fitWidthProperty().bind(mainPane.widthProperty());
        mainImageView.fitHeightProperty().bind(mainPane.heightProperty());

    }
}
