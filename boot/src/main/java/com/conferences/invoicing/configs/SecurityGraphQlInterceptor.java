package com.conferences.invoicing.configs;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class SecurityGraphQlInterceptor {//implements WebGraphQlInterceptor {

    /*@Override
    public Mono<WebGraphQlResponse> intercept(
            WebGraphQlRequest request,
            Chain chain) {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        request.configureExecutionInput((ei, builder) ->
                builder.graphQLContext(ctx -> ctx.put("auth", auth)).build()
        );

        return chain.next(request);
    }*/
}

