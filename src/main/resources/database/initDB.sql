CREATE SEQUENCE public.stock_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE TABLE public.stock (
                              id numeric NOT NULL DEFAULT nextval('stock_id_seq'::regclass),
                              "name" varchar NOT NULL,
                              CONSTRAINT stock_pk PRIMARY KEY (id)
);


CREATE SEQUENCE public.product_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE TABLE public.product (
                                id numeric NOT NULL DEFAULT nextval('product_id_seq'::regclass),
                                art numeric(8) NOT NULL,
                                "name" varchar NOT NULL,
                                last_purch numeric(8, 2) NULL,
                                last_sale numeric(8, 2) NULL,
                                stock_id numeric NULL,
                                CONSTRAINT product_pk PRIMARY KEY (id)
);

CREATE SEQUENCE public.prod_on_stock_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE TABLE public.prod_on_stock (
                                      id numeric NOT NULL DEFAULT nextval('prod_on_stock_id_seq'::regclass),
                                      prod_id numeric NOT NULL,
                                      stock_id numeric NOT NULL,
                                      count numeric(4) NOT NULL,
                                      CONSTRAINT prod_on_stock_pk PRIMARY KEY (id),
                                      CONSTRAINT prod_on_stock_fk FOREIGN KEY (prod_id) REFERENCES public.product(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                      CONSTRAINT prod_on_stock_fk_1 FOREIGN KEY (stock_id) REFERENCES public.stock(id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE SEQUENCE public.users_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE TABLE public."users" (
                               id numeric NOT NULL DEFAULT nextval('users_id_seq'::regclass),
                               username varchar NOT NULL,
                               "password" varchar NOT NULL,
                               CONSTRAINT users_pk PRIMARY KEY (id)
);

