package demo01;

import com.microsoft.playwright.*;

public class InterceptNetworkRequests {
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
            // 增加路由处理，url参数输入的是路由匹配字符串
            page.route("**", route -> {
                System.out.println(route.request().url());
                route.resume();
            });
            page.navigate("http://todomvc.com");
        }
    }
}