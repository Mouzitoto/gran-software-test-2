package kz.mouzitoto.gransoftwaretest2.config;

import kz.mouzitoto.gransoftwaretest2.model.SortDirection;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SortDirectionConverter implements Converter<String, SortDirection> {

    private static final String SORT_DIRECTION_ASC = "asc";

    @Override
    public SortDirection convert(String value) {
        try {
            return SortDirection.valueOf(value.trim().toLowerCase());
        } catch (Exception e) {
            return SortDirection.valueOf(SORT_DIRECTION_ASC);
        }
    }
}
