package com.fjern.app.utils;

public class fileUtils {

    public static String removeFileExt(String fileName){
        int indexOfExt=fileName.lastIndexOf('.');
        return fileName.substring(0, indexOfExt);
    }
}
