package com.fkzm.blog.mapper;

import com.fkzm.blog.entities.Tag;
import com.fkzm.blog.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User get(Integer id);
    Long delete(Integer id);
    Long save(User user);
    Long update(User user);

    User findUser(@Param("username") String username ,@Param("password") String password);

}
