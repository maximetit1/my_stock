package com.example.mystock.service;

import com.example.mystock.documents.Moving;
import com.example.mystock.сontroller.Response;

public interface MoveService {
    /**
     * Перемещение
     * @param moving - список товаров, их количество и склады
     */
    Response posting(Moving moving);
}
