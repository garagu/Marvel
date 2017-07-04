package com.garagu.marvel.data;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
public class FileManager {

    private final Context context;

    @Inject
    public FileManager(Context context) {
        this.context = context;
    }

    public String readAsset(String fileName) throws IOException {
        final InputStream is = context.getAssets().open(fileName);
        final InputStreamReader isr = new InputStreamReader(is);
        final BufferedReader br = new BufferedReader(isr);
        final StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
        }
        br.close();
        return stringBuilder.toString();
    }

}