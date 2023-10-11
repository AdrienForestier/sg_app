package fr.an.test.sparkserver.metadata;

import lombok.Value;

public record JoinedColumn(
        String leftColumn,
        String rightColumn) {
}
