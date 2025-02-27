package com.gsm._8th.class4.backend.task12.global.annotation;

import jdk.jfr.Name;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Name("TaskInfo")
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface TaskInfo {
}