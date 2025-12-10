package framework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocatorManager {

    private static final String LOCATORS_DIR = "/locators/";
    private static final Map<String, Map<String, String>> locatorsCache = new ConcurrentHashMap<>();

    public static By getLocator(String page, String elementName) {
        Map<String, String> pageLocators = locatorsCache.computeIfAbsent(page, LocatorManager::loadLocators);
        String locatorValue = pageLocators.get(elementName);

        if (locatorValue == null) {
            throw new IllegalArgumentException("No locator found for element: " + elementName + " on page: " + page);
        }

        String[] parts = locatorValue.split("=", 2);
        String strategy = parts[0];
        String value = parts[1];

        switch (strategy.toLowerCase()) {
            case "id":
                return By.id(value);
            case "name":
                return By.name(value);
            case "classname":
                return By.className(value);
            case "tagname":
                return By.tagName(value);
            case "linktext":
                return By.linkText(value);
            case "partiallinktext":
                return By.partialLinkText(value);
            case "css":
                return By.cssSelector(value);
            case "xpath":
                return By.xpath(value);
            default:
                throw new IllegalArgumentException("Unsupported locator strategy: " + strategy);
        }
    }

    private static Map<String, String> loadLocators(String page) {
        String filePath = LOCATORS_DIR + page.toLowerCase() + ".json";
        try (InputStream inputStream = LocatorManager.class.getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IllegalStateException("Cannot find " + filePath);
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, new TypeReference<Map<String, String>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to load locators for page: " + page, e);
        }
    }
}