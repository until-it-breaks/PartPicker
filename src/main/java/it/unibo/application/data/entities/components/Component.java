package it.unibo.application.data.entities.components;

import java.util.Map;

import it.unibo.application.data.entities.BaseInfo;
import it.unibo.application.data.entities.enums.Specs;

public interface Component {
    BaseInfo getBaseInfo();

    Map<Specs, String> getSpecificAttributes();
}