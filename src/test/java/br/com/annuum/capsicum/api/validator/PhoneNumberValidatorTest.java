package br.com.annuum.capsicum.api.validator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneNumberValidatorTest {

    private List<String> invalidDdds = Arrays.asList(
        "00",
        "01",
        "02",
        "03",
        "04",
        "05",
        "06",
        "07",
        "08",
        "09",
        "20",
        "23",
        "25",
        "26",
        "29",
        "30",
        "36",
        "39",
        "40",
        "50",
        "52",
        "56",
        "57",
        "58",
        "59",
        "60",
        "70",
        "72",
        "76",
        "78",
        "80",
        "90"
    );

    private List<String> validDdds = Arrays.asList(
        "11",
        "12",
        "13",
        "14",
        "15",
        "16",
        "17",
        "18",
        "19",
        "21",
        "22",
        "24",
        "27",
        "28",
        "31",
        "32",
        "33",
        "34",
        "35",
        "37",
        "38",
        "41",
        "42",
        "43",
        "44",
        "45",
        "46",
        "47",
        "48",
        "49",
        "51",
        "53",
        "54",
        "55",
        "61",
        "62",
        "63",
        "64",
        "65",
        "66",
        "67",
        "68",
        "69",
        "71",
        "73",
        "74",
        "75",
        "77",
        "79",
        "81",
        "82",
        "83",
        "84",
        "85",
        "86",
        "87",
        "88",
        "89",
        "91",
        "92",
        "93",
        "94",
        "95",
        "96",
        "97",
        "98",
        "99"
    );

    @Test
    public void shouldAcceptPhoneNumberWithElevenDigitsWithValidDddAndStartingWith9() {
        validDdds.forEach(ddd -> assertPhoneOk(ddd + "9" + randomNumeric(8)));
    }

    @Test
    public void shouldNotAcceptPhoneNumberWithElevenDigitsWithInvalidDddAndStartingWith9() {
        invalidDdds.forEach(ddd -> assertPhoneFail(ddd + "9" + randomNumeric(8)));
    }

    @Test
    public void shouldNotAcceptPhoneNumberWithLessThanElevenDigitsWithValidDddAndStartingWith9() {
        validDdds.forEach(ddd -> assertPhoneFail(ddd + "9" + randomNumeric(1, 7)));
    }

    @Test
    public void shouldNotAcceptPhoneNumberWithMoreThanElevenDigitsWithValidDddAndStartingWith9() {
        validDdds.forEach(ddd -> assertPhoneFail(ddd + "9" + randomNumeric(9, 15)));
    }

    private void assertPhoneOk(String phonuNumber) {
        assertTrue(new PhoneNumberValidator().isValid(phonuNumber, null));
    }

    private void assertPhoneFail(String phonuNumber) {
        assertFalse(new PhoneNumberValidator().isValid(phonuNumber, null));
    }

}
