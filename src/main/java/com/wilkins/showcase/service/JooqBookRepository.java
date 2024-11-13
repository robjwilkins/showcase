package com.wilkins.showcase.service;

import com.wilkins.showcase.Book;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

import static org.jooq.impl.DSL.field;

@Service
public class JooqBookRepository implements BookRepository {

    private final DSLContext dsl;

    public JooqBookRepository(DataSource dataSource) throws SQLException {
        this.dsl = DSL.using(dataSource.getConnection());
    }

    @Override
    public Stream<Book> findAll() {
        return dsl.select(bookFields)
                .from(bookTable)
                .fetchStreamInto(Book.class);
    }

    private static final Table<Record> bookTable = DSL.table("book");
    private static final Field<String> externalId = field("book.external_id", String.class);
    private static final Field<String> title = field("book.title", String.class);
    private static final Field<String> author = field("book.author", String.class);
    private static final List<Field<String>> fields = List.of(externalId, title, author);
    private static final Field[] bookFields = fields.toArray(new Field[0]);
}
