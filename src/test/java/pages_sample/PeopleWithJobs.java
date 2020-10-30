package pages_sample;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class PeopleWithJobs {
    @FindBy(how = How.XPATH, using = "//*[contains(text(), 'Add')]")
    private WebElement addPersonButton;
    @FindBy(how = How.XPATH, using = "//*[contains(text(), 'Reset')]")
    private WebElement resetListButton;
    @FindBy(how = How.XPATH, using = "//body/div[3]/div[1]/div[1]/ul[1]/div[1]/li[1]/span[2]")
    private WebElement edit1stButton;//
    @FindBy(how = How.XPATH, using = "//body/div[3]/div[1]/div[1]/ul[1]/div[1]/li[1]/span[1]")
    private WebElement remove1stButton;
    @FindBy(how = How.CLASS_NAME, using = "w3-padding-16")
    private List<WebElement> listOfPersons;


    public String getPageUrl() {
        return "https://kristinek.github.io/site/tasks/list_of_people_with_jobs.html";
    }

    public void clickAddPerson() {
        addPersonButton.click();

    }

    public void clickEdit1st() throws InterruptedException {
        Thread.sleep(3000);
        edit1stButton.click();
    }
    public int checkListSize() {
        return listOfPersons.size();
    }
    public void clickRemovePerson() throws InterruptedException{
        Thread.sleep(3000);
        remove1stButton.click();
    }
    public void clickResetList() {
        resetListButton.click();
    }
}
