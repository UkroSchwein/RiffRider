package com.example.riffrider;

import android.os.Bundle;

import java.util.List;

public class DataHolder {
    private static List<String> data;

    private static boolean Filtered = false;

    public static List<String> getData() {
        return data;
    }

    public static void setData(List<String> data) {
        DataHolder.data = data;
    }

    public static boolean getFiltered() {
        return Filtered;
    }

    public static void setFiltered(boolean data) {
        DataHolder.Filtered = data;
    }
}
