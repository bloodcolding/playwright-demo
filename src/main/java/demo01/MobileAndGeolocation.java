package demo01;

import com.microsoft.playwright.*;

import java.nio.file.Paths;
import java.util.List;


public class MobileAndGeolocation {
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
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setUserAgent("Mobile Safari/537.36")
                    // 设置展示屏幕大小
                    .setViewportSize(411, 731)
                    // 设置缩放比例
                    .setDeviceScaleFactor(3)
                    // 移动端网页，一些网站会有针对移动端的样式
                    .setIsMobile(true)
                    // 开启触摸屏幕功能，可以用像素位置点击
                    .setHasTouch(true)
                    // 语言
                    .setLocale("en-US")
                    // 地理位置（此位置为罗马斗兽场）
                    .setGeolocation(41.889938, 12.492507)
                    // 允许进行定位
                    .setPermissions(List.of("geolocation")));
            Page page = context.newPage();
            page.navigate("https://map.baidu.com/");
            // 关闭广告
            page.click("div.close-btn-indexpage");
            // 点击当前地址
            page.click("#map-operate span");
            // 点击确认
            page.click("div.sure-btn");
            // 截屏展示结果
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot2.png")));
        }
    }
}