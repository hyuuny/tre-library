package com.hyuuny.trelibrary.popularsearchterms.application;

import com.hyuuny.trelibrary.popularsearchterms.domain.PopularSearchTerm;

public class PopularSearchTermDto {

    public record Response(
            Long id,
            String term,
            Long count
    ) {
        public Response(PopularSearchTerm entity) {
            this(
                    entity.getId(),
                    entity.getTerm(),
                    entity.getCount()
            );
        }
    }
}
