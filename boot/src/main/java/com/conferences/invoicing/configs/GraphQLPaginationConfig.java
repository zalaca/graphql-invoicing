package com.conferences.invoicing.configs;

import graphql.schema.GraphQLTypeVisitor;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.graphql.data.pagination.ConnectionFieldTypeVisitor;
import org.springframework.graphql.data.pagination.CursorEncoder;
import org.springframework.graphql.data.pagination.CursorStrategy;
import org.springframework.graphql.data.query.ScrollPositionCursorStrategy;
import org.springframework.graphql.data.query.SliceConnectionAdapter;
import org.springframework.graphql.data.query.WindowConnectionAdapter;

import java.util.List;

@Configuration
public class GraphQLPaginationConfig {

    @Bean
    GraphQlSourceBuilderCustomizer graphQlSourceCustomizer() {
        CursorStrategy<ScrollPosition> strategy =
                CursorStrategy.withEncoder(
                        new ScrollPositionCursorStrategy(),
                        CursorEncoder.base64()
                );

        GraphQLTypeVisitor visitor = ConnectionFieldTypeVisitor.create(
                List.of(
                        new WindowConnectionAdapter(strategy),
                        new SliceConnectionAdapter(strategy)
                )
        );

        return builder -> builder.typeVisitors(List.of(visitor));
    }
}
