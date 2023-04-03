package com.example.imageredctor;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import javax.imageio.ImageIO;


public class HelloController extends HelloApplication{
    @FXML
    private BarChart<?, ?>barGistogram;
    FileChooser fileChooser;
    @FXML
    private GridPane gp_FirstTab;

    static boolean flag = true;
    @FXML
    private Canvas canvasDraw;
    Timeline updateChart;
    @FXML
    private ImageView imgviewForWaves;
    @FXML
    private GridPane gp_SecondTab;
    @FXML
    private GridPane gp_ThirdTab;
    private TextField[][]tf;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView iv_loadingImage;
    @FXML
    private ImageView iv_loadingImage1;
    public byte [][]array;
    ThirdWave tw;
    int numberOfPoints = 9;
    public byte a4Array[][];
    public byte a4ArrayCopy[][];
    public byte a8Array[][];
    public byte extendedArray[][];
    @FXML
    private CategoryAxis gistogramLegend;
    @FXML
    private TextField countOFwaves;
    private TextField[] tfWaves;
    @FXML
    private GridPane gpWavesradius;
    Image img;
    double[][] resultOfWaves;
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();


    @FXML
    void cteateTFforRadius(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER)
        {
            int size = Integer.parseInt(countOFwaves.getText());
            gpWavesradius.setPrefWidth(200);
            gpWavesradius.setPrefHeight(100);
            gpWavesradius.setGridLinesVisible(true);
            tfWaves = new TextField[size];
            for(int i = 0; i < size;i++){
                tfWaves[i] = new TextField();
                if(i == 0){
                    tfWaves[i].setText("0.6");
                } else if (i == 1) {
                    tfWaves[i].setText("0.3");
                } else if (i== 2) {
                    tfWaves[i].setText("0.1");
                }
                tfWaves[i].setPrefHeight(25);
                tfWaves[i].setPrefWidth(80);
                tfWaves[i].setAlignment(Pos.CENTER);
                tfWaves[i].setEditable(true);
                gpWavesradius.setColumnIndex(tfWaves[i], i);
                gpWavesradius.getChildren().add(tfWaves[i]);

            }
        }
    }
    @FXML
    void initialize() {
       gistogramLegend.setAnimated(true);
       gistogramLegend.setTickLabelGap(5.5);
    }
    @FXML
    void SaveToFile(ActionEvent event) throws IOException
    {
        createTable(array,(int)(img.getWidth()),(int)(img.getHeight()),gp_FirstTab);
    }
        public void createTable(byte[][] tableArr,int length, int width ,GridPane gridPane) {
            gridPane.setGridLinesVisible(true);
            gridPane.setPrefHeight(10000);
            gridPane.setPrefWidth(15000);
            tableArr = new byte[(int)img.getWidth()][(int)img.getHeight()];
            PixelReader px = img.getPixelReader();
            tf = new TextField[(int)img.getWidth()][(int)img.getHeight()];
            for (int i = 0; i < (int)img.getHeight(); i++) {
                for (int j = 0; j < (int)img.getWidth(); j++) {
                    tf[i][j] = new TextField();
                    Color cl = px.getColor(i, j);
                     if (cl.equals(Color.WHITE)) {
                        tableArr[i][j] = 0;
                        tf[i][j].setText("0");
                    }
                    else{
                        tableArr[i][j] = 1;
                        tf[i][j].setText("1");
                        tf[i][j].setStyle("-fx-text-fill: #07aad0");
                        tf[i][j].setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                    }
                    tf[i][j].setPrefHeight(20);
                    tf[i][j].setPrefWidth(25);
                    tf[i][j].setAlignment(Pos.CENTER);
                    tf[i][j].setEditable(true);
                    gridPane.setRowIndex(tf[i][j], j);
                    gridPane.setColumnIndex(tf[i][j], i);
                    gridPane.getChildren().add(tf[i][j]);
                }
            }

        }

    public void loadFromFile(ActionEvent actionEvent) {

        WritableImage wImage = new WritableImage((int)img.getWidth(),(int) img.getHeight());

        PixelWriter writer = wImage.getPixelWriter();

        for(int y = 0; y <(int) img.getHeight(); y++) {
            for(int x = 0; x <(int) img.getWidth(); x++) {
                if( Integer.parseInt(tf[x][y].getText()) == 1){
                    writer.setColor(x, y, Color.BLACK);
                }else {
                    writer.setColor(x, y, Color.WHITE);
                }
            }
        }
        a4method();
        image1.setImage(wImage);
    }
    public void a4method(){
       // extendedArray = new byte[(int)img.getHeight()+2][(int)img.getWidth()+2];

        a4Array = new byte[(int)img.getHeight() + 2][(int)img.getWidth() + 2];
        a4ArrayCopy = new byte[(int)img.getHeight() + 2][(int)img.getWidth() + 2];
        a8Array = new byte[(int)img.getHeight() + 2][(int)img.getWidth() + 2];
        for(int i = 0;i< (int)img.getHeight()+2;i++)
        {
            for(int j = 0;j < (int)img.getWidth()+2;j++){
                /*a4Array[0][j] = 0;
                a4Array[(int)img.getHeight() + 1][j] = 0;*/
                /*extendedArray[0][j] = 0;
                extendedArray[(int)img.getHeight() + 1][j] = 0;*/
            }
           /* a4Array[i][0] = 0;
            a4Array[i][(int)img.getWidth() + 1] = 0;*/
            /*extendedArray[i][0] = 0;
            extendedArray[i][(int)img.getWidth() + 1] = 0;*/
        }
        for(int i = 1;i< (int)img.getHeight()+1;i++)
        {
            for(int j = 1;j < (int)img.getWidth()+1;j++){
                // a4Array[i][j] = array[i-1][j-1];
              //  extendedArray[i][j] = Byte.parseByte(tf[i-1][j-1].getText());
            }
        }

        for(int i = 0;i< (int)img.getHeight()+2;i++)
        {
            for(int j = 0;j < (int)img.getWidth()+2;j++) {
                a4ArrayCopy[i][j] = a4Array[i][j];
            }
        }
        for(int i = 1;i< (int)img.getHeight()+1;i++)
        {
            for(int j = 1;j < (int)img.getWidth()+1;j++) {
                  a8Array[i][j] = (byte) (a4Array[i + 1][j] + a4Array[i][j+1] + a4Array[i][j-1] + a4Array[i-1][j]
                          + a4Array[i-1][j-1] + a4Array[i-1][j+1] + a4Array[i+1][j-1] + a4Array[i+1][j+1]);
            }
        }


        for(int i = 1;i< (int)img.getHeight()+1;i++)
        {
            for(int j = 1;j < (int)img.getWidth()+1;j++) {
                a4ArrayCopy[i][j] = (byte) (a4Array[i + 1][j] + a4Array[i][j+1] + a4Array[i][j-1] + a4Array[i-1][j]);
            }
        }


    }
    @FXML
    void resultBut(ActionEvent event) {
        createTable(a4Array,(int)(img.getWidth()),(int)(img.getHeight()),gp_SecondTab);
    }

    @FXML
    void a8but(ActionEvent event) {
        createTable(a8Array,(int)(img.getWidth()),(int)(img.getHeight()),gp_ThirdTab);
    }

    @FXML
    void savingfileBmpbtn(ActionEvent event) {
        fileChooser = new FileChooser();
        File selectedFile = fileChooser.showSaveDialog(null);

       // savingBMP();
        try{
            BufferedImage imgf = new BufferedImage((int)img.getWidth(), (int)img.getHeight(), BufferedImage.TYPE_INT_RGB );

            File f = new File( selectedFile.getAbsolutePath());

            for(int x = 0; x < (int)img.getWidth(); x++){
                for(int y = 0; y < (int)img.getHeight(); y++){
                    if(Integer.parseInt(tf[x][y].getText()) == 0) {
                        imgf.setRGB(x, y, 16777215);
                    }
                    else{
                        imgf.setRGB(x, y, 0);
                    }
                }
            }
            ImageIO.write(imgf, "BMP", f);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    void loadingButton(ActionEvent event) {
        fileChooser = new FileChooser();
        File loadedFile = fileChooser.showOpenDialog(null);
        img = new Image(loadedFile.getAbsolutePath());
        iv_loadingImage.setImage(img);
        iv_loadingImage1.setImage(img);

    }
    @FXML
    void ThirdWaveButtonActon(ActionEvent event) {
       extendedArrayForWaves(Integer.parseInt(countOFwaves.getText()));
       tw = new ThirdWave();
       double[] radiusArr = new double[Integer.parseInt(countOFwaves.getText())];
       for(int i = 0; i < Integer.parseInt(countOFwaves.getText());i++ ){
           radiusArr[i] = Double.parseDouble(tfWaves[i].getText());
       }
       //resultOfWaves = new double[(int)img.getWidth()][(int)img.getHeight()];
       resultOfWaves = tw.CalculateWaves(extendedArray,(int)img.getWidth(),(int)img.getHeight(),Integer.parseInt(countOFwaves.getText()),radiusArr);
        HashMap<Double,Integer> hs = tw.getGistogram();
        SortByValue sv = new SortByValue(hs);
        System.out.println("Sorting values in ascending order:");


        Iterator iterator = sv.sortByValue(true).entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
                createGistogram((double)entry.getKey(),(int)entry.getValue());
            }
        gistogramLegend.setAnimated(false);
        writeToImageWave();
    }
    EventHandler<ActionEvent> chartUpdater = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Random random = new Random();
            //add a new point to the chart
            XYChart.Series ds = new XYChart.Series();
            ds.getData().add(
                    new XYChart.Data( (random.nextDouble() * 1e8)+"",numberOfPoints++)
            );
            barGistogram.getData().add(ds);
            //remove the first point, because that's the left-most.
            barGistogram.getData().remove(ds);
        }
    };


    public void extendedArrayForWaves(int countOfWave){
        extendedArray = new byte[(int)(img.getHeight() + (countOfWave * 2))][(int)(img.getWidth() + (countOfWave*2))];
        for(int i = 0; i < img.getHeight() +(countOfWave * 2);i++){
            for(int j = 0; j < img.getWidth() + (countOfWave * 2);j++) {
                extendedArray[i][j] = 0;
            }
        }
        for(int i = countOfWave; i < img.getHeight() + countOfWave;i++){
            for(int j = countOfWave; j < img.getWidth() + countOfWave;j++){
                extendedArray[i][j] = Byte.parseByte(tf[j-countOfWave][i-countOfWave].getText());
            }
        }
        for(int i = 0;i < img.getHeight() +(countOfWave * 2);i++){
            for(int j = 0; j < img.getWidth() + (countOfWave * 2); j++ ){
                System.out.print(extendedArray[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void createGistogram(double key,int value){

        XYChart.Series ds = new XYChart.Series();
        ds.getData().add(new XYChart.Data(key+"",value));
        barGistogram.getData().add(ds);
    }
    public void writeToImageWave(){
        WritableImage wwImage = new WritableImage((int)img.getWidth(),(int) img.getHeight());

        PixelWriter writer = wwImage.getPixelWriter();

        for(int y = 0; y <(int) img.getHeight(); y++) {
            for(int x = 0; x <(int) img.getWidth(); x++) {

                    writer.setColor(y, x,Color.color(1 - (resultOfWaves[x][y]/10),1 - (resultOfWaves[x][y]/10) ,1 - (resultOfWaves[x][y]/10)));

            }

        }

        imgviewForWaves.setImage(wwImage);
    }
}


