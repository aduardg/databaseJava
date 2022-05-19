package ru.database.application.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.database.application.model.User;

@Mapper
public interface UserMapper {
     User getAutorization(User user);
     int setTokenUser(User user);
}
