package FrontEndTest.extentReports;

import com.aventstack.extentreports.ExtentReports;

public class ExtentFactory {
    public static ExtentReports getInstance() {
        ExtentReports extent;
        extent = new ExtentReports();
        extent.setSystemInfo("Selenium Version", "4.0.0");
        extent.setSystemInfo("Platform","Windows");

        return extent;

    }

}
