package com.homework;

import com.homework.exception.BalanceException;
import com.homework.utils.validators.ValidateBalance;
import org.junit.Test;

import java.math.BigDecimal;

public class ValidateBalanceTest {
    private ValidateBalance validator = new ValidateBalance();

    @Test
    public void checkAmountOnPositiveSum_ValidAmount_ShouldNotThrow() {
        validator.checkAmountOnPositiveSum(new BigDecimal("100"));
    }

    @Test(expected = BalanceException.class)
    public void checkAmountOnPositiveSum_NegativeAmount_ShouldThrow() {
        validator.checkAmountOnPositiveSum(new BigDecimal("-100"));
    }

    @Test
    public void checkBalanceCompareToAmount_SufficientFunds_ShouldNotThrow() {
        validator.checkBalanceCompareToAmount(new BigDecimal("500"), new BigDecimal("300"));
    }

    @Test(expected = BalanceException.class)
    public void checkBalanceCompareToAmount_InsufficientFunds_ShouldThrow() {
        validator.checkBalanceCompareToAmount(new BigDecimal("100"), new BigDecimal("200"));
    }
}