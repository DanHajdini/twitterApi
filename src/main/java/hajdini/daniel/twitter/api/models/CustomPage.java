package hajdini.daniel.twitter.api.models;

import java.util.List;

public record CustomPage<T>(
        int totalPages,
        int currentPage,
        int totalElements,
        int size,
        List<T> content

) {
}
