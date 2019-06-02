package com.example.demo.service;

import com.example.demo.entity.Post;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 投稿検索条件
 */
class PostSpecifications {

    /**
     * title:like
     */
    static Specification<Post> tittleContains(String title) {
        return StringUtils.isEmpty(title) ? null : new Specification<Post>() {
            @Override
            public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("title"), "%" + title + "%");
            }
        };
    }

    /**
     * detail:like
     */
    static Specification<Post> detailContains(String detail) {
        return StringUtils.isEmpty(detail) ? null : new Specification<Post>() {
            @Override
            public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("detail"), "%" + detail + "%");
            }
        };
    }
}
