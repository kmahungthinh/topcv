package com.example.topcv.common.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GenericMapper {

  private final ModelMapper modelMapper;

  public GenericMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  // map cả null sang
  public <T, E> E mapToType(T source, Class<E> typeDestination) {
    if (source == null) {
      return null;
    }
    return modelMapper.map(source, typeDestination);
  }

  // map bỏ qua null
  public <T, E> E mapToTypeNotNullProperty(T source, Class<E> typeDestination) {
    if (source == null) {
      return null;
    }
    modelMapper
        .getConfiguration()
        .setPropertyCondition(Conditions.isNotNull())
        .setMatchingStrategy(MatchingStrategies.STRICT);
    return modelMapper.map(source, typeDestination);
  }

  public <S, T> List<T> mapToListOfType(List<S> source, Class<T> targetClass) {
    if (source == null || source.isEmpty()) {
      return null;
    }
    return source.stream()
        .map(item -> modelMapper.map(item, targetClass))
        .collect(Collectors.toList());
  }

  public <S, T> List<T> mapToListOfTypeNotNullProperty(List<S> source, Class<T> targetClass) {
    if (source == null || source.isEmpty()) {
      return null;
    }
    return source.stream()
        .map(item -> mapToTypeNotNullProperty(item, targetClass))
        .collect(Collectors.toList());
  }

  public <S, T> Page<T> toPage(Page<S> source, Class<T> targetClass, Pageable pageable) {
    if (source == null || source.isEmpty()) {
      return null;
    }
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    return new PageImpl<>(
        source.stream()
            .map(item -> modelMapper.map(item, targetClass))
            .collect(Collectors.toList()),
        pageable,
        source.getTotalElements());
  }

  public void copyNonNullProperties(Object src, Object target) {
    BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
  }

  public String[] getNullPropertyNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<>();
    for (java.beans.PropertyDescriptor pd : pds) {
      Object srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null) emptyNames.add(pd.getName());
    }
    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }
}
