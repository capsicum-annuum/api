package br.com.annuum.capsicum.api.mapper;

import br.com.annuum.capsicum.api.domain.Encodable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttributeMachCodeMapper {

    public String map(Integer binaryIdentifier) {
        return Integer.toBinaryString(binaryIdentifier);
    }

    public String mapFromList(final List<? extends Encodable> attributes) {
        StringBuilder builder = new StringBuilder();
        attributes.forEach(encodable -> {
            builder.append(Integer.toBinaryString(encodable.getBinaryIdentifier()));
        });
        return builder.toString();
    }

}
