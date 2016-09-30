import java.net.URI;

import org.flywaydb.core.Flyway;

public class Migrations {
    public static void main(String[] args) throws Exception {
    	String url = "jdbc:postgresql://localhost:5432/tododb";
		String user = "todoadmin";
	    String password = "K9T0my8eJG8UXncuaTwDC7BclHpOg9H6";

        String s = System.getenv("DATABASE_URL");
        if (s != null) {
            URI dbUri = new URI(s);
            url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            user = dbUri.getUserInfo().split(":")[0];
            password = dbUri.getUserInfo().split(":")[1];
        }

	    Flyway flyway = new Flyway();
        flyway.setDataSource(url, user, password);
        flyway.migrate();
    }
}
