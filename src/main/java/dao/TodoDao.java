package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import model.Todo;

public class TodoDao {

	private QueryRunner queryRunner;
	private Connection connection;

	public TodoDao(Connection connection) {
		this.queryRunner = new QueryRunner();
		this.connection = connection;
	}

	public List<Todo> all() {
		ResultSetHandler<List<Todo>> h = new BeanListHandler<Todo>(Todo.class);
		try {
			List<Todo> list = this.queryRunner.query(this.connection, "SELECT * FROM todos", h);
			if (list.isEmpty()) {
				throw new NotFoundException();
			}

			return list;
		} catch (SQLException e) {
        	e.printStackTrace();
			throw new InternalServerErrorException(e);
		}
	}

	public Todo create(Todo todo) {
		ResultSetHandler<Todo> h = new BeanHandler<Todo>(Todo.class);
		try {
			return this.queryRunner.insert(this.connection, "INSERT INTO todos (title, status) values (?,?)", h,
					todo.getTitle(), todo.getStatus());
		} catch (SQLException e) {
			throw new InternalServerErrorException(e);
		}
	}

	public void delete(int id) {
		try {
			int num = this.queryRunner.update(this.connection, "DELETE FROM todos WHERE id = ?", id);
			if (num == 0) {
				throw new NotFoundException();
			}
		} catch (SQLException e) {
			throw new InternalServerErrorException(e);
		}
	}

	public Todo findById(int id) {
		ResultSetHandler<List<Todo>> h = new BeanListHandler<Todo>(Todo.class);
		try {
			List<Todo> list = this.queryRunner.query(this.connection, "SELECT * FROM todos WHERE id = ?", h, id);
			if (list.isEmpty()) {
				throw new NotFoundException();
			}

			return list.get(0);
		} catch (SQLException e) {
			throw new InternalServerErrorException(e);
		}
	}

	public void update(Todo todo) {
		try {
			int num = this.queryRunner.update(this.connection, "UPDATE todos SET title = ?, status = ? WHERE id = ?",
					todo.getTitle(), todo.getStatus(), todo.getId());
			if (num == 0) {
				throw new NotFoundException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InternalServerErrorException(e);
		}
	}
}
