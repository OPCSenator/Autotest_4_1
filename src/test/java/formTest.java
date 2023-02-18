import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class formTest {

    String generateDate(int addDays){
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldRegisterByAccountNumberDOMModification() {
        String myDate = generateDate(4);
        open("http://localhost:9999");
        SelenideElement formCity = $("[data-test-id=city]");
        formCity.$("[data-test-id=city] input").setValue("Кострома");

        SelenideElement formDate = $("[data-test-id=date]");
        formDate.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        formDate.$("[data-test-id=date] input").setValue(myDate);

        SelenideElement formName = $("[data-test-id=name]");
        formName.$("[data-test-id=name] input").setValue("Иванов Иван Иванович");

        SelenideElement formPhone = $("[data-test-id=phone]");
        formPhone.$("[data-test-id=phone] input").setValue("+01234567890");

        $("[class = 'checkbox__box']").click();

        $$("button").find(exactText("Забронировать")).click();

       $(withText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));

        }

}
