package guru.qa.diploma.api.models.tariffs;

public record TariffModel(
        Integer id,
        String tariffName,
        Integer price,
        String description,
        Integer owner,
        String created,
        String modified
) {}