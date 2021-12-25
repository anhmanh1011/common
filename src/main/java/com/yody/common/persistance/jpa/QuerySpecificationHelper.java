package com.yody.common.persistance.jpa;

import static com.yody.common.enums.SearchOperation.EQUAL;
import static com.yody.common.enums.SearchOperation.GREATER_THAN;
import static com.yody.common.enums.SearchOperation.GREATER_THAN_EQUAL;
import static com.yody.common.enums.SearchOperation.IN;
import static com.yody.common.enums.SearchOperation.LESS_THAN;
import static com.yody.common.enums.SearchOperation.LESS_THAN_EQUAL;
import static com.yody.common.enums.SearchOperation.MATCH;
import static com.yody.common.enums.SearchOperation.MATCH_END;
import static com.yody.common.enums.SearchOperation.MATCH_START;
import static com.yody.common.enums.SearchOperation.NOT_EQUAL;
import static com.yody.common.enums.SearchOperation.NOT_IN;

import com.yody.common.enums.SearchOperation;
import com.yody.common.utility.Strings;
import java.util.Date;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class QuerySpecificationHelper {

  public static Predicate toPredicate(QueryCriteria criteria, Root<?> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
    if (criteria.getValue() == null) {
      return null;
    }
    if (criteria.getOperation() == GREATER_THAN) {
      if (criteria.isDate()) {
        return builder.greaterThan(root.<Date>get(criteria.getKey()), (Date) criteria.getValue());
      }
      return builder.greaterThan(root.<String>get(criteria.getKey()), criteria.getValue().toString());
    } else if (criteria.getOperation() == GREATER_THAN_EQUAL) {
      if (criteria.isDate()) {
        return builder.greaterThanOrEqualTo(root.<Date>get(criteria.getKey()), (Date) criteria.getValue());
      }
      return builder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
    } else if (criteria.getOperation() == LESS_THAN) {
      if (criteria.isDate()) {
        return builder.lessThan(root.<Date>get(criteria.getKey()), (Date) criteria.getValue());
      }
      return builder.lessThan(root.<String>get(criteria.getKey()), criteria.getValue().toString());
    } else if (criteria.getOperation() == LESS_THAN_EQUAL) {
      if (criteria.isDate()) {
        return builder.lessThanOrEqualTo(root.<Date>get(criteria.getKey()), (Date) criteria.getValue());
      }
      return builder.lessThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
    } else if (criteria.getOperation() == EQUAL) {
      if (criteria.isDate()) {
        return builder.equal(root.<Date>get(criteria.getKey()), (Date) criteria.getValue());
      }
      return builder.equal(root.get(criteria.getKey()), criteria.getValue());
    } else if (criteria.getOperation() == NOT_EQUAL) {
      if (criteria.isDate()) {
        return builder.notEqual(root.<Date>get(criteria.getKey()), (Date) criteria.getValue());
      }
      return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
    } else if (criteria.getOperation() == MATCH) {
      return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
    } else if (criteria.getOperation() == MATCH_START) {
      return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue());
    } else if (criteria.getOperation() == MATCH_END) {
      return builder.like(root.<String>get(criteria.getKey()), criteria.getValue() + "%");
    } else if (criteria.getOperation() == IN) {
      return root.<String>get(criteria.getKey()).in(criteria.getValue());
    } else if (criteria.getOperation() == NOT_IN) {
      return root.<String>get(criteria.getKey()).in(criteria.getValue()).not();
    }
    return null;
  }

  public static QueryCriteria toQuery(String key, String params, boolean isDate) {
    if (Strings.isEmpty(params) || Strings.isEmpty(key)) {
      return null;
    }
    if (params.contains(":")) {
      String[] s = params.split(":");
      SearchOperation operation = SearchOperation.parse(s[0]);
      return new QueryCriteria(key, operation, s[1], isDate);
    }
    return new QueryCriteria(key, EQUAL, params, isDate);
  }
}
