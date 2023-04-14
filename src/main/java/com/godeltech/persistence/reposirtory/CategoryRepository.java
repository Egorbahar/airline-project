package com.godeltech.persistence.reposirtory;

import com.godeltech.persistence.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
