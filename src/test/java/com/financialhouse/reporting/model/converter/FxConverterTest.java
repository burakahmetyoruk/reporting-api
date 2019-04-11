package com.financialhouse.reporting.model.converter;

import com.financialhouse.reporting.entity.Fx;
import com.financialhouse.reporting.model.enumaration.Currency;
import com.financialhouse.reporting.model.response.FxDto;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class FxConverterTest {

    private FxConverter fxConverter;

    @Before
    public void setUp() {
        fxConverter = new FxConverter();
    }

    @Test
    public void should_convert_fx_to_fx_dto() {
        //given
        Fx fx = new Fx();
        fx.setCurrency(Currency.EUR);
        fx.setAmount(BigDecimal.TEN);

        //when
        final FxDto fxDto = fxConverter.apply(fx);

        //then
        assertNotNull(fxDto);
        assertEquals(fx.getAmount(), fxDto.getOriginalAmount());
        assertEquals(fx.getCurrency(), fxDto.getOriginalCurrency());
    }

}