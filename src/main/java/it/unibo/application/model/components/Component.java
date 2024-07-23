package it.unibo.application.model.components;

import java.time.Year;
import java.util.Currency;
import java.util.UUID;

public interface Component {

    UUID getUuid();

    String getName();

    Year getLaunchYear();

    Currency getMsrp();

}