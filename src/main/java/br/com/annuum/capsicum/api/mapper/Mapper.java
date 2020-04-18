package br.com.annuum.capsicum.api.mapper;

import static java.util.stream.Collectors.toList;

import java.util.Collection;

@FunctionalInterface
public interface Mapper<IN, OUT> {

  OUT map(IN in);

  default Collection<OUT> map(final Collection<IN> in) {
    return in.stream().map(this::map).collect(toList());
  }

}
