package it.unibo.application.data.entities.components;

import java.util.Map;

import it.unibo.application.data.entities.BaseInfo;

public interface Component {
    BaseInfo getBaseInfo();

    Map<String, String> getSpecificAttributes();
}