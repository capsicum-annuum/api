--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2 (Ubuntu 12.2-2.pgdg16.04+1)
-- Dumped by pg_dump version 12.2

-- Started on 2020-04-05 02:18:31

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 208 (class 1259 OID 9381335)
-- Name: causes; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.causes (
    id bigint NOT NULL,
    description character varying(255)
);


ALTER TABLE public.causes OWNER TO ygmfurlunobgmc;

--
-- TOC entry 209 (class 1259 OID 9381340)
-- Name: city; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.city (
    id bigint NOT NULL,
    name character varying(255),
    state_id bigint
);


ALTER TABLE public.city OWNER TO ygmfurlunobgmc;

--
-- TOC entry 202 (class 1259 OID 9381323)
-- Name: city_sequence; Type: SEQUENCE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE SEQUENCE public.city_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.city_sequence OWNER TO ygmfurlunobgmc;

--
-- TOC entry 210 (class 1259 OID 9381345)
-- Name: federatedUnity; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.state (
    id bigint NOT NULL,
    name character varying(255),
    uf_code character varying(255)
);


ALTER TABLE public.state OWNER TO ygmfurlunobgmc;

--
-- TOC entry 203 (class 1259 OID 9381325)
-- Name: state_sequence; Type: SEQUENCE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE SEQUENCE public.state_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.state_sequence OWNER TO ygmfurlunobgmc;

--
-- TOC entry 211 (class 1259 OID 9381353)
-- Name: user; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public."user" (
    id bigint NOT NULL,
    description character varying(255),
    email character varying(255),
    name character varying(255),
    phone character varying(255),
    user_image oid,
    user_type integer,
    user_address_id bigint
);


ALTER TABLE public."user" OWNER TO ygmfurlunobgmc;

--
-- TOC entry 212 (class 1259 OID 9381361)
-- Name: user_address; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.user_address (
    id bigint NOT NULL,
    address_number character varying(255),
    cep character varying(255),
    complement character varying(255),
    district character varying(255),
    street_name character varying(255),
    city_id bigint
);


ALTER TABLE public.user_address OWNER TO ygmfurlunobgmc;

--
-- TOC entry 204 (class 1259 OID 9381327)
-- Name: user_address_sequence; Type: SEQUENCE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE SEQUENCE public.user_address_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_address_sequence OWNER TO ygmfurlunobgmc;

--
-- TOC entry 213 (class 1259 OID 9381369)
-- Name: user_causes; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.user_causes (
    user_domain_id bigint NOT NULL,
    causes_id bigint NOT NULL
);


ALTER TABLE public.user_causes OWNER TO ygmfurlunobgmc;

--
-- TOC entry 214 (class 1259 OID 9381372)
-- Name: user_contributor; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.user_contributor (
    allows_be_contacted_by_organization boolean,
    user_id bigint NOT NULL
);


ALTER TABLE public.user_contributor OWNER TO ygmfurlunobgmc;

--
-- TOC entry 215 (class 1259 OID 9381377)
-- Name: user_contributor_skills; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.user_contributor_skills (
    user_contributor_domain_user_id bigint NOT NULL,
    skills_id bigint NOT NULL
);


ALTER TABLE public.user_contributor_skills OWNER TO ygmfurlunobgmc;

--
-- TOC entry 216 (class 1259 OID 9381380)
-- Name: user_login; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.user_login (
    id bigint NOT NULL,
    password character varying(255),
    username character varying(255)
);


ALTER TABLE public.user_login OWNER TO ygmfurlunobgmc;

--
-- TOC entry 205 (class 1259 OID 9381329)
-- Name: user_login_sequence; Type: SEQUENCE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE SEQUENCE public.user_login_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_login_sequence OWNER TO ygmfurlunobgmc;

--
-- TOC entry 217 (class 1259 OID 9381388)
-- Name: user_organization; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.user_organization (
    cnpj character varying(255),
    user_id bigint NOT NULL
);


ALTER TABLE public.user_organization OWNER TO ygmfurlunobgmc;

--
-- TOC entry 206 (class 1259 OID 9381331)
-- Name: user_sequence; Type: SEQUENCE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE SEQUENCE public.user_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_sequence OWNER TO ygmfurlunobgmc;

