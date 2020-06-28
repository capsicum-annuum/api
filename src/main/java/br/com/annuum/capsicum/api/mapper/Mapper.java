package br.com.annuum.capsicum.api.mapper;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@FunctionalInterface
public interface Mapper<IN, OUT> {

    OUT map(IN in);

    default List<OUT> map(final Collection<IN> in) {
        return in.stream().map(this::map).collect(toList());
    }

}
