package cz.upce.webapp.selenium

import cz.upce.webapp.dao.testutil.Creator
import cz.upce.webapp.dao.users.model.User;
import cz.upce.webapp.selenium.testframework.SeleniumTestCase;
import cz.upce.webapp.selenium.testframework.pages.DashboardPage;
import cz.upce.webapp.selenium.testframework.pages.LoginPage;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Martin Volenec / st46661
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LoginUITest extends Specification
{
    @Autowired private LoginPage loginPage;
    @Autowired private DashboardPage dashboardPage;
    @Autowired private Creator creator


    def "loginPageCorrectTitle"()
    {
        given:
            def password = "\$2a\$12" + "\$TNH.f4YLpJgCVduQPUejUOuIifkJ5T7DjINwgslGI5s4u7.Plm6d."
            creator.save(new User(email: "tester2@test.cz", password: password))
        when:
            def actualDashboardTitle = loginPage
                    .visit()
                    .submitLoginForm("tester2@test.cz", "test")
                    .title

        then:
            dashboardPage.title == actualDashboardTitle;

    }

}
