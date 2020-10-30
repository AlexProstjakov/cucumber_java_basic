package pages_sample;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.assertEquals;

public class EditPersonPage extends AddPersonPage{
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Edit')]")
    private WebElement editButton;

    public void editPerson(String name, String job) {
        addNameField.clear();
        addJobField.clear();
        addNameField.sendKeys(name);
        addJobField.sendKeys(job);
        editButton.click();
    }
}
