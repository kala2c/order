package com.order.repository;

import com.order.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAllByOrderByWeight();

    List<Category> findAllByIdIn(List<Integer> list);

    /**
     * 更新类目信息
     * @param id 要更新的类目id
     * @param name 更新后的名字
     * @param description 更新后的介绍
     * @return 返回更新数据的实体
     */
    @Modifying
    @Query(value = "update category set name=?2 description=?3 where id=?1 ", nativeQuery = true)
    Category updateInfo(Integer id, String name, String description);
}
