package com.financialhouse.reporting.model.converter;

import com.financialhouse.reporting.entity.Acquirer;
import com.financialhouse.reporting.model.response.AcquirerDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AcquirerConverterTest {

    private AcquirerConverter acquirerConverter;

    @Before
    public void setUp() {
        acquirerConverter = new AcquirerConverter();
    }

    @Test
    public void should_convert_acquirer_to_acquirer_dto() {
        //given
        Acquirer acquirer = new Acquirer();
        acquirer.setId(1L);
        acquirer.setName("name");

        //when
        final AcquirerDto acquirerDto = acquirerConverter.apply(acquirer);

        //then
        assertNotNull(acquirerDto);
        assertEquals(acquirer.getId(), acquirerDto.getCode());
        assertEquals(acquirer.getName(), acquirerDto.getName());
    }
}