package com.github.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Open main page")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Search for repository with {repo} name")
    public void searchForRepository(String repo) {
        $("[name=q]").click();
        $("[name=q]").setValue(repo);
        $("[name=q]").submit();
    }

    @Step("Click on repository link with name {repo}")
    public void clickOnRepoName(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Open issues tab")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Check that issue with #{number} exists on page")
    public void checkThatIssueExistsOnPage(int number) {
        $(withText("#" + number)).should(Condition.exist);

    }
}
