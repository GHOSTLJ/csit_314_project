package test;

import PROJECTS.control_layer.UserController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserControllerTest {

    private UserController userController;

    @BeforeEach
    public void setUp() throws Exception {
        this.userController = new UserController();
    }

    @AfterEach
    public void tearDown() throws Exception {
        this.userController = null;
    }

    @ParameterizedTest
    @CsvFileSource(resources = "test-data.csv", numLinesToSkip = 1)
    public void testUserLogin(String username, String password, boolean expectedResult) {
        boolean actualResult = userController.UserLogin(username, password);

        if (expectedResult) {
            assertTrue(actualResult, "Login should succeed for username: " + username);
            System.out.println("Login should succeed for username: " + username);
        } else {
            assertFalse(actualResult, "Login should fail for username: " + username);
            System.out.println("Login should fail for username: " + username);
        }
    }
}