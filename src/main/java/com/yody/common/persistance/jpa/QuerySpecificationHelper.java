
package com.yody.common.persistance.jpa;

import lombok.AllArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static com.yody.common.enums.SearchOperation.*;
import static com.yody.common.enums.SearchOperation.NOT_IN;


public class QuerySpecificationHelper{

  public static Predicate toPredicate(
      QueryCriteria criteria, Root<?> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
      if (criteria.getValue() == null) return null;
      if (criteria.getOperation() == GREATER_THAN) {
          return builder.greaterThan(
              root.<String>get(criteria.getKey()), criteria.getValue().toString());
      } else if (criteria.getOperation() == GREATER_THAN_EQUAL) {
          return builder.greaterThan(
              root.<String>get(criteria.getKey()), criteria.getValue().toString());
      } else if (criteria.getOperation() == LESS_THAN) {
          return builder.lessThan(root.<String>get(criteria.getKey()), criteria.getValue().toString());
      } else if (criteria.getOperation() == LESS_THAN_EQUAL) {
          return builder.lessThanOrEqualTo(
              root.<String>get(criteria.getKey()), criteria.getValue().toString());
      } else if (criteria.getOperation()== EQUAL) {
          return builder.equal(root.get(criteria.getKey()), criteria.getValue());
      } else if (criteria.getOperation() == MATCH) {
          return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
      } else if (criteria.getOperation()== IN) {
          return root.<String>get(criteria.getKey()).in(criteria.getValue());
      } else if (criteria.getOperation()== NOT_IN) {
          return root.<String>get(criteria.getKey()).in(criteria.getValue()).not();
      }
      return null;
  }
}
