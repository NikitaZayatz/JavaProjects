package com.example.imageredctor;

public class ThirdWave {

     public void FirstIterationWave(byte array_wave[][],int width,int height){
        double wave_matrix[][] = new double[width][height];
        int count;
        for(int i = 1; i < width+1;i++){
            for(int j = 1; j < height+1;j++){
                if(array_wave[i+1][j+1] == 0){
                    continue;
                }
                else
                {
                   count =(array_wave[i+1][j] + array_wave[i][j+1] + array_wave[i+1][j+1]
                            + array_wave[i][j-1] + array_wave[i-1][j]+array_wave[i-1][j-1]
                           + array_wave[i-1][j+1] + array_wave[i +1][j-1]);
                   wave_matrix[i-1][j-1] = count * 0.6;
                }

            }
        }
        for(int i = 0;i < width;i++){
            for(int j = 0; j < height;j++){
                System.out.print(String.format("%3.3f ",wave_matrix[i][j]));
            }
            System.out.println();
        }
    }
    public void SecondIterationWave(){

    }
    public void ThirdIterationWave(){

    }
}
