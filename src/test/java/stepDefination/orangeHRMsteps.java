package stepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageFile.orangeHRM;

public class orangeHRMsteps {
    orangeHRM o=new orangeHRM();
    @Given("user open orangehrm webpage")
    public void user_open_orangehrm_webpage() {
        o.userOpenOrangehrmWebpage();
    }
    @Then("user enter valid username")
    public void user_enter_valid_username() {
        o.userEnterValidUsername();
    }
    @Then("user enter valid password")
    public void user_enter_valid_password() {
        o.userEnterValidPassword();
    }
    @Then("user click on login button")
    public void user_click_on_login_button() {
        o.userClickOnLoginButton();
    }
    @Then("user verify login succes")
    public void user_verify_login_succes() {
        o.userVerifyLoginSucces();
    }
    @Then("user enter Invalid username")
    public void user_enter_invalid_username() {
        o.userEnterInValidUsername();
    }
    @Then("user enter Invalid password")
    public void user_enter_invalid_password() {
        o.userEnterInValidPassword();
    }
    @Then("user verify invalid ui response")
    public void user_verify_invalid_ui_response() {
        o.userVerifyInvalidUiResponse();
    }
}
