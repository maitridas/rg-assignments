package com.maitri.employeespring.dao;

import com.maitri.employeespring.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcEmployeeDAO implements EmployeeDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcEmployeeDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Employee employee) {
        String sql = "INSERT INTO employees (name, department) VALUES (?, ?)";
        jdbcTemplate.update(sql, employee.getName(), employee.getDepartment());
    }

    @Override
    public Employee findById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmployeeRowMapper());
    }

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employees SET name = ?, department = ? WHERE id = ?";
        jdbcTemplate.update(sql, employee.getName(), employee.getDepartment(), employee.getId());
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static final class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setDepartment(rs.getString("department"));
            return employee;
        }
    }
}
