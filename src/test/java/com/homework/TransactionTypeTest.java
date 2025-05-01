package com.homework;

import com.homework.model.enums.TransactionType;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionTypeTest {
    @Test
    public void values_ShouldReturnAllTypes() {
        TransactionType[] types = TransactionType.values();
        assertEquals(3, types.length);
        assertEquals(TransactionType.DEPOSIT, types[0]);
        assertEquals(TransactionType.WITHDRAWAL, types[1]);
        assertEquals(TransactionType.TRANSFER, types[2]);
    }

    @Test
    public void getDescription_ShouldReturnCorrectDescription() {
        assertEquals("Пополнение", TransactionType.DEPOSIT.getDescription());
        assertEquals("Снятие", TransactionType.WITHDRAWAL.getDescription());
        assertEquals("Перевод", TransactionType.TRANSFER.getDescription());
    }
}