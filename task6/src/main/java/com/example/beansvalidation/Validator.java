package com.example.beansvalidation;

import com.example.annotations.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Validator {
    public static List<String> validate(Object o) {

        List<String> messagesList = new ArrayList<String>();
        Class cl = o.getClass();

        for (Field field: cl.getDeclaredFields()) {
            field.setAccessible(true);
            //System.out.println(field.getName() + ": ");

            AssertFalse af = field.getAnnotation(AssertFalse.class);
            if (af != null) {
                //System.out.println(af.message());
                messagesList.add(af.message());
            }

            AssertTrue at = field.getAnnotation(AssertTrue.class);
            if (at != null) {
                //System.out.println(at.message());
                messagesList.add(at.message());
            }

            Email email = field.getAnnotation(Email.class);
            if (email != null) {
                try {
                    Object value = field.get(o);
                    if (value != null) {
                        boolean isMatchPattern = Pattern.compile("^(.+)@(\\S+)$")
                                .matcher(value.toString())
                                .matches();
                        if (!isMatchPattern) {
                            //System.out.println(email.message());
                            messagesList.add(email.message());
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            MaxLength maxLen = field.getAnnotation(MaxLength.class);
            if (maxLen != null) {
                try {
                    Object value = field.get(o);
                    if (value != null) {
                        int valueLen = value.toString().length();
                        if (valueLen > maxLen.len()) {
                            //System.out.println(maxLen.message());
                            messagesList.add(maxLen.message());
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            MinLength minLen = field.getAnnotation(MinLength.class);
            if (minLen != null) {
                try {
                    Object value = field.get(o);
                    if (value != null) {
                        int valueLen = value.toString().length();
                        if (valueLen < maxLen.len()) {
                            //System.out.println(minLen.message());
                            messagesList.add(minLen.message());
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            NotNullOrEmpty notNullOrEmpty = field.getAnnotation(NotNullOrEmpty.class);
            if (notNullOrEmpty != null) {
                try {
                    Object value = field.get(o);
                    if (Objects.isNull(value) || value.equals(0)) {
                        //System.out.println(notNullOrEmpty.message());
                        messagesList.add(notNullOrEmpty.message());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return messagesList;
    }
}