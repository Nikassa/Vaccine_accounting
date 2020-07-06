package ru.my.task.vaccine_accounting.annotation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.my.task.vaccine_accounting.VaccineAccountingApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = VaccineAccountingApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "/application-integrationtest.properties")
public class InsuranceNumberConstraintValidatorTest {

    String insuranceNumber = "160-722-773 54";

    @Test
    public void whenIncorrectFormatAndCheckSum_thenError() {
        InsuranceNumberConstraintValidator insuranceNumberConstraintValidator = new InsuranceNumberConstraintValidator();
        Assert.assertEquals(true, insuranceNumberConstraintValidator.isValid(insuranceNumber, null));
    }

}