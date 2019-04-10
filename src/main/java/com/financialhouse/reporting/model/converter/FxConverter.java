package com.financialhouse.reporting.model.converter;

import com.financialhouse.reporting.entity.Fx;
import com.financialhouse.reporting.model.response.FxDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class FxConverter implements Function<Fx, FxDto> {
    @Override
    public FxDto apply(Fx fx) {
        FxDto fxDto = new FxDto();
        fxDto.setOriginalAmount(fx.getAmount());
        fxDto.setOriginalCurrency(fx.getCurrency());
        return fxDto;
    }
}