package com.taskstorage.uroboros.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class ControllerUtils {
    static Map<String, String> getErrors(BindingResult bindingResult) {
        //В качестве ключа - имя поля с ошибкой с приставкой "Error"
        //В качестве значения getDefaultMessage
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        //Получаем лист с ошибками и через stream api преобразуем его в map
        return bindingResult.getFieldErrors().stream().collect(collector);
    }
}