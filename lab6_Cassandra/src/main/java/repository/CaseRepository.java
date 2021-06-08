package repository;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.PagingIterable;
import config.CaseTableManager;
import config.KeyspaceSimpleManager;
import model.CaseDao;
import model.CaseMapper;
import model.Case;
import model.CaseMapperBuilder;

import java.util.Random;
import java.util.Scanner;

public class CaseRepository {
    private final Random random = new Random(System.currentTimeMillis());
    private final static String tableName = "case";
    CaseDao dao;

    public CaseRepository() {
        CqlSession session = CqlSession.builder().build();
        KeyspaceSimpleManager keyspaceManager = new KeyspaceSimpleManager(session, tableName);
        keyspaceManager.dropKeyspace();
        keyspaceManager.selectKeyspaces();
        keyspaceManager.createKeyspace();
        keyspaceManager.useKeyspace();

        CaseTableManager tableManager = new CaseTableManager(session);
        tableManager.createTable();

        CaseMapper caseMapper = new CaseMapperBuilder(session).build();
        dao = caseMapper.caseDao(CqlIdentifier.fromCql(tableName));
    }

    public Case add() {
        Case aCase = enterCaseInfo();
        dao.save(aCase);

        System.out.println(aCase);
        return aCase;
    }

    public void getById(Long id) {
        Case aCase = dao.getById(id);
        if (aCase != null) {
            System.out.println(aCase);
        } else {
            System.out.println("Id not found");
        }
    }

    public void deleteById(Long id) {
        Case aCase = dao.getById(id);
        if (aCase != null) {
            dao.delete(aCase);
            System.out.println("Successfully removed");
        } else {
            System.out.println("Id not found");
        }
    }

    public Case updateById(Long id) {
        Case aCase = dao.getById(id);
        if (aCase != null) {
            aCase = enterCaseInfo();
            aCase.setId(id);
            dao.update(aCase);
            System.out.println(aCase);
        } else {
            System.out.println("Id not found");
        }
        return aCase;
    }

    public void getByType(String type) {
        PagingIterable<Case> cases = dao.getByType(type);
        if (cases != null && cases.one() != null) {
            for (Case aCase : cases) {
                System.out.println(aCase.toString());
            }
        } else {
            System.out.println("Not found");
        }
    }

    public void getAvgVictims() {
        PagingIterable<Case> cases = dao.getAll();
        int sum = 0;
        int n = 0;
        for (Case aCase : cases) {
            sum += aCase.getVictims();
            n++;
        }
        System.out.println("Average number of victims: " + (float) sum / n);
    }

    public void getAll() {
        PagingIterable<Case> cases = dao.getAll();
        if (cases != null && cases.one() != null) {
            for (Case aCase : cases) {
                System.out.println(aCase);
            }
        } else {
            System.out.println("empty");
        }
    }

    private Case enterCaseInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type: ");
        String type = scanner.next();
        System.out.print("Status: ");
        String status = scanner.next();
        System.out.print("Number of victims: ");
        short victims = scanner.nextShort();
        return new Case(Math.abs(random.nextLong()), type, status, victims);
    }
}
