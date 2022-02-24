package com.example.domains.contracts.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface PagingAndSortingDomainService<E, K> extends DomainService<E, K> {
	Iterable<E> getAll(Sort sort);
	Page<E> getAll(Pageable pageable);
}
