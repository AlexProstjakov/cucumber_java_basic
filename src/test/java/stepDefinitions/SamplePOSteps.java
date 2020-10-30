package stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages_sample.*;

import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class SamplePOSteps {
    private WebDriver driver;
    static AgePage agePage;
    static AgeSubmittedPage ageSubmittedPage;
    static PeopleWithJobs peopleWithJobs;
    static AddPersonPage addPersonPage;
    static AddPersonPage editPersonPage;

    public SamplePOSteps() {
        this.driver = Hooks.driver;
        agePage = PageFactory.initElements(Hooks.driver, AgePage.class);
        ageSubmittedPage = PageFactory.initElements(Hooks.driver, AgeSubmittedPage.class);
        peopleWithJobs = PageFactory.initElements(Hooks.driver, PeopleWithJobs.class);
        addPersonPage = PageFactory.initElements(Hooks.driver, AddPersonPage.class);
        editPersonPage = PageFactory.initElements(Hooks.driver, EditPersonPage.class);
    }

    @When("^I enter name: \"([^\"]*)\" using PO$")
    public void iEnterName(String name) throws Throwable {
        agePage.enterName(name);
    }

    @And("^I enter age: (\\d+) using PO$")
    public void iEnterAge(int age) throws Throwable {
        agePage.enterAge(age);
    }

    @Given("^I (?:am on|open) age page using PO$")
    public void iAmOnAgePage() throws Throwable {
        driver.get(agePage.getPageUrl());
    }

    @And("^I click submit age using PO$")
    public void iClickSubmitAge() throws Throwable {
        agePage.clickSubmit();
    }

    @Then("^I see message: \"(.*)\" using PO$")
    public void iSeeMessage(String message) throws Throwable {
        ageSubmittedPage.checkMessageText(message);
    }

    @When("^I enter values using PO:$")
    public void iEnterValues(Map<String, String> valuesToEnter) throws Throwable {
        agePage.enterName(valuesToEnter.get("name"));
        agePage.enterAge(valuesToEnter.get("age"));
    }



    @Given("^I am on People with jobs page$")
    public void iAmOnPeopleWithJobsPage() {
        driver.get(peopleWithJobs.getPageUrl());
    }

    @When("^I click Add Person$")
    public void iClickAddPerson() {
        peopleWithJobs.clickAddPerson();
    }

    @When("^I add new person with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iAddNewPersonWithAnd(String name, String job) throws Throwable {
        addPersonPage.addPerson(name, job);
        //Thread.sleep(5000);
    }

    @Then("^I want to check that there are (\\d+) people on the list$")
    public void iWantToCheckThatThereArePeopleOnTheList(int listSize) throws InterruptedException {
        assertEquals(listSize, peopleWithJobs.checkListSize());
    }

    @Then("^I want to see a person in the list with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iWantToSeeAPersonInTheListWithAnd(String name, String job) throws Throwable {
        String addedPersonName = driver.findElement(By.cssSelector("div.w3-row:nth-child(3) div.w3-half div.w3-container ul.w3-ul.w3-card-4 div.w3-padding-16:nth-child(4) li:nth-child(1) > span.w3-xlarge.name:nth-child(3)")).getText();
        assertEquals(name, addedPersonName);
        String addedPersonJob = driver.findElement(By.cssSelector("div.w3-row:nth-child(3) div.w3-half div.w3-container ul.w3-ul.w3-card-4 div.w3-padding-16:nth-child(4) li:nth-child(1) > span.job:nth-child(5)")).getText();
        assertEquals(job, addedPersonJob);
    }

    @When("^I click Edit first person$")
    public void iClickEditFirstPerson() throws InterruptedException {
        peopleWithJobs.clickEdit1st();
    }

    @And("^I replace existing with new person with name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void iReplaceExistingWithNewPersonWithAnd(String name, String job) throws Throwable {
        editPersonPage.editPerson(name, job);
    }

    @Then("^I want to see edited person in a list with name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void iWantToSeeEditedPersonInAListWithAnd(String name, String job) throws Throwable {
        String editedPersonName = driver.findElement(By.cssSelector("div.w3-row:nth-child(3) div.w3-half div.w3-container ul.w3-ul.w3-card-4 div.w3-padding-16:nth-child(1) li:nth-child(1) > span.w3-xlarge.name:nth-child(3)")).getText();
        assertEquals(name, editedPersonName);
        String editedPersonJob = driver.findElement(By.cssSelector("div.w3-row:nth-child(3) div.w3-half div.w3-container ul.w3-ul.w3-card-4 div.w3-padding-16:nth-child(1) li:nth-child(1) > span.job:nth-child(5)")).getText();
        assertEquals(job, editedPersonJob);
    }

    @When("^I click Remove person button$")
    public void iClickRemovePersonButton() throws InterruptedException {
        peopleWithJobs.clickRemovePerson();
    }

    @Then("^I want to see this person removed$")
    public void iWantToSeeThisPersonRemoved() throws Exception {
        try {
            driver.findElement(By.id("person0"));
        } catch (Exception e){
            e.getMessage();
            System.out.println("Person succesfully removed!");
        }
    }

    @Then("^I click reset list button$")
    public void iClickResetListButton() {
        peopleWithJobs.clickResetList();
    }

    @And("^I want to see added person removed$")
    public void iWantToSeeAddedPersonRemoved() throws Exception {
        try {
            driver.findElement(By.id("person3"));
            System.out.println("Person not removed, list is not reset!");
        } catch (Exception e){
            e.getMessage();
            System.out.println("Person succesfully removed, list is reset!");
        }
    }

    @And("^I want to see first person with name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void iWantToSeeFirstPersonWithNameAndJob(String name, String job) throws Exception {
        try {
            String nameMike = driver.findElement(By.xpath("//span[contains(text(),'Mike')]")).getText();
            String jobMike = driver.findElement(By.xpath("//span[contains(text(),'Web Designer')]")).getText();
            assertEquals(name, nameMike);
            assertEquals(job, jobMike);
            System.out.println("List is reset!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("List is not reset, original name and job not found!");
        }
    }

    @And("^I click Clear all fields button$")
    public void iClickClearAllFieldsButton() {
        addPersonPage.clearFields();
    }

    @And("^I click Add Person button$")
    public void iClickAddPersonButton() {
        addPersonPage.addPersonClick();
    }

    @Then("^I want to see added person in the list with empty name and job$")
    public void iWantToSeeAddedPersonInTheListWithEmptyNameAndJob() {
        String emptyName = driver.findElement(By.cssSelector("div.w3-row:nth-child(3) div.w3-half div.w3-container ul.w3-ul.w3-card-4 div.w3-padding-16:nth-child(4) li:nth-child(1) > span.w3-xlarge.name:nth-child(3)")).getText();
        assertEquals("", emptyName);
        String emptyJob = driver.findElement(By.cssSelector("div.w3-row:nth-child(3) div.w3-half div.w3-container ul.w3-ul.w3-card-4 div.w3-padding-16:nth-child(4) li:nth-child(1) > span.job:nth-child(5)")).getText();
        assertEquals("", emptyJob);
    }
}
