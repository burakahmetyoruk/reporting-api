package com.financialhouse.reporting.model.converter;

import com.financialhouse.reporting.entity.Merchant;
import com.financialhouse.reporting.model.response.MerchantDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MerchantConverterTest {

    private MerchantConverter merchantConverter;

    @Before
    public void setUp() {
        merchantConverter = new MerchantConverter();
    }

    @Test
    public void should_convert_merchant_to_merchant_dto() {
        //given
        Merchant merchant = new Merchant();
        merchant.setName("name");
        merchant.setId(1L);

        //when
        final MerchantDto merchantDto = merchantConverter.apply(merchant);

        //then
        assertNotNull(merchantDto);
        assertEquals(merchant.getId(), merchantDto.getId());
        assertEquals(merchant.getName(), merchantDto.getName());
    }
}