package com.trustly.gamerranker.data.mapper;

import com.trustly.gamerranker.data.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper {

  @Override
  public User mapRow(ResultSet resultSet, int i) throws SQLException {
    User user = new User();
    user.setId(resultSet.getLong("id"));
    user.setName(resultSet.getString("username"));
    return user;
  }
}
