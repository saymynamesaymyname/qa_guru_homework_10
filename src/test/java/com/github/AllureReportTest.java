package com.github;

import com.codeborne.selenide.Condition;
import com.github.steps.WebSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AllureReportTest extends BaseTest {

    public static final String REPOSITORY = "saymynamesaymyname/qa_guru_homework_3";
    public static final int NUMBER = 2;

    @Test
    @DisplayName("Pure Selenide issue search")
    public void pureSelenideIssueSearch() {
        open("https://github.com");

        $("[name=q]").click();
        $("[name=q]").setValue(REPOSITORY);
        $("[name=q]").submit();

        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText("#" + NUMBER)).should(Condition.exist);

    }

    @Test
    @DisplayName("Issue search with lambda steps")
    public void lambdaStepsIssueSearch() {
        step("Open main page", () -> {
            open("https://github.com");
        });
        step("Search for repo " + REPOSITORY, () -> {
            $("[name=q]").click();
            $("[name=q]").setValue(REPOSITORY);
            $("[name=q]").submit();
        });
        step("Click on repo name " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Click on issues tab", () -> {
            $("#issues-tab").click();
        });
        step("Check that issue with #" + NUMBER + " exists", () -> {
            $(withText("#" + NUMBER)).should(Condition.exist);
        });
    }

        @Test
        @Owner("Alex Shestov")
        @Feature("Search")
        @Story("Repository search")
        @DisplayName("Issue search with steps")
        public void issueSearchWithSteps() {
        WebSteps webSteps = new WebSteps();
        webSteps.openMainPage();
        webSteps.searchForRepository(REPOSITORY);
        webSteps.clickOnRepoName(REPOSITORY);
        webSteps.openIssuesTab();
        webSteps.checkThatIssueExistsOnPage(NUMBER);

    }
}
