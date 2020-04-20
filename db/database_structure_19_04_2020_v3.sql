--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-04-19 00:19:15

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'WIN1252';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 2916 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 208 (class 1259 OID 40970)
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

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


ALTER TABLE public.address OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 40960)
-- Name: address_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.address_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.address_sequence OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 40978)
-- Name: cause; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cause (
    id bigint NOT NULL,
    description character varying(255)
);


ALTER TABLE public.cause OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 40962)
-- Name: cause_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cause_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cause_sequence OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 40983)
-- Name: city; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.city (
    id bigint NOT NULL,
    google_place_city_id character varying(255),
    name character varying(255),
    state character varying(255) NOT NULL
);


ALTER TABLE public.city OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 40964)
-- Name: city_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.city_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.city_sequence OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 40991)
-- Name: skill; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.skill (
    id bigint NOT NULL,
    description character varying(255),
    name character varying(255)
);


ALTER TABLE public.skill OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 40966)
-- Name: skill_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.skill_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.skill_sequence OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 40999)
-- Name: user_group; Type: TABLE; Schema: public; Owner: postgres
--

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
    address_id bigint NOT NULL,
    match_code character varying(2048)
);


ALTER TABLE public.user_group OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 41007)
-- Name: user_group_cause_that_support; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_group_cause_that_support (
    user_group_id bigint NOT NULL,
    cause_that_support_id bigint NOT NULL
);


ALTER TABLE public.user_group_cause_that_support OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 41010)
-- Name: user_organization; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_organization (
    id bigint NOT NULL,
    created_at timestamp with time zone NOT NULL default current_timestamp
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
    address_id bigint NOT NULL,
    match_code character varying(2048)
);


ALTER TABLE public.user_organization OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 41018)
-- Name: user_organization_cause_that_support; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_organization_cause_that_support (
    user_organization_id bigint NOT NULL,
    cause_that_support_id bigint NOT NULL
);


ALTER TABLE public.user_organization_cause_that_support OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 40968)
-- Name: user_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_sequence OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 41021)
-- Name: user_volunteer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_volunteer (
    id bigint NOT NULL,
    created_at timestamp with time zone NOT NULL default current_timestamp
    email character varying(255),
    name character varying(255),
    password character varying(255),
    latitude double precision,
    longitude double precision,
    description character varying(255),
    has_cnh boolean DEFAULT false NOT NULL,
    phone character varying(255),
    profile_picture_id bigint,
    address_id bigint NOT NULL,
    match_code character varying(2048)
);


ALTER TABLE public.user_volunteer OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 41030)
-- Name: user_volunteer_cause_that_support; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_volunteer_cause_that_support (
    user_volunteer_id bigint NOT NULL,
    cause_that_support_id bigint NOT NULL
);


ALTER TABLE public.user_volunteer_cause_that_support OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 41033)
-- Name: user_volunteer_user_skills; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_volunteer_user_skills (
    user_volunteer_id bigint NOT NULL,
    user_skills_id bigint NOT NULL
);


ALTER TABLE public.user_volunteer_user_skills OWNER TO postgres;


--
-- TOC entry 2917 (class 0 OID 0)
-- Dependencies: 203
-- Name: address_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.address_sequence', 1, false);


--
-- TOC entry 2918 (class 0 OID 0)
-- Dependencies: 204
-- Name: cause_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cause_sequence', 1, false);


--
-- TOC entry 2919 (class 0 OID 0)
-- Dependencies: 205
-- Name: city_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.city_sequence', 1, false);


--
-- TOC entry 2920 (class 0 OID 0)
-- Dependencies: 206
-- Name: skill_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.skill_sequence', 1, false);


--
-- TOC entry 2921 (class 0 OID 0)
-- Dependencies: 207
-- Name: user_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_sequence', 1, false);


--
-- TOC entry 2744 (class 2606 OID 40977)
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- TOC entry 2746 (class 2606 OID 40982)
-- Name: cause cause_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cause
    ADD CONSTRAINT cause_pkey PRIMARY KEY (id);


--
-- TOC entry 2748 (class 2606 OID 40990)
-- Name: city city_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);


--
-- TOC entry 2750 (class 2606 OID 40998)
-- Name: skill skill_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.skill
    ADD CONSTRAINT skill_pkey PRIMARY KEY (id);