--
-- TOC entry 218 (class 1259 OID 9381393)
-- Name: user_skill; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.user_skill (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.user_skill OWNER TO ygmfurlunobgmc;

--
-- TOC entry 207 (class 1259 OID 9381333)
-- Name: user_skill_sequence; Type: SEQUENCE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE SEQUENCE public.user_skill_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_skill_sequence OWNER TO ygmfurlunobgmc;

--
-- TOC entry 3916 (class 0 OID 9381335)
-- Dependencies: 208
-- Data for Name: causes; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.causes (id, description) FROM stdin;
\.


--
-- TOC entry 3917 (class 0 OID 9381340)
-- Dependencies: 209
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.city (id, name, state_id) FROM stdin;
\.


--
-- TOC entry 3918 (class 0 OID 9381345)
-- Dependencies: 210
-- Data for Name: federatedUnity; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.federatedUnity (id, name, uf_code) FROM stdin;
\.


--
-- TOC entry 3919 (class 0 OID 9381353)
-- Dependencies: 211
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public."user" (id, description, email, name, phone, user_image, user_type, user_address_id) FROM stdin;
\.


--
-- TOC entry 3920 (class 0 OID 9381361)
-- Dependencies: 212
-- Data for Name: user_address; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.user_address (id, address_number, cep, complement, district, street_name, city_id) FROM stdin;
\.


--
-- TOC entry 3921 (class 0 OID 9381369)
-- Dependencies: 213
-- Data for Name: user_causes; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.user_causes (user_domain_id, causes_id) FROM stdin;
\.


--
-- TOC entry 3922 (class 0 OID 9381372)
-- Dependencies: 214
-- Data for Name: user_contributor; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.user_contributor (allows_be_contacted_by_organization, user_id) FROM stdin;
\.


--
-- TOC entry 3923 (class 0 OID 9381377)
-- Dependencies: 215
-- Data for Name: user_contributor_skills; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.user_contributor_skills (user_contributor_domain_user_id, skills_id) FROM stdin;
\.


--
-- TOC entry 3924 (class 0 OID 9381380)
-- Dependencies: 216
-- Data for Name: user_login; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.user_login (id, password, username) FROM stdin;
\.


--
-- TOC entry 3925 (class 0 OID 9381388)
-- Dependencies: 217
-- Data for Name: user_organization; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.user_organization (cnpj, user_id) FROM stdin;
\.


--
-- TOC entry 3926 (class 0 OID 9381393)
-- Dependencies: 218
-- Data for Name: user_skill; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.user_skill (id, name) FROM stdin;
\.


--
-- TOC entry 3934 (class 0 OID 0)
-- Dependencies: 202
-- Name: city_sequence; Type: SEQUENCE SET; Schema: public; Owner: ygmfurlunobgmc
--

SELECT pg_catalog.setval('public.city_sequence', 1, false);


--
-- TOC entry 3935 (class 0 OID 0)
-- Dependencies: 203
-- Name: state_sequence; Type: SEQUENCE SET; Schema: public; Owner: ygmfurlunobgmc
--

SELECT pg_catalog.setval('public.state_sequence', 1, false);


--
-- TOC entry 3936 (class 0 OID 0)
-- Dependencies: 204
-- Name: user_address_sequence; Type: SEQUENCE SET; Schema: public; Owner: ygmfurlunobgmc
--

SELECT pg_catalog.setval('public.user_address_sequence', 1, false);


--
-- TOC entry 3937 (class 0 OID 0)
-- Dependencies: 205
-- Name: user_login_sequence; Type: SEQUENCE SET; Schema: public; Owner: ygmfurlunobgmc
--

SELECT pg_catalog.setval('public.user_login_sequence', 1, false);


--
-- TOC entry 3938 (class 0 OID 0)
-- Dependencies: 206
-- Name: user_sequence; Type: SEQUENCE SET; Schema: public; Owner: ygmfurlunobgmc
--

SELECT pg_catalog.setval('public.user_sequence', 1, false);


--
-- TOC entry 3939 (class 0 OID 0)
-- Dependencies: 207
-- Name: user_skill_sequence; Type: SEQUENCE SET; Schema: public; Owner: ygmfurlunobgmc
--

SELECT pg_catalog.setval('public.user_skill_sequence', 1, false);


--
-- TOC entry 3758 (class 2606 OID 9381339)
-- Name: causes causes_pkey; Type: CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.causes
    ADD CONSTRAINT causes_pkey PRIMARY KEY (id);


--
-- TOC entry 3760 (class 2606 OID 9381344)
-- Name: city city_pkey; Type: CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);


