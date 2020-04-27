package br.com.annuum.capsicum.api.converter;

import br.com.annuum.capsicum.api.domain.Encodable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EncodableAttributeConverter {

    public String convertToBinaryCode(List<? extends Encodable> encodables) {
        StringBuilder builder = new StringBuilder();
        encodables.forEach(encodable -> {
            builder.append(Integer.toBinaryString(encodable.getBinaryIdentifier()));
        });
        return builder.toString();
    }
}
