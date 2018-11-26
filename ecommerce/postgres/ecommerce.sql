CREATE ROLE ecommerce WITH LOGIN ENCRYPTED PASSWORD 'ecommerce';



CREATE DATABASE ecommerce
    WITH
    OWNER = ecommerce
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Spain.1252'
    LC_CTYPE = 'Spanish_Spain.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;



CREATE TABLE public.categories
(
    id bigserial NOT NULL,
    published integer NOT NULL DEFAULT 0,
    name character varying(255) NOT NULL,
    icon character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now(),
    CONSTRAINT categories_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.categories
    OWNER to ecommerce;




CREATE TABLE public.products
(
    id bigserial NOT NULL,
    published integer NOT NULL DEFAULT 0,
    rating_cache double precision NOT NULL DEFAULT '3'::double precision,
    rating_count integer NOT NULL DEFAULT 0,
    category_id bigint NOT NULL,
    name character varying(255) NOT NULL,
    pricing double precision NOT NULL DEFAULT '0'::double precision,
    short_description character varying(255) NOT NULL,
    long_description text NOT NULL,
    icon character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now(),
    CONSTRAINT products_pkey PRIMARY KEY (id),
    CONSTRAINT products_category_id_fkey FOREIGN KEY (category_id)
        REFERENCES public.categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.products
    OWNER to ecommerce;



CREATE TABLE public.users
(
    id bigserial NOT NULL,
    user_type integer NOT NULL DEFAULT 0,
    username character varying(128) NOT NULL,
    email character varying(128) NOT NULL,
    password character varying(128) NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now(),
    CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to ecommerce;



CREATE TABLE public.reviews
(
    id bigserial NOT NULL,
    product_id bigint NOT NULL,
    user_id bigint NOT NULL,
    rating double precision NOT NULL,
    comment text NOT NULL,
    approved integer NOT NULL DEFAULT 1,
    spam integer NOT NULL DEFAULT 0,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now(),
    CONSTRAINT reviews_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.reviews
    OWNER to ecommerce;



CREATE TABLE public.purchases
(
    id bigserial NOT NULL,
    user_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now(),
    CONSTRAINT purchases_pkey PRIMARY KEY (id),
    CONSTRAINT purchases_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.purchases
    OWNER to ecommerce;



CREATE TABLE public.purchase_items
(
    id bigserial NOT NULL,
    purchase_id bigint NOT NULL,
    product_id bigint NOT NULL,
    price double precision NOT NULL,
    quantity double precision NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now(),
    CONSTRAINT purchase_items_pkey PRIMARY KEY (id),
    CONSTRAINT purchase_items_product_id_fkey FOREIGN KEY (product_id)
        REFERENCES public.products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT purchase_items_purchase_id_fkey FOREIGN KEY (purchase_id)
        REFERENCES public.purchases (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.purchase_items
    OWNER to ecommerce;



INSERT INTO categories ("published", "name", "icon") VALUES 
(1, 'Category One', 'category01.jpg'),
(1, 'Category Two', 'category02.jpg'),
(1, 'Category Three', 'category03.jpg'),
(1, 'Category Four', 'category04.jpg'),
(1, 'Category Five', 'category05.jpg');



INSERT INTO products ("published", "rating_cache", "rating_count", "category_id", "name", "pricing", "short_description", "long_description", "icon") VALUES
(1, 3.0, 0, 1, 'Second product', 55.00, 'This is a short description', 'Lorem ipsum dolor ...', 'product02.jpg'),
(1, 3.0, 0, 1, 'Third product', 65.00, 'This is a short description', 'Lorem ipsum dolor ...', 'product03.jpg'),
(1, 3.0, 0, 1, 'Fourth product', 85.00, 'This is a short description', 'Lorem ipsum dolor ...', 'product04.jpg'),
(1, 3.0, 0, 1, 'Fifth product', 95.00, 'This is a short description', 'Lorem ipsum dolor ...', 'product05.jpg'),
(1, 3.0, 0, 2, 'Product 06', 35.00, 'This is a short description', 'Lorem ipsum dolor ...', 'product06.jpg'),
(1, 3.0, 0, 2, 'Product 07', 45.00, 'This is a short description', 'Lorem ipsum dolor ...', 'product07.jpg'),
(1, 3.0, 0, 3, 'Product 08', 52.00, 'This is a short description', 'Lorem ipsum dolor ...', 'product08.jpg'),
(1, 3.0, 0, 3, 'Product 09', 62.00, 'This is a short description', 'Lorem ipsum dolor ...', 'product09.jpg'),
(1, 3.0, 0, 4, 'Product 10', 14.00, 'This is a short description', 'Lorem ipsum dolor ...', 'product10.jpg'),
(1, 3.0, 0, 4, 'Product 11', 18.00, 'This is a short description', 'Lorem ipsum dolor ...', 'product11.jpg'),
(1, 3.0, 0, 5, 'Product 12', 40.00, 'This is a short description', 'Lorem ipsum dolor ...', 'product12.jpg'),
(1, 3.0, 0, 5, 'Product 13', 44.00, 'This is a short description', 'Lorem ipsum dolor ...', 'product13.jpg');



UPDATE products SET long_description = 'Lorem ipsum dolor sit amet, 
consectetur adipisicing elit, sed do eiusmod tempor incididunt ut 
labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
Duis aute irure dolor in reprehenderit in voluptate velit esse cillum 
dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non 
proident, sunt in culpa qui officia deserunt mollit anim id est laborum';



INSERT INTO users ("username", "email", "password") VALUES 
('jose', 'jose.lopez@gmail.com', 'lopez2018'),
('maria', 'maria.quintero@gmail.com', 'quintero2018'),
('pedro', 'pedro.gutierrez@gmail.com', 'gutierrez2018');



INSERT INTO reviews ("product_id", "user_id", "rating", "comment") VALUES
(1, 1, 4.5, 'Comments 001'),
(1, 2, 3.5, 'Comments 002'),
(1, 3, 4.0, 'Comments 003'),
(2, 1, 2.5, 'Comments 004'),
(2, 2, 3.5, 'Comments 005'),
(2, 3, 3.0, 'Comments 006'),
(3, 1, 4.0, 'Comments 007'),
(3, 2, 3.5, 'Comments 008'),
(3, 3, 2.0, 'Comments 009'),
(4, 1, 5.0, 'Comments 010'),
(4, 2, 3.0, 'Comments 011'),
(4, 3, 3.5, 'Comments 012'),
(5, 1, 3.0, 'Comments 013'),
(5, 2, 4.5, 'Comments 014'),
(5, 3, 4.0, 'Comments 015');

