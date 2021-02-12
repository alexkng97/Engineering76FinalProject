package com.sparta.greg.pom.pagesTest.trainee;

import com.sparta.greg.pom.pages.trainee.AttendanceTrainee;
import com.sparta.greg.pom.pages.trainee.HomeTrainee;
import com.sparta.greg.pom.pages.components.Login;
import com.sparta.greg.pom.pages.trainee.Attendance;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TraineeAttendanceTest {

    protected WebDriver webDriver;
    private Login loginPage;
    private HomeTrainee homePage;
    private AttendanceTrainee traineeAttendance;
    private Properties properties = new Properties();
    private String usernameTrainee;
    private String passwordTrainee;

    @Before
    public void setup(){
        webDriver = new ChromeDriver();
        loginPage = new Login(webDriver);

        try {
            properties.load(new FileReader("src/test/resources/login.properties"));
            usernameTrainee = properties.getProperty("traineeUsername");
            passwordTrainee = properties.getProperty("traineePassword");
        } catch (IOException e) {
            e.printStackTrace();
        }
        homePage = loginPage.logInAsTrainee(usernameTrainee, passwordTrainee);
    }

    @Test
    @DisplayName("Click on a week homepage path")
    public void clickOnAWeekHomePageToAttendance(){
        traineeAttendance = homePage.goToWeeklyAttendance();
        traineeAttendance.clickWeek(11);
    }

    @Test
    @DisplayName("Click on a week sidebar path")
    public void clickOnAWeekSideBar(){
        homePage.getSideBarTrainee().clickTraineeOptions();
        traineeAttendance = homePage.getSideBarTrainee().goToTraineeAttendance();
        traineeAttendance.clickWeek(11);
    }

    @Test
    @DisplayName("Toggle method test")
    public void doesToggleWork(){
        traineeAttendance = homePage.goToWeeklyAttendance();
        traineeAttendance.clickWeek(11);
        Assertions.assertTrue(traineeAttendance.isToggledOnWeek(11));
    }

    @Test
    @DisplayName("Count days in week")
    public void doesCountWork(){
        traineeAttendance = homePage.goToWeeklyAttendance();
        Assertions.assertEquals(5, traineeAttendance.getNumberOfDaysInWeek(6));
    }
}
