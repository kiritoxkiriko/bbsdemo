package com.wxm.bbsdemo.entity;

import com.wxm.bbsdemo.utility.DateUtil;

import java.text.DateFormat;
import java.util.Date;

public class DateReform extends Date {
    public DateReform() {
        super();
    }

    public DateReform(long date) {
        super(date);
    }

    public DateReform(Date date) {
        super(date.getTime());
    }

    @Override
    public String toString() {
        return DateUtil.dateReform(this);
    }
}
