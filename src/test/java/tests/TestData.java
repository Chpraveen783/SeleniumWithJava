package tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TestData {
    private String email;
    private String password;
    private String productName;
    private String country;

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getProductName() { return productName; }
    public String getCountry() { return country; }

    public static TestData loadFromYaml(String path) {
        Map<String, String> map = new HashMap<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                int idx = line.indexOf(':');
                if (idx <= 0) continue;
                String key = line.substring(0, idx).trim();
                String value = line.substring(idx + 1).trim();
                // remove surrounding quotes if present
                if ((value.startsWith("\"") && value.endsWith("\"")) || (value.startsWith("'") && value.endsWith("'"))) {
                    value = value.substring(1, value.length() - 1);
                }
                map.put(key, value);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read test data file: " + path, e);
        }
        TestData td = new TestData();
        td.email = map.getOrDefault("email", "");
        td.password = map.getOrDefault("password", "");
        td.productName = map.getOrDefault("productName", "");
        td.country = map.getOrDefault("country", "");
        return td;
    }
}
