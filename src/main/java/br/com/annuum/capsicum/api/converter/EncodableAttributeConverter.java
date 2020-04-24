package br.com.annuum.capsicum.api.converter;

import br.com.annuum.capsicum.api.domain.Encodable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EncodableAttributeConverter {

    public String convertToBinaryCode(List<Encodable> attribute) {
        StringBuilder builder = new StringBuilder();
        attribute.forEach(encodable -> {
            builder.append(Integer.toBinaryString(encodable.getBinaryIdentifier()));
        });
        return builder.toString();
    }
}