--
-- TOC entry 3762 (class 2606 OID 9381352)
-- Name: federatedUnity state_pkey; Type: CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.federatedUnity
    ADD CONSTRAINT state_pkey PRIMARY KEY (id);


--
-- TOC entry 3766 (class 2606 OID 9381368)
-- Name: user_address user_address_pkey; Type: CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_address
    ADD CONSTRAINT user_address_pkey PRIMARY KEY (id);


--
-- TOC entry 3768 (class 2606 OID 9381376)
-- Name: user_contributor user_contributor_pkey; Type: CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_contributor
    ADD CONSTRAINT user_contributor_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3770 (class 2606 OID 9381387)
-- Name: user_login user_login_pkey; Type: CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_login
    ADD CONSTRAINT user_login_pkey PRIMARY KEY (id);


--
-- TOC entry 3772 (class 2606 OID 9381392)
-- Name: user_organization user_organization_pkey; Type: CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_organization
    ADD CONSTRAINT user_organization_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3764 (class 2606 OID 9381360)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 3774 (class 2606 OID 9381397)
-- Name: user_skill user_skill_pkey; Type: CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_skill
    ADD CONSTRAINT user_skill_pkey PRIMARY KEY (id);


--
-- TOC entry 3778 (class 2606 OID 9381413)
-- Name: user_causes FK6ypqsedihfgdwer3s3ro33hfi; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_causes
    ADD CONSTRAINT "FK6ypqsedihfgdwer3s3ro33hfi" FOREIGN KEY (causes_id) REFERENCES public.causes(id);


--
-- TOC entry 3783 (class 2606 OID 9381438)
-- Name: user_organization FKaml2v7lrdhhe8jdbbyagkc0mu; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_organization
    ADD CONSTRAINT "FKaml2v7lrdhhe8jdbbyagkc0mu" FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- TOC entry 3777 (class 2606 OID 9381408)
-- Name: user_address FKfr03lndxyp40y5s18mb11qh20; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_address
    ADD CONSTRAINT "FKfr03lndxyp40y5s18mb11qh20" FOREIGN KEY (city_id) REFERENCES public.city(id);


--
-- TOC entry 3775 (class 2606 OID 9381398)
-- Name: city FKihijj5d7rtf9249lma9rr241y; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT "FKihijj5d7rtf9249lma9rr241y" FOREIGN KEY (state_id) REFERENCES public.federatedUnity(id);


--
-- TOC entry 3782 (class 2606 OID 9381433)
-- Name: user_contributor_skills FKldx5jtaak1oytbrh3g7qanykd; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_contributor_skills
    ADD CONSTRAINT "FKldx5jtaak1oytbrh3g7qanykd" FOREIGN KEY (user_contributor_domain_user_id) REFERENCES public.user_contributor(user_id);


--
-- TOC entry 3781 (class 2606 OID 9381428)
-- Name: user_contributor_skills FKm6cjb6874xrkgts8sybf1uks0; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_contributor_skills
    ADD CONSTRAINT "FKm6cjb6874xrkgts8sybf1uks0" FOREIGN KEY (skills_id) REFERENCES public.user_skill(id);


--
-- TOC entry 3780 (class 2606 OID 9381423)
-- Name: user_contributor FKoa0ollhrwcr6ohnkiacmwpegl; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_contributor
    ADD CONSTRAINT "FKoa0ollhrwcr6ohnkiacmwpegl" FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- TOC entry 3776 (class 2606 OID 9381403)
-- Name: user FKp655boqb9o01mxbed6peb2jvi; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT "FKp655boqb9o01mxbed6peb2jvi" FOREIGN KEY (user_address_id) REFERENCES public.user_address(id);


--
-- TOC entry 3779 (class 2606 OID 9381418)
-- Name: user_causes FKq8pnhlpvvkm56dchqj9ovcpdc; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_causes
    ADD CONSTRAINT "FKq8pnhlpvvkm56dchqj9ovcpdc" FOREIGN KEY (user_domain_id) REFERENCES public."user"(id);


--
-- TOC entry 3932 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: ygmfurlunobgmc
--

REVOKE ALL ON SCHEMA public FROM postgres;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO ygmfurlunobgmc;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- TOC entry 3933 (class 0 OID 0)
-- Dependencies: 680
-- Name: LANGUAGE plpgsql; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON LANGUAGE plpgsql TO ygmfurlunobgmc;


-- Completed on 2020-04-05 02:18:57

--
-- PostgreSQL database dump complete
--

