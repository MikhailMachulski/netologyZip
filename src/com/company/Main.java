package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    protected static final String savePathOne = "/Users/mikhailmachulski/IdeaProjects/Games/savegames/saveOne.java";
    protected static final String savePathTwo = "/Users/mikhailmachulski/IdeaProjects/Games/savegames/saveTwo.java";
    protected static final String savePathThree = "/Users/mikhailmachulski/IdeaProjects/Games/savegames/saveThree.java";


    protected static final String zipPath = "/Users/mikhailmachulski/IdeaProjects/Games/savegames/zip.zip";

    public static void main(String[] args) throws IOException {
        GameProgress playerOne = new GameProgress(88, 2, 13, 11);
        GameProgress playerTwo = new GameProgress(52, 4, 23, 44);
        GameProgress playerThree = new GameProgress(5, 9, 63, 153);

        ArrayList<String> allSavePath = new ArrayList<>();
        allSavePath.add(savePathOne);
        allSavePath.add(savePathTwo);
        allSavePath.add(savePathThree);

        saveGame(savePathOne, playerOne);
        saveGame(savePathTwo, playerTwo);
        saveGame(savePathThree, playerThree);

        zipFiles(zipPath, allSavePath);

        deleteFile(savePathOne);
        deleteFile(savePathTwo);
        deleteFile(savePathThree);

    }

    public static void saveGame(String filePath, GameProgress progress) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            stream.writeObject(progress);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipPath, ArrayList filePath) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipPath))) {

            for (int i = 0; i < filePath.size(); i++) {
                FileInputStream fis = new FileInputStream(filePath.get(i).toString());

                ZipEntry entry = new ZipEntry(filePath.get(i).toString());
                zout.putNextEntry(entry);

                byte[] buffer = new byte[fis.available()];

                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteFile(String filePath) {
        File F = new File(filePath);
        try {
            if (F.delete())
                System.out.println(filePath + " is deleted");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

}
