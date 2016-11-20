package com.us.image.type.handler;

import com.us.image.enumeration.BloodGroup;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:rekadowney@163.com">发送邮件</a>
 * @createDate : 2016/11/20 19:23
 * @description :
 */
@MappedTypes(value = BloodGroup.class)
@MappedJdbcTypes(value = JdbcType.VARCHAR)
public class BloodGroupTypeHandler extends BaseTypeHandler<BloodGroup> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BloodGroup parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getName());
    }

    @Override
    public BloodGroup getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return transfer(rs.getString(columnName));
    }

    @Override
    public BloodGroup getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return transfer(rs.getString(columnIndex));
    }

    @Override
    public BloodGroup getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return transfer(cs.getString(columnIndex));
    }

    private BloodGroup transfer(String dbData) {
        return StringUtils.hasLength(dbData) ? BloodGroup.parse(dbData) : null;
    }

}
