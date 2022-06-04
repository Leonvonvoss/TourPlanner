package at.technikumwien.tourplanner.config;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static Configuration instance;
    private final Properties properties = new Properties();

    private Configuration() {

    }

    public static Configuration Instance(){
        if(instance == null){
            instance = new Configuration();
            try {
                instance.load();
            } catch (IOException e) {
                throw new RuntimeException("Can't load properties!", e);
            }
        }
        return instance;
    }

    private void load() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("at/technikumwien/tourplanner/configs/config.properties");
        properties.load(inputStream);
    }

    public String getProperty(@NotNull String key) {
        if(key.isEmpty()) {
            return "null";
        }
        return properties.getProperty(key);
    }

    public Properties getProperties(){
        return properties;
    }
}
