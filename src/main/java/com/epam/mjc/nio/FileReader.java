package com.epam.mjc.nio;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Path path = Path.of(file.toURI());
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(path)))) {
            Map<String, String> keyValue = new HashMap<>();
            while (br.ready()) {
                String[] split = br.readLine().split(":");
                keyValue.put(split[0], split[1].strip());
            }

            return new Profile(
                    keyValue.get("Name"),
                    Integer.parseInt(keyValue.get("Age")),
                    keyValue.get("Email"),
                    Long.parseLong(keyValue.get("Phone")));

        } catch (IOException e) {
            throw new MyOwnException(e.getMessage());
        }
    }
}
