package com.goit.javadev;

import com.goit.javadev.entity.Client;
import com.goit.javadev.entity.Project;
import com.goit.javadev.entity.ProjectWorker;
import com.goit.javadev.entity.Worker;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class DatabasePopulateService {
    private static final String INSERT_WORKER_QUERY = "INSERT INTO worker VALUES (?, ?, ?, ?, ?);";
    private static final String INSERT_CLIENT_QUERY = "INSERT INTO client VALUES (?, ?);";
    private static final String INSERT_PROJECT_QUERY = "INSERT INTO project VALUES (?, ?, ?, ?, ?);";
    private static final String INSERT_PROJECT_WORKER_QUERY = "INSERT INTO project_worker VALUES (?, ?);";

    private static final List<Worker> WORKERS = Arrays.asList(
            new Worker(1L, "Cade Mays", LocalDate.of(1998, Month.SEPTEMBER, 23), Worker.Level.TRAINEE, 300),
            new Worker(2L, "Ruth Little", LocalDate.of(1995, Month.DECEMBER, 7), Worker.Level.TRAINEE, 500),
            new Worker(3L, "Euan Sykes", LocalDate.of(1989, Month.JULY, 14), Worker.Level.JUNIOR, 900),
            new Worker(4L, "Marnie Wilkinson", LocalDate.of(1983, Month.FEBRUARY, 4), Worker.Level.JUNIOR, 1200),
            new Worker(5L, "Montgomery Welch", LocalDate.of(1986, Month.MAY, 19), Worker.Level.MIDDLE, 2100),
            new Worker(6L, "Zackary Clarke", LocalDate.of(1993, Month.JULY, 1), Worker.Level.MIDDLE, 3240),
            new Worker(7L, "Faris Combs", LocalDate.of(1998, Month.NOVEMBER, 21), Worker.Level.MIDDLE, 4300),
            new Worker(8L, "Wiktor Stone", LocalDate.of(1998, Month.JUNE, 17), Worker.Level.MIDDLE, 1800),
            new Worker(9L, "Mariam Snow", LocalDate.of(2001, Month.FEBRUARY, 5), Worker.Level.SENIOR, 5600),
            new Worker(10L, "Alessia Mora", LocalDate.of(1987, Month.APRIL, 27), Worker.Level.SENIOR, 12000)
    );
    private static final List<Client> CLIENTS = Arrays.asList(
            new Client(1L, "Mohamad Jordan"),
            new Client(2L, "Jamil Coffey"),
            new Client(3L, "Ellena Stein"),
            new Client(4L, "Michael Bennett"),
            new Client(5L, "Abbas Russell")
    );
    private static final List<Project> PROJECTS = Arrays.asList(
            new Project(1L, 1L, "Magnetic Program", LocalDate.of(2021, Month.JANUARY, 10), LocalDate.of(2023, Month.NOVEMBER, 30)),
            new Project(2L, 1L, "Project Synergy", LocalDate.of(2022, Month.NOVEMBER, 23), LocalDate.of(2023, Month.JANUARY, 10)),
            new Project(3L, 3L, "Dynamic Program", LocalDate.of(2022, Month.DECEMBER, 17), LocalDate.of(2023, Month.JUNE, 24)),
            new Project(4L, 4L, "Project Illuminate", LocalDate.of(2018, Month.MARCH, 12), LocalDate.of(2023, Month.JUNE, 1)),
            new Project(5L, 1L, "Native Program", LocalDate.of(2010, Month.APRIL, 11), LocalDate.of(2015, Month.SEPTEMBER, 22)),
            new Project(6L, 4L, "Tarius", LocalDate.of(2022, Month.OCTOBER, 17), LocalDate.of(2023, Month.DECEMBER, 11)),
            new Project(7L, 3L, "MicroRaptor", LocalDate.of(2017, Month.JULY, 14), LocalDate.of(2020, Month.JANUARY, 10)),
            new Project(8L, 3L, "Osiris", LocalDate.of(2021, Month.OCTOBER, 25), LocalDate.of(2022, Month.NOVEMBER, 9)),
            new Project(9L, 3L, "INCANDESCENT GHOST", LocalDate.of(2016, Month.JULY, 6), LocalDate.of(2018, Month.AUGUST, 1)),
            new Project(10L, 5L, "Tearful bite", LocalDate.of(2022, Month.APRIL, 4), LocalDate.of(2025, Month.FEBRUARY, 28))
    );
    private static final List<ProjectWorker> PROJECTS_WORKERS = Arrays.asList(
            new ProjectWorker(1L, 10L),
            new ProjectWorker(2L, 8L),
            new ProjectWorker(2L, 7L),
            new ProjectWorker(2L, 2L),
            new ProjectWorker(3L, 1L),
            new ProjectWorker(3L, 9L),
            new ProjectWorker(3L, 5L),
            new ProjectWorker(4L, 3L),
            new ProjectWorker(4L, 4L),
            new ProjectWorker(5L, 9L),
            new ProjectWorker(5L, 8L),
            new ProjectWorker(5L, 5L),
            new ProjectWorker(5L, 1L),
            new ProjectWorker(5L, 4L),
            new ProjectWorker(6L, 10L),
            new ProjectWorker(6L, 1L),
            new ProjectWorker(7L, 10L),
            new ProjectWorker(8L, 8L),
            new ProjectWorker(8L, 7L),
            new ProjectWorker(8L, 3L),
            new ProjectWorker(9L, 1L),
            new ProjectWorker(9L, 2L),
            new ProjectWorker(9L, 3L),
            new ProjectWorker(10L, 10L),
            new ProjectWorker(10L, 9L),
            new ProjectWorker(10L, 7L),
            new ProjectWorker(10L, 6L)
    );

    public static void main(String[] args) {
        try (Connection connection = Database.getInstance().getConnection()) {
            populateWorker(connection);
            populateClient(connection);
            populateProject(connection);
            populateProjectWorker(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void populateWorker(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_WORKER_QUERY)) {
            for (Worker worker : WORKERS) {
                statement.setLong(1, worker.getId());
                statement.setString(2, worker.getName());
                statement.setDate(3, Date.valueOf(worker.getBirthday()));
                statement.setString(4, worker.getLevel().toString());
                statement.setInt(5, worker.getSalary());

                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    public static void populateClient(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_CLIENT_QUERY)) {
            for (Client client : CLIENTS) {
                statement.setLong(1, client.getId());
                statement.setString(2, client.getName());

                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    public static void populateProject(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_PROJECT_QUERY)) {
            for (Project project : PROJECTS) {
                statement.setLong(1, project.getId());
                statement.setLong(2, project.getClientId());
                statement.setString(3, project.getName());
                statement.setDate(4, Date.valueOf(project.getStartDate()));
                statement.setDate(5, Date.valueOf(project.getFinishDate()));

                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    public static void populateProjectWorker(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_PROJECT_WORKER_QUERY)) {
            for (ProjectWorker projectWorker : PROJECTS_WORKERS) {
                statement.setLong(1, projectWorker.getProjectId());
                statement.setLong(2, projectWorker.getWorkerId());

                statement.addBatch();
            }
            statement.executeBatch();
        }
    }
}
