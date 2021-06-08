package config;

import com.datastax.oss.driver.api.core.CqlSession;

public class CaseTableManager extends SimpleManager {

    public CaseTableManager(CqlSession session) {
        super(session);
    }

    public void createTable() {
        executeSimpleStatement(
                "CREATE TABLE case (\n" +
                        "    id BIGINT PRIMARY KEY,\n" +
                        "    otype TEXT,\n" +
                        "    status TEXT,\n" +
                        "    victims SMALLINT\n" +
                        ");");
    }
}
