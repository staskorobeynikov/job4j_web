package ru.job4j.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private final Properties properties = new Properties();

    private static final Config CONFIG = new Config();

    private static final Logger LOG = LogManager.getLogger(Config.class.getName());

    public Config() {
        this.init();
    }

    public static Config getInstance() {
        return CONFIG;
    }

    public void init() {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("app.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException io) {
            LOG.error(io.getMessage(), io);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