--
-- TOC entry 2752 (class 2606 OID 41006)
-- Name: user_group user_group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT user_group_pkey PRIMARY KEY (id);


--
-- TOC entry 2754 (class 2606 OID 41017)
-- Name: user_organization user_organization_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_organization
    ADD CONSTRAINT user_organization_pkey PRIMARY KEY (id);


--
-- TOC entry 2756 (class 2606 OID 41029)
-- Name: user_volunteer user_volunteer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_volunteer
    ADD CONSTRAINT user_volunteer_pkey PRIMARY KEY (id);


--
-- TOC entry 2760 (class 2606 OID 41051)
-- Name: user_group_cause_that_support user_group_id_user_group_cause_that_support_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group_cause_that_support
    ADD CONSTRAINT user_group_id_user_group_cause_that_support_fkey FOREIGN KEY (user_group_id) REFERENCES public.user_group(id);


--
-- TOC entry 2764 (class 2606 OID 41071)
-- Name: user_volunteer address_id_user_volunteer_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_volunteer
    ADD CONSTRAINT address_id_user_volunteer_fkey FOREIGN KEY (address_id) REFERENCES public.address(id);


--
-- TOC entry 2765 (class 2606 OID 41076)
-- Name: user_volunteer_cause_that_support cause_id_user_volunteer_cause_that_support_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_volunteer_cause_that_support
    ADD CONSTRAINT cause_id_user_volunteer_cause_that_support_fkey FOREIGN KEY (cause_that_support_id) REFERENCES public.cause(id);


--
-- TOC entry 2762 (class 2606 OID 41061)
-- Name: user_organization_cause_that_support cause_id_user_organization_cause_that_support_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_organization_cause_that_support
    ADD CONSTRAINT cause_id_user_organization_cause_that_support_fkey FOREIGN KEY (cause_that_support_id) REFERENCES public.cause(id);


--
-- TOC entry 2759 (class 2606 OID 41046)
-- Name: user_group_cause_that_support cause_id_user_group_cause_that_support_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group_cause_that_support
    ADD CONSTRAINT cause_id_user_group_cause_that_support_fkey FOREIGN KEY (cause_that_support_id) REFERENCES public.cause(id);


--
-- TOC entry 2768 (class 2606 OID 41091)
-- Name: user_volunteer_user_skills user_volunteer_id_user_volunteer_user_skills_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_volunteer_user_skills
    ADD CONSTRAINT user_volunteer_id_user_volunteer_user_skills_fkey FOREIGN KEY (user_volunteer_id) REFERENCES public.user_volunteer(id);


--
-- TOC entry 2761 (class 2606 OID 41056)
-- Name: user_organization address_id_user_organization_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_organization
    ADD CONSTRAINT address_id_user_organization_fkey FOREIGN KEY (address_id) REFERENCES public.address(id);


--
-- TOC entry 2766 (class 2606 OID 41081)
-- Name: user_volunteer_cause_that_support user_volunteer_id_user_volunteer_cause_that_support_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_volunteer_cause_that_support
    ADD CONSTRAINT user_volunteer_id_user_volunteer_cause_that_support_fkey FOREIGN KEY (user_volunteer_id) REFERENCES public.user_volunteer(id);


--
-- TOC entry 2757 (class 2606 OID 41036)
-- Name: address city_id_address_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT city_id_address_fkey FOREIGN KEY (city_id) REFERENCES public.city(id);


--
-- TOC entry 2758 (class 2606 OID 41041)
-- Name: user_group address_id_user_group_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT address_id_user_group_fkey FOREIGN KEY (address_id) REFERENCES public.address(id);


--
-- TOC entry 2763 (class 2606 OID 41066)
-- Name: user_organization_cause_that_support user_organization_id_user_organization_cause_that_support_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_organization_cause_that_support
    ADD CONSTRAINT user_organization_id_user_organization_cause_that_support_fkey FOREIGN KEY (user_organization_id) REFERENCES public.user_organization(id);


--
-- TOC entry 2767 (class 2606 OID 41086)
-- Name: user_volunteer_user_skills skill_id_user_volunteer_user_skills_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_volunteer_user_skills
    ADD CONSTRAINT skill_id_user_volunteer_user_skills_fkey FOREIGN KEY (user_skills_id) REFERENCES public.skill(id);


-- Completed on 2020-04-19 00:19:15

--
-- PostgreSQL database dump complete
--