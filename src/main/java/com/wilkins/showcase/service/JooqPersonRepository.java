package com.wilkins.showcase.service;

import com.wilkins.showcase.domain.Person;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.jooq.impl.DSL.field;

@Service
public class JooqPersonRepository implements PersonRepository {

    private final DSLContext dsl;

    public JooqPersonRepository(DataSource dataSource) throws SQLException {
        this.dsl = DSL.using(dataSource.getConnection());
    }

    @Override
    public List<Person> findAll() {
        return dsl.select(personFields)
                .from(personTable)
                .fetch(asPerson());
    }

    private static RecordMapper<Record, Person> asPerson() {
        return r -> new Person(r.get(externalId),
                r.get(title),
                r.get(forename),
                r.get(surname));
    }

    private static final Table personTable = DSL.table("person");
    private static final Field<String> externalId = field("person.external_id", String.class);
    private static final Field<String> title = field("person.title", String.class);
    private static final Field<String> forename = field("person.forename", String.class);
    private static final Field<String> surname = field("person.surname", String.class);
    private static final Field[] personFields = {
            externalId, title, forename, surname
    };

}
