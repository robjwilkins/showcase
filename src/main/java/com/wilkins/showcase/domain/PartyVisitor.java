package com.wilkins.showcase.domain;

public class PartyVisitor implements Visitor {
    @Override
    public String visit(Party party) {
        return "Notice period = " + party.noticePeriod().toDays();
    }

    @Override
    public String visit(Contractor contractor) {
        return "Contractor Notice period = " + contractor.noticePeriod().toDays();
    }

}
