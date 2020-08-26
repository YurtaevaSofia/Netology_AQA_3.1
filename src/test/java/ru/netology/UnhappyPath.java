package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UnhappyPath {

    @Test
    public void shouldNotSubmitRequestWithoutName(){

        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[name=phone]").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        form.$("[data-test-id=name] [class = input__sub]").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldNotSubmitRequestWithoutPhone(){

        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[name=name]").setValue("Софья");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        form.$("[data-test-id=phone] [class = input__sub]").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldNotSubmitRequestWithoutCheckbox(){

        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[name=name]").setValue("Софья");
        form.$("[name=phone]").setValue("+79270000000");
        form.$("button").click();
        form.$("#root > div > form > div:nth-child(3) > label").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}

