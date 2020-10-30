package pages_sample;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.assertEquals;

public class AddPersonPage {
    @FindBy(how = How.ID, using = "name")
    protected WebElement addNameField;
    @FindBy(how = How.ID, using = "job")
    protected WebElement addJobField;
    @FindBy(how = How.ID, using = "modal_button")
    private WebElement addPersonButton;
    @FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[2]/div[1]/button[1]")
    private WebElement clearFieldsButton;

    public void addPerson(String name, String job) {
        addNameField.sendKeys(name);
        addJobField.sendKeys(job);

    }
    public void addPersonClick(){
        addPersonButton.click();
    }
    public void editPerson(String name, String job) {
        //This method is overriden in EditPersonPage class//
    }

    public void clearFields() {
        clearFieldsButton.click();
    }
}
