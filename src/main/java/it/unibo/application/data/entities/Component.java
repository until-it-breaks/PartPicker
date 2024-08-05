package it.unibo.application.data.entities;

import java.util.Map;

public interface Component {
    BaseInfo getBaseInfo();

    Map<String, String> getSpecificAttributes();
}