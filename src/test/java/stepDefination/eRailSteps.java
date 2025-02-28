package stepDefination;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageFile.eRail;

public class eRailSteps{

    eRail er= new eRail();
    @Given("user open url on browser")
    public void user_open_url_on_browser() {
        er.userOpenOnBrowser("https://erail.in/");
    }
    @When("user clicked on from field")
    public void user_clicked_on_from_field() {
        er.userClickedOnFromField();
    }
    @Then("user clear data from field")
    public void user_clear_data_from_field() {
        er.userClearDataFromField();
    }
    @Then("user enter data del and opened dropdown")
    public void user_enter_data_del_and_opened_dropdown() {
        er.userEnterDataDelAndOpenedDropdown();
    }
    @Then("user select fourth positon and print on console")
    public void user_select_fourth_positon_and_print_on_console() {
        er.userSelectFourthPositonAndPrintOnConsole();
    }
    @Then("user stored all suggested station name on execel sheet and compared")
    public void user_stored_all_suggested_station_name_on_execel_sheet_and_compared() {
        er.userStoredAllSuggestedStationNameOnExecelSheetAndCompared();
    }
    @And("user select thirty day from today")
    public void user_select_thirty_day_from_today() {
        er.userSelectThirtyDayFromToday();
    }



}
