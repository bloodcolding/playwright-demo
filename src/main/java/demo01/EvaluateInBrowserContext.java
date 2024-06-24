package demo01;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class EvaluateInBrowserContext {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            // 采用chromium浏览器
            BrowserType chromium = playwright.chromium();
            // 自定义浏览器配置
            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
            // 展示ui
            options.setHeadless(false)
                    // 每一个步骤之间延迟3000ms
                    .setSlowMo(3000)
                    // 不采用chromium沙箱模式
                    .setChromiumSandbox(false);
            // 启动浏览器
            Browser browser = chromium.launch(options);
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://www.cnblogs.com//");
            // 获取网页信息
            Object dimensions = page.evaluate("""
                    () => {
                      return {
                          width: document.documentElement.clientWidth,
                          height: document.documentElement.clientHeight,
                          deviceScaleFactor: window.devicePixelRatio
                      }
                    }""");
            System.out.println(dimensions);
        }
    }
}