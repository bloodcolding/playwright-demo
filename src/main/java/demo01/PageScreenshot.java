package demo01;

import com.microsoft.playwright.*;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class PageScreenshot {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            List<BrowserType> browserTypes = Arrays.asList(
                    playwright.chromium(),
                    playwright.webkit(),
                    playwright.firefox()
            );
            for (BrowserType browserType : browserTypes) {
                try (Browser browser = browserType.launch()) {
                    BrowserContext context = browser.newContext();
                    Page page = context.newPage();
                    page.navigate("https://top.baidu.com/board?platform=pc&sa=pcindex_entry");
                    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot-" + browserType.name() + ".png")));
                }
            }
        }
    }
}