package com.example.flexitrack3fx.data;


import java.io.BufferedReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Config {
    private static Config config;
    private List<Users> users;
    private static final String FILE_PATH = "users.txt";

    public static Config getInstance() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    private Config() {
        users = new ArrayList<>();
        loadUsersFromFile();
    }

    public List<Users> getUsers() {
        return users;
    }

    public void updateUser(Users updatedUser) {
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(updatedUser.getEmail())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            users.set(index, updatedUser);
            System.out.println("Usuario actualizado: " + updatedUser.getEmail());
        } else {
            users.add(updatedUser);
            System.out.println("Usuario añadido: " + updatedUser.getEmail());
        }
        saveUsersToFile();
    }

    private void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    users.add(new Users(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Users user : users) {
                writer.write(user.getName() + "," + user.getEmail() + "," + user.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}