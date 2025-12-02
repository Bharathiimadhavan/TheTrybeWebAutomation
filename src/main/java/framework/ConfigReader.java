package framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        loadConfig();
    }

    public static void loadConfig() {
        String env = System.getProperty("env");
        if (env == null || env.isEmpty()) {
            env = "staging";
        }
        String fileName = "src/main/resources/config." + env + ".properties";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not load properties file: " + fileName, e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
