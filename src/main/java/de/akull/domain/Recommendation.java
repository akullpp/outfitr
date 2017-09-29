package de.akull.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Recommendation {

    private Integer degree;

    private Scale scale;

    private Integer level;
}
