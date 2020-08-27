package br.com.annuum.capsicum.api.mapper;

import br.com.annuum.capsicum.api.domain.Encodable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class AttributeMachCodeMapper {

    public Integer map(final List<? extends Encodable> attributes) {
        AtomicReference<Integer> matchCode = new AtomicReference<>(0);
        attributes.forEach(encodable -> {
            matchCode.updateAndGet(v -> v + encodable.getBinaryIdentifier());
        });
        return matchCode.get();
    }

}
