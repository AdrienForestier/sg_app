package fr.an.test.sparkserver.metadata;

import com.google.common.collect.ImmutableList;
import lombok.Value;

public record JoinRelationship(
        String leftTable,
        String rightTable,
        ImmutableList<JoinedColumn> columns) {
}
