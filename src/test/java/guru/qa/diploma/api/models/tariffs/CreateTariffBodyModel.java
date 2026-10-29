package guru.qa.diploma.api.models.tariffs;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateTariffBodyModel(
        @JsonProperty("tariffName") String tariffName,
        @JsonProperty("price") Integer price,
        @JsonProperty("description") String description
) {}