package com.groupthree.bankapp.service;

import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class FirstDateOfMonthValidationService {
    public boolean isFirstDayOfTheMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1;
    }

}
