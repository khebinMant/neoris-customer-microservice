package com.neoris.customer.common.repositories;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

/**
 * Repository base with support querydsl
 *
 * @author Kevin
 * @version 1.1
 * @param <T>
 */
public class JPAQueryDslBaseRepository<T> extends QuerydslRepositorySupport implements IQueryDslBaseRepository<T> {

    /**
     * Entity class
     */
    private final Class<T> domainClass;

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public JPAQueryDslBaseRepository(Class<T> domainClass) {
        super(domainClass);
        this.domainClass = domainClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(T entity) {
        getEntityManager().persist(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(T entity) {
        getEntityManager().merge(entity);
    }

    /**
     * Clone query
     *
     * @param query JPQL query
     * @param <P> Query by entity
     * @return Query
     */
    protected <P> JPQLQuery<P> cloneQuery(JPAQuery<P> query) {
        return query.clone(this.getEntityManager());
    }

    /**
     * Find page by query
     *
     * @param query JPQL query
     * @param pageable Pagination
     * @param <Q> Entity
     * @return Page
     */
    protected <Q> Page<Q> findPageData(JPQLQuery<Q> query, Pageable pageable) {
        JPQLQuery<Q> countQuery = cloneQuery((JPAQuery<Q>) query);
        return PageableExecutionUtils.getPage(getQuerydsl().applyPagination(pageable, query).fetch(), pageable, countQuery::fetchCount);
    }
}
