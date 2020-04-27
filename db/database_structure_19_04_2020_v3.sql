--DROP SCHEMA public cascade;
--
CREATE SCHEMA public AUTHORIZATION OWNER_NAME;
--
--COMMENT ON SCHEMA public IS 'standard public schema';
--
--
-- OWNER_NAMEQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-04-21 23:23:36

CREATE TABLE public.address (
    id bigint NOT NULL,
    latitude double precision,
    longitude double precision,
    address_number character varying(255),
    complement character varying(255),
    district character varying(255),
    google_place_address_id character varying(255),
    street_name character varying(255),
    city_id bigint NOT NULL
);


ALTER TABLE public.address OWNER TO OWNER_NAME;


CREATE SEQUENCE public.address_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 20;


ALTER TABLE public.address_sequence OWNER TO OWNER_NAME;


CREATE TABLE public.cause (
    id bigint NOT NULL,
    binary_identifier integer,
    description character varying(255)
);


ALTER TABLE public.cause OWNER TO OWNER_NAME;


CREATE SEQUENCE public.cause_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 20;


ALTER TABLE public.cause_sequence OWNER TO OWNER_NAME;


CREATE TABLE public.city (
    id bigint NOT NULL,
    google_place_city_id character varying(255),
    name character varying(255),
    state character varying(255) NOT NULL
);


ALTER TABLE public.city OWNER TO OWNER_NAME;


CREATE SEQUENCE public.city_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 20;


ALTER TABLE public.city_sequence OWNER TO OWNER_NAME;


CREATE TABLE public.skill (
    id bigint NOT NULL,
    binary_identifier integer,
    description character varying(255),
    name character varying(255)
);


ALTER TABLE public.skill OWNER TO OWNER_NAME;


CREATE SEQUENCE public.skill_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 20;


ALTER TABLE public.skill_sequence OWNER TO OWNER_NAME;


CREATE TABLE public.user_group (
    id bigint NOT NULL,
    created_at timestamp with time zone NOT NULL default current_timestamp,
    email character varying(255),
    name character varying(255),
    password character varying(255),
    latitude double precision,
    longitude double precision,
    description character varying(255),
    mission character varying(255),
    phone character varying(255),
    profile_picture_id bigint,
    address_id bigint NOT NULL
);


ALTER TABLE public.user_group OWNER TO OWNER_NAME;


CREATE TABLE public.user_group_cause_that_support (
    user_group_id bigint NOT NULL,
    cause_that_support_id bigint NOT NULL
);


ALTER TABLE public.user_group_cause_that_support OWNER TO OWNER_NAME;


CREATE TABLE public.user_organization (
    id bigint NOT NULL,
    created_at timestamp with time zone NOT NULL default current_timestamp,
    email character varying(255),
    name character varying(255),
    password character varying(255),
    latitude double precision,
    longitude double precision,
    cnpj character varying(255),
    description character varying(255),
    mission character varying(255),
    phone character varying(255),
    profile_picture_id bigint,
    address_id bigint NOT NULL
);


ALTER TABLE public.user_organization OWNER TO OWNER_NAME;


CREATE TABLE public.user_organization_cause_that_support (
    user_organization_id bigint NOT NULL,
    cause_that_support_id bigint NOT NULL
);


ALTER TABLE public.user_organization_cause_that_support OWNER TO OWNER_NAME;


CREATE SEQUENCE public.user_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 20;


ALTER TABLE public.user_sequence OWNER TO OWNER_NAME;


CREATE TABLE public.user_volunteer (
    id bigint NOT NULL,
    created_at timestamp with time zone NOT NULL default current_timestamp,
    email character varying(255),
    skill_match_code character varying(2048),
    cause_match_code character varying(2048),
    name character varying(255),
    password character varying(255),
    latitude double precision,
    longitude double precision,
    availability character varying(255),
    description character varying(255),
    has_cnh boolean DEFAULT false,
    phone character varying(255),
    profile_picture_id bigint,
    address_id bigint NOT NULL
);


ALTER TABLE public.user_volunteer OWNER TO OWNER_NAME;


CREATE TABLE public.user_volunteer_cause_that_support (
    user_volunteer_id bigint NOT NULL,
    cause_that_support_id bigint NOT NULL
);


ALTER TABLE public.user_volunteer_cause_that_support OWNER TO OWNER_NAME;


CREATE TABLE public.user_volunteer_user_skills (
    user_volunteer_id bigint NOT NULL,
    user_skills_id bigint NOT NULL
);


ALTER TABLE public.user_volunteer_user_skills OWNER TO OWNER_NAME;


ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.cause
    ADD CONSTRAINT cause_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.skill
    ADD CONSTRAINT skill_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT user_group_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.user_organization
    ADD CONSTRAINT user_organization_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.user_volunteer
    ADD CONSTRAINT user_volunteer_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.user_group_cause_that_support
    ADD CONSTRAINT user_group_id_user_group_cause_that_support_fkey FOREIGN KEY (user_group_id) REFERENCES public.user_group(id);


ALTER TABLE ONLY public.user_volunteer
    ADD CONSTRAINT address_id_user_volunteer_fkey FOREIGN KEY (address_id) REFERENCES public.address(id);


ALTER TABLE ONLY public.user_volunteer_cause_that_support
    ADD CONSTRAINT cause_id_user_volunteer_cause_that_support_fkey FOREIGN KEY (cause_that_support_id) REFERENCES public.cause(id);


ALTER TABLE ONLY public.user_organization_cause_that_support
    ADD CONSTRAINT cause_id_user_organization_cause_that_support_fkey FOREIGN KEY (cause_that_support_id) REFERENCES public.cause(id);


ALTER TABLE ONLY public.user_group_cause_that_support
    ADD CONSTRAINT cause_id_user_group_cause_that_support_fkey FOREIGN KEY (cause_that_support_id) REFERENCES public.cause(id);


ALTER TABLE ONLY public.user_volunteer_user_skills
    ADD CONSTRAINT user_volunteer_id_user_volunteer_user_skills_fkey FOREIGN KEY (user_volunteer_id) REFERENCES public.user_volunteer(id);


ALTER TABLE ONLY public.user_organization
    ADD CONSTRAINT address_id_user_organization_fkey FOREIGN KEY (address_id) REFERENCES public.address(id);


ALTER TABLE ONLY public.user_volunteer_cause_that_support
    ADD CONSTRAINT user_volunteer_id_user_volunteer_cause_that_support_fkey FOREIGN KEY (user_volunteer_id) REFERENCES public.user_volunteer(id);


ALTER TABLE ONLY public.address
    ADD CONSTRAINT city_id_address_fkey FOREIGN KEY (city_id) REFERENCES public.city(id);


ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT address_id_user_group_fkey FOREIGN KEY (address_id) REFERENCES public.address(id);


ALTER TABLE ONLY public.user_organization_cause_that_support
    ADD CONSTRAINT user_organization_id_user_organization_cause_that_support_fkey FOREIGN KEY (user_organization_id) REFERENCES public.user_organization(id);


ALTER TABLE ONLY public.user_volunteer_user_skills
    ADD CONSTRAINT skill_id_user_volunteer_user_skills_fkey FOREIGN KEY (user_skills_id) REFERENCES public.skill(id);
