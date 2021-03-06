package com.garagu.marvel.data.local;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.FileOutputStream;
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

    private String readFile(@NonNull InputStream is) throws IOException {
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

    public String readFromAssets(@NonNull String fileName) throws IOException {
        final InputStream is = context.getAssets().open(fileName);
        return readFile(is);
    }

    public String readFromInternalMemory(@NonNull String fileName) throws IOException {
        final InputStream is = context.openFileInput(fileName);
        return readFile(is);
    }

    public void writeIntoInternalMemory(@NonNull String fileName, @NonNull String fileContent) throws IOException {
        final FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        fos.write(fileContent.getBytes());
        fos.close();
    }

}