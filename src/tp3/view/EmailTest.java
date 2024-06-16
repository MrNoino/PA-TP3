package tp3.view;

import tp3.model.EmailWrapper;

/**
 * A class to test the email sending capabilities
 */
public class EmailTest {
    public static void main(String[] args) {
        EmailWrapper emailWrapper = new EmailWrapper();
        System.out.println(emailWrapper.sendMail("nunosantoslopes@hotmail.com", "Teste2", "Isto é um teste com java"));
    }
}
