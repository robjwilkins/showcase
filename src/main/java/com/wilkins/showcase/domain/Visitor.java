package com.wilkins.showcase.domain;

public interface Visitor {
    String visit(Party party);

    String visit(Contractor contractor);
}
