package com.pod32g.Demo.Utils;

import java.util.HashMap;
import java.util.Map;

import io.vavr.collection.List;

public class Utils {
    public static <X, Y> Map<X, Y> generateSimpleResponse(X message, Y value) {
        return new HashMap<X, Y>() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            {
                put(message, value);
            }
        };
    }

    public static <X> List<X> convertToVavrList(java.util.List<X> list) {
        return List.ofAll(list);
    }
}