package web.webTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Random;

public class CreateUserTest extends TestBase{
    private static Random rand = new Random();
    String fullName = "FinalQA"+rand.nextInt(10000);
    String email = "elnacho"+rand.nextInt(10000)+"@hotmail.com";
    String psw = "123456789";
    @Test
    public void verifyCreateUser() throws InterruptedException {
        // create account
        mainPage.signUpButton.click();
        signUpPage.fullNameTextBox.setText(fullName);
        signUpPage.emailTextBox.setText(email);
        signUpPage.passwordTextBox.setText(psw);
        signUpPage.acceptTermsButton.click();
        signUpPage.signUpButton.click();
        Assertions.assertTrue(centralSection.openSettingsButton.isControlDisplayed(), "ERROR! El Usuario no se creo correctamente");

        // logout
        menuSection.logoutButton.click();
        Assertions.assertTrue(mainPage.loginButton.isControlDisplayed(), "ERROR: logout failed");

        // create account again with equals credentials
        mainPage.signUpButton.click();
        signUpPage.fullNameTextBox.setText(fullName);
        signUpPage.emailTextBox.setText(email);
        signUpPage.passwordTextBox.setText(psw);
        signUpPage.acceptTermsButton.click();
        signUpPage.signUpButton.click();
        Assertions.assertEquals("Account with this email address already exists!", mainPage.errorMessage.getTextValue(), "ERROR el no se escontro el mensaje cuenta ya existente!");
        Thread.sleep(2000);
    }

}
