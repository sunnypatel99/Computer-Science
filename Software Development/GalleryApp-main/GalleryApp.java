 package cs1302.gallery;

 import com.google.gson.*;
 import javafx.scene.layout.VBox;
 import javafx.scene.layout.HBox;
 import javafx.scene.Scene;
 import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;
 import javafx.scene.text.Text;
 import javafx.scene.control.MenuItem;
 import javafx.scene.control.Menu;
 import javafx.scene.control.MenuBar;
 import javafx.application.Platform;
 import javafx.scene.control.MenuBar;
 import javafx.scene.control.Button;
 import javafx.stage.Stage;
 import javafx.application.Application;
 import javafx.scene.text.Text;
 import javafx.scene.control.TextField;
 import javafx.animation.Timeline;
 import javafx.scene.control.ProgressBar;
 import javafx.scene.control.ToolBar;
 import javafx.scene.control.Label;
 import javafx.event.ActionEvent;
 import javafx.event.EventHandler;
 import javafx.scene.layout.TilePane;
 import javafx.animation.KeyFrame;
 import javafx.util.Duration;
 import java.io.InputStreamReader;
 import java.net.URL;
 import javafx.scene.control.Alert;
 import javafx.scene.control.ButtonType;
 import java.net.MalformedURLException;
 import java.io.IOException;
 import java.net.URLEncoder;
 import java.io.UnsupportedEncodingException;
 import java.nio.charset.StandardCharsets;
 import javafx.animation.Animation;

 /**
  * This class represents an iTunes GalleryApp.
  */

 public class GalleryApp extends Application {

     Scene scene;
     VBox vbox;
     HBox hbox;
     MenuBar menubar;
     Menu file;
     Menu help;
     MenuItem exit;
     MenuItem about;
     Button playpause;
     Button updateimage;
     String userinput;
     String[] words;
     double progress = 0.0;
     Timeline timeline;
     ProgressBar progressBar = new ProgressBar();
     ToolBar toolbar;
     TextField textfield;
     double playcount = -1.0;
     boolean play = true;
     JsonArray jsonResults;
     JsonArray unused;
     JsonArray used;
     ImageView[]  images = new ImageView[20];
     TilePane tpane = new TilePane();
     String[] results;
     Stage abtPanel;
     VBox infoBox;
     Text mrinfo;
     ImageView roshenPic;
     Scene infoScene;
     Label search;
     EventHandler<ActionEvent> handler;
     int ranUMember;
     int ranDMember;
     JsonObject result;
     JsonElement artworkUrl100;
     KeyFrame keyFrame;
     InputStreamReader reader;
     URL url;
     String queryString;
     JsonParser jp;
     JsonElement je;
     JsonObject root;
     String art;

     /**
      * This is the start method that runs the program.
      *
      * <p>
      * @inheritdoc
      */
     @Override

     public void start(Stage stage) {
         vbox = new VBox();
         //This adds all the vbox components
         vbox.getChildren().addAll(menuBarSetUp(),toolBarFunction());
         // This deals with freezing
         Thread task = new Thread(() -> {
             getImages(userinput);
             Platform.runLater(() -> {
                 vbox.getChildren().add(changeTPane());
             });
         });
         task.setDaemon(true);
         task.start();
         scene = new Scene(vbox, 500, 485);
         stage.setTitle("Gallery App!");
         stage.setScene(scene);
         stage.sizeToScene();
         stage.show();
     } // staart

     /**
      * This is the extra credit where I talk about myself.
      */

     public void extraCredit() {
         abtPanel = new Stage();
         infoBox = new VBox();
         mrinfo = new Text();
         //Below is my info and my picture.
         mrinfo.setText(
             " Roshen Jegajeevan \n rj65735@uga.edu \n Application Version 1.4");
         roshenPic = new ImageView("file:resources/IMG_0055.JPG");
         roshenPic.setFitWidth(350);
         roshenPic.setFitHeight(350);
         infoBox.getChildren().addAll(mrinfo, roshenPic);
         infoScene = new Scene(infoBox);
         abtPanel.setTitle("About Roshen Jegajeevan");
         abtPanel.setScene(infoScene);
         abtPanel.sizeToScene();
         abtPanel.show();
     } // extraCredit  method


      /**
      * This initalizes the components of the tool bar.
      */

     public void initalizeToolBar() {
         toolbar = new ToolBar();
         playpause = new Button("Play");
         search = new Label("Search Query:");
         textfield = new TextField("rock"); //default is set to rock.
         userinput = modifyText(textfield);
         updateimage = new Button("Update Images");
         toolbar.getItems().addAll(playpause, search, textfield, updateimage);
     } //initalizeToolBar


     /**
      * This method creates the progress bar
      *
      * @return hbox The progress bar
      */

     public HBox addPBar() {
         hbox = new HBox();
         progressBar.setLayoutX(25.0);
         progressBar.setLayoutY(550.0);
         hbox.getChildren().add(progressBar);
         return hbox;
     } //addsProgressBar

      /**
      * This method updates the images on the tile panes.
      *
      * @return tpane This is the updated tilepane.
      */

     public TilePane changeTPane() {
         int numresults = results.length;
         if (numresults < 21) {
             return tpane;
         }
         //resets the tile panes
         tpane.getChildren().clear();
         progress = 0.0;
         for (int i = 0; i < 20; i++) {
             images[i].setImage(new Image(results[i]));
             images[i].setFitWidth(100.0);
             images[i].setFitHeight(100.0);
             tpane.getChildren().add(images[i]);
         }
         for (int x = 0; x < unused.size(); x++) {
             if (used.contains(unused.get(x))) {
                 unused.remove(unused.get(x));
             }
         }
         return tpane;
     } //changeTPanes

     /**
      * This gets the string URLs for the query.
      *
      * @param i This is the number of URLs desired.
      */

     public void getResults(int i) {
         result = jsonResults.get(i).getAsJsonObject();
         used.add(result);
         artworkUrl100 = result.get("artworkUrl100");
         if (artworkUrl100 != null) {
             art = artworkUrl100.getAsString();
             Image image = new Image(art);
             //stores results
             results[i] = art;
             images[i] = new ImageView();
             images[i].setImage(new Image(results[i]));
             Platform.runLater(() -> incrProgress());
         } //if statement
     } //getResultsmethod

     /**
      * This method reads the values of the URL.
      *
      * @param value is the string to be encoded
      * @return string is the encoded string.
      */

     public String encValue(String value) {
         try {
             return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
         } catch (UnsupportedEncodingException e) {
             throw new RuntimeException(e.getMessage());
         }
     } //encodesvalue

     /**
      * This increments the progress in the progress bar.
      */

     public void incrProgress() {
         progress = progress + 0.05;
         progressBar.setProgress(progress);
     } //incrProgress

     /**
      * Helps read in the JSON query.
      *
      * @param url link with image.
      * @param queryString url link modifyed.
      * @param reader reads the results.
      */

     public void read(URL url, String queryString, InputStreamReader reader) {
         try {
             url = new URL(queryString);
         } catch (MalformedURLException e) {
             throw new RuntimeException(e.getMessage());
         }
         //error catching
         try {
             reader = new InputStreamReader(url.openStream());
         } catch (IOException e) {
             throw new RuntimeException(e.getMessage());
         }
         jp = new JsonParser();
         je = jp.parse(reader);
         root = je.getAsJsonObject();
         //results
         jsonResults = root.getAsJsonArray("results");
         unused = root.getAsJsonArray("results");
         used = new JsonArray();
         results = new String[jsonResults.size()];
     } //read

     /**
      * Lines up the timeline with the image replacement.
      *
      * @param handler timeline lambda expression
      */

     public void setUpTimeline(EventHandler<ActionEvent> handler) {
         keyFrame = new KeyFrame(Duration.seconds(2), handler);
         timeline = new Timeline();
         timeline.setCycleCount(Timeline.INDEFINITE);
         timeline.getKeyFrames().add(keyFrame);
         timeline.play();
     } //setUpTimeline

      /**
      * This method sets up the menu bar with file, theme, and about.
      *
      * @return menubar This method returns the set up menubar so I can
      * add it in the start method.
      */

     public MenuBar menuBarSetUp() {
         menubar = new MenuBar();
         //This sets up the labels for the menu bar.
         file = new Menu("File");
         help = new Menu("Help");
         exit = new MenuItem("Exit");
         about = new MenuItem("About");
         menubar.getMenus().addAll(file, help);
         file.getItems().add(exit);
         help.getItems().add(about);
         //This sets up the action for the buttons.
         exit.setOnAction(event -> System.exit(0));
         about.setOnAction(e -> {
             extraCredit();
         });
         return menubar;
     } // menuBarSetUp


     /**
      * This gets the images.
      *
      * @param userInput this is the user's input.
      */

     public void getImages(String userInput) {
         tpane.setPrefColumns(5);<
         tpane.setPrefRows(4);
         reader = null;
         url = null;
         queryString = "https://itunes.apple.com/search?term="
             + userInput;
         encValue(queryString);
         read(url, queryString, reader);
         if (results.length < 21) {
             Platform.runLater(() -> {
                 Alert alert = new Alert(Alert.AlertType.ERROR,
                                         "There are not enough results. Displaying previous.",
                                         ButtonType.OK);
                 alert.showAndWait();
                 playpause.setText("Play");
             });
             return;
         } //if
         for (int i = 0; i < 20; i++) {
             getResults(i);
         } //for
     } //getImages

     /**
      * This randomly places images.
      */

     public void playPauseImgChange() {
         handler = (e -> {
             if (jsonResults.size() > 21) {
                 if (playpause.getText() == "Play") {
                     timeline.pause();
                     return;
                 } //if
                 //process for random images
                 ranUMember = (int) (Math.random() * unused.size());
                 ranDMember = (int) (Math.random() * images.length);
                 result = unused.get(ranUMember).getAsJsonObject();
                 artworkUrl100 = result.get("artworkUrl100");
                 if (artworkUrl100 != null) {
                     randomSub(artworkUrl100, ranDMember);
                 } //if
                 if (used.contains(unused.get(ranUMember))) {
                     unused.remove(unused.get(ranUMember));
                 } //if
             } //if
         });
         setUpTimeline(handler);
     } //playPauseImgChange

     /**
      * This merges the random image with the other ImageView objects.
      *
      * @param artworkUrl100 JSon Element of new member
      * @param ranDMember location of new member
      */

     public void randomSub(JsonElement artworkUrl100, int ranDMember) {
         //takes new
         used.add(artworkUrl100);
         String artUrl = artworkUrl100.getAsString();
         Image image = new Image(artUrl);
         images[ranDMember] = new ImageView();
         //Stores random member in unused
         unused.add(jsonResults.get(ranDMember));
         images[ranDMember].setImage(image);
         images[ranDMember].setFitHeight(100.0);
         images[ranDMember].setFitWidth(100.0);
         tpane.getChildren().clear();
         for (int i = 0; i < 20; i++) {
             tpane.getChildren().add(images[i]);
         }
     } //rendomSub


     /**
      * This helps changes up the images randomly.
      */

     public void randomImage() {
         playcount++;
         if (playcount % 2 == 0.0) {
             play = true;
         } //if  statement
         if (playcount % 2 != 0.0) {
             play = false;
         } //if statement
         Platform.runLater(() -> {
             if (play) {
                 playpause.setText("Pause");
                 timeline.play();
             } //if statement
             if (!play) {
                 playpause.setText("Play");
                 timeline.pause();
                 return;
             } //if statement
         });
         if (play) {
             playPauseImgChange();
         } //if statement
     } //randomImage method

     /**
      * This method makes the users input usable.
      *
      * @param textField this is the text field where the user's input goes.
      * @return userinput This is the changed user input.
      */

     public String modifyText(TextField textField) {
         userinput = textField.getText();
         words = userinput.split(" ");
         userinput = "";
         for (int i = 0; i < words.length; i++) {
             if (i == 0) {
                 userinput = userinput + words[i];
                 continue;
             } // user input
             userinput = userinput + "+" + words[i];
         }
         progress = 0.0;
         return userinput;
     } //modifyText

     /**
      * This sets up the tool bar functionality.
      *
      * @return toolbar which is the set up toolbar.
      */

     public ToolBar toolBarFunction() {
         initalizeToolBar();
         //this is the functionality for playpause button
         playpause.setOnAction(e -> {
             randomImage();
         });
         //this is the functionality for update image button
         updateimage.setOnAction(e -> {
             updateimage.setStyle("-fx-background: #FF0000;");
             boolean running = false;
             if (timeline != null) {
                 if (timeline.getStatus() == Animation.Status.RUNNING) {
                     running = true;
                     timeline.pause();
                 } //if statement
             } //if statement
             //new input from user for Gallery App
             String newinput = modifyText(textfield);
             progressBar.setProgress(0.0);
             vbox.getChildren().addAll(addPBar());
             Thread task = new Thread(() -> {
                 getImages(newinput);
                 Platform.runLater(() -> {
                     changeTPane();
                 });
             });
             task.setDaemon(true);
             task.start();
             if (running) {
                 timeline.play();
             } //if
         });
         return toolbar;
     } //toolBarFunction

 } //GalleryApp   method

