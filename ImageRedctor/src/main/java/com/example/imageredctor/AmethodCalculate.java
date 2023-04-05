package com.example.imageredctor;

public class AmethodCalculate {
    /*public  byte[][] a4method() {
        // extendedArray = new byte[(int)img.getHeight()+2][(int)img.getWidth()+2];
        a4Array = new byte[(int) img.getHeight() + 2][(int) img.getWidth() + 2];
        a4ArrayCopy = new byte[(int) img.getHeight() + 2][(int) img.getWidth() + 2];
        a8Array = new byte[(int) img.getHeight() + 2][(int) img.getWidth() + 2];
        for (int i = 0; i < (int) img.getHeight() + 2; i++) {
            for (int j = 0; j < (int) img.getWidth() + 2; j++) {
                *//*a4Array[0][j] = 0;
                a4Array[(int)img.getHeight() + 1][j] = 0;*//*
                *//*extendedArray[0][j] = 0;
                extendedArray[(int)img.getHeight() + 1][j] = 0;*//*
            }
           *//* a4Array[i][0] = 0;
            a4Array[i][(int)img.getWidth() + 1] = 0;*//*
            *//*extendedArray[i][0] = 0;
            extendedArray[i][(int)img.getWidth() + 1] = 0;*//*
        }
        for (int i = 1; i < (int) img.getHeight() + 1; i++) {
            for (int j = 1; j < (int) img.getWidth() + 1; j++) {
                // a4Array[i][j] = array[i-1][j-1];
                //  extendedArray[i][j] = Byte.parseByte(tf[i-1][j-1].getText());
            }
        }

        for (int i = 0; i < (int) img.getHeight() + 2; i++) {
            for (int j = 0; j < (int) img.getWidth() + 2; j++) {
                a4ArrayCopy[i][j] = a4Array[i][j];
            }
        }
        for (int i = 1; i < (int) img.getHeight() + 1; i++) {
            for (int j = 1; j < (int) img.getWidth() + 1; j++) {
                a8Array[i][j] = (byte) (a4Array[i + 1][j] + a4Array[i][j + 1] + a4Array[i][j - 1] + a4Array[i - 1][j]
                        + a4Array[i - 1][j - 1] + a4Array[i - 1][j + 1] + a4Array[i + 1][j - 1] + a4Array[i + 1][j + 1]);
            }
        }


        for (int i = 1; i < (int) img.getHeight() + 1; i++) {
            for (int j = 1; j < (int) img.getWidth() + 1; j++) {
                a4ArrayCopy[i][j] = (byte) (a4Array[i + 1][j] + a4Array[i][j + 1] + a4Array[i][j - 1] + a4Array[i - 1][j]);
            }
        }

    }*/
}
