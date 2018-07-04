package dao;

import javax.annotation.PostConstruct;

public enum DBVar {
    DB_CLIENTS("clients"),
    DB_WORKERS("workers"),
    DB_VISITS("visits"),
    DB_SUBS("subscriptions"),
    DB_SPEC_OF_WORK("specialization_of_workers"),
    DB_DISC("discounts"),
    DB_PASSPORT("passport"),
    DB_SPEC("specializations"),

    ID_WORKER("id_worker"),
    SALARY("salary"),

    ID_CLIENT("id_client"),
    BIRTHDAY("birthday"),

    FIRST_NAME("first_name"),
    LAST_NAME("last_name"),
    ADDRESS("address"),
    TELEPHONE("telephone"),
    DELETED("deleted"),


    ID_PASSPORT("id_passport"),
    INFO("info"),

    ID_SPEC("id_specialization"),
    DENIM("denomination"),
    NOTE("note"),
    DATE("date"),


    ID_VISIT("id_visit"),
    CLIENT_ID("client_id"),
    SUBS_ID("subscription_id"),
    SP_WORK("spwork_id"),

    ID_SPWORK("id_spwork"),
    WORKER_ID("worker_id"),
    SPEC_ID("specialization_id"),

    ID_SUBS("id_subscription"),
    TITILE("title"),
    PRICE("price"),
    NEMBER_VISITS("number_visits"),
    NEMBER_DAYS("number_days"),
    DATE_BEGIN("date_begin"),
    DATE_END("date_end"),
    DISC_ID("discount_id"),

    ID_DISC("id_discount");


    private String colum;

    DBVar(String colum) {
        this.colum = colum;
    }

    public String getVAr() {
        return colum;
    }
}



