package com.conferences.invoicing.configs;

import com.conferences.invoicing.domain.scalars.Money;
import graphql.language.StringValue;
import graphql.scalars.ExtendedScalars;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.math.BigDecimal;
import java.util.Currency;

@Configuration
public class GraphQLScalarConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(ExtendedScalars.Date)
                .scalar(ExtendedScalars.GraphQLBigDecimal)
                .scalar(ExtendedScalars.GraphQLBigInteger)
                .scalar(moneyScalar());
    }

    private GraphQLScalarType moneyScalar() {
        return GraphQLScalarType.newScalar()
                .name("Money")
                .description("Monetary value with amount and currency")
                .coercing(new Coercing<Money, String>() {

                    @Override
                    public String serialize(Object dataFetcherResult) {
                        if (dataFetcherResult instanceof Money money) {
                            return money.getAmount() + " " + money.getCurrency().getCurrencyCode();
                        }
                        throw new CoercingSerializeException("Invalid Money value");
                    }

                    @Override
                    public Money parseValue(Object input) {
                        return parseMoney(input);
                    }

                    @Override
                    public Money parseLiteral(Object input) {
                        if (input instanceof StringValue value) {
                            return parseMoney(value.getValue());
                        }
                        throw new CoercingParseLiteralException("Invalid Money literal");
                    }

                    private Money parseMoney(Object input) {
                        if (!(input instanceof String s)) {
                            throw new CoercingParseValueException("Money must be a string");
                        }
                        String[] parts = s.split(" ");
                        return new Money(
                                new BigDecimal(parts[0]),
                                Currency.getInstance(parts[1])
                        );
                    }
                })
                .build();
    }
}
