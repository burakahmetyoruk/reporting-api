package com.financialhouse.reporting.model.converter;

import com.financialhouse.reporting.entity.Acquirer;
import com.financialhouse.reporting.model.response.AcquirerDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AcquirerConverter implements Function<Acquirer, AcquirerDto> {
    @Override
    public AcquirerDto apply(Acquirer acquirer) {
        AcquirerDto acquirerDto = new AcquirerDto();
        acquirerDto.setCode(acquirer.getId());
        acquirerDto.setName(acquirer.getName());
        return acquirerDto;
    }
}