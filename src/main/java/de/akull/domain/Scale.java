package de.akull.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Scale {
    CELSIUS("metric");

    private String unit;
}
