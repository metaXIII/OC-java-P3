package com.metaxiii.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {
    private boolean isDev;

    public App() {
        System.out.println("Lecture du fichier config.properties");
    }

    public boolean checkDev() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            String filename = "config.properties";
            input = App.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return false;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out

            if (prop.getProperty("isDev").contains("true")) {
                isDev = true;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isDev;
    }

    public int getSize() {
        int size = 1;
        Properties prop = new Properties();
        InputStream input = null;

        try {
            String filename = "config.properties";
            input = App.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return 1;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out

            size = prop.getProperty("size").length();

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return size;
    }

    public int getError() {
        int error = 1;
        Properties prop = new Properties();
        InputStream input = null;

        try {
            String filename = "config.properties";
            input = App.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return 1;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out

            error = Integer.parseInt(prop.getProperty("errorMax"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return error;
    }


    public int getValue() {
        int value = 4;
        Properties prop = new Properties();
        InputStream input = null;

        try {
            String filename = "config.properties";
            input = App.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return 1;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out

            value = Integer.parseInt(prop.getProperty("possibleValueMastermind"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
}
