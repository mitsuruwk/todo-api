package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.InternalServerErrorException;

import org.postgresql.ds.PGConnectionPoolDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DaoFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DaoFactory.class.getName());

    private static Connection getConnection() {
        Connection con = getConnectionOfHerokuPostgres();
        if (con != null) {
            return con;
        }

        try {
            String name = "java:comp/env/jdbc/todoapi";
            InitialContext context = new InitialContext();
            Object o = context.lookup(name);

            if (o instanceof PGConnectionPoolDataSource) {
                return ((PGConnectionPoolDataSource) o).getConnection();
            }

            DataSource ds = (DataSource) o;
            return ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            throw new InternalServerErrorException(e);
        }
    }

    private static Connection getConnectionOfHerokuPostgres() {
        String s = System.getenv("DATABASE_URL");
        if (s == null) {
            return null;
        }

        LOGGER.info(s);

        try {
            URI dbUri = new URI(s);
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

            return DriverManager.getConnection(dbUrl, username, password);
        } catch (URISyntaxException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static TodoDao createTodoDao() {
        return new TodoDao(getConnection());
    }

    private DaoFactory() {
    }
}
