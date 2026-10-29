package guru.qa.diploma.api.models.tariffs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PatchTariffBodyModel(
        @JsonProperty("tariffName") String tariffName,
        @JsonProperty("price") Integer price,
        @JsonProperty("description") String description
) {}