package ru.my.task.vaccine_accounting.annotation;

import com.google.common.annotations.VisibleForTesting;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class InsuranceNumberConstraintValidator implements ConstraintValidator<InsuranceNumber, String> {

    @Override
    public void initialize(InsuranceNumber insuranceNumber) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    //    format ХХХ-ХХХ-ХХХ ХХ
    @VisibleForTesting
    @Override
    public boolean isValid(String insuranceNumberField, ConstraintValidatorContext cxt) {
        boolean result = false;

        if ((insuranceNumberField == null) || (insuranceNumberField.equals(""))) {
            return false;
        } else if (!insuranceNumberField.matches("^\\d{3}-\\d{3}-\\d{3}\\s\\d{2}$")) {
            return false;
        } else if (insuranceNumberField.length() != 14) {
            return false;
        }

        insuranceNumberField = insuranceNumberField.replace("-", "");
        insuranceNumberField = insuranceNumberField.replace(" ", "");

        if (insuranceNumberField.length() != 11) {
            return false;
        } else {

            int[] insuranceNumberArray = Arrays.stream(insuranceNumberField.split("")).mapToInt(Integer::parseInt).toArray();
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum = sum + insuranceNumberArray[i] * (9 - i);
            }

            int checkDigit = 0;
            if (sum < 100) {
                checkDigit = sum;
            } else if (sum > 101) {
                checkDigit = sum % 101;
                if (checkDigit == 100) {
                    checkDigit = 0;
                }
            }

            if (String.valueOf(checkDigit).equals(insuranceNumberField.substring(9, 11))) {
                result = true;
            }
        }

        return result;
    }

}
