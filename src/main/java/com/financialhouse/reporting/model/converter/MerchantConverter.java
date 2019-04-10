package com.financialhouse.reporting.model.converter;

import com.financialhouse.reporting.entity.Merchant;
import com.financialhouse.reporting.model.response.MerchantDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MerchantConverter implements Function<Merchant, MerchantDto> {
    @Override
    public MerchantDto apply(Merchant merchant) {
        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setId(merchant.getId());
        merchantDto.setName(merchant.getName());
        return merchantDto;
    }
}