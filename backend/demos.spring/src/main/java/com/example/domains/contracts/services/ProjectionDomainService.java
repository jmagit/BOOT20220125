package com.example.domains.contracts.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface ProjectionDomainService<E, K> extends PagingAndSortingDomainService<E, K> {
	<T> List<T> getByProjection(Class<T> type);
	<T> Iterable<T> getByProjection(Sort sort, Class<T> type);
	<T> Page<T> getByProjection(Pageable pageable, Class<T> type);
}