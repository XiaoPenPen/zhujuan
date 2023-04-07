package com.game.zhujuan.repository;

import com.game.zhujuan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dushixiang
 * @date 2019-04-24 19:13
 */
public interface UserRepository extends JpaRepository<User, String> {
}
