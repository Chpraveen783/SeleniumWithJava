package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReportManager {
    private static ExtentReports extent;

    public static synchronized ExtentReports getExtentReports() {
        if (extent == null) {
            try {
                String userDir = System.getProperty("user.dir");
                Path reportsDir = Paths.get(userDir, "target", "reports");
                if (!Files.exists(reportsDir)) {
                    Files.createDirectories(reportsDir);
                }
                String reportPath = reportsDir.resolve("ExtentReport.html").toString();
                ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
                spark.config().setReportName("E2E Test Report");
                spark.config().setDocumentTitle("Automation Results");

                extent = new ExtentReports();
                extent.attachReporter(spark);
                extent.setSystemInfo("OS", System.getProperty("os.name"));
                extent.setSystemInfo("User", System.getProperty("user.name"));
            } catch (Exception e) {
                throw new RuntimeException("Failed to create report manager", e);
            }
        }
        return extent;
    }
}
