package com.example.project;

/**
 * 리스트형 달력 데이터
 **/
public class AbookListVO {
    String listDay;
    String listMoney;
    String listDayWeek;

    AbookListVO(String listDay, String listMoney, String listDayWeek) {
        this.listDay = listDay;
        this.listMoney = listMoney;
        this.listDayWeek = listDayWeek;
    }
}