--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2 (Ubuntu 12.2-2.pgdg16.04+1)
-- Dumped by pg_dump version 12.2

-- Started on 2020-04-05 17:44:52

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
-- TOC entry 202 (class 1259 OID 9642255)
-- Name: causes; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.causes (
    id bigint NOT NULL,
    description character varying(255)
);


ALTER TABLE public.causes OWNER TO ygmfurlunobgmc;

--
-- TOC entry 203 (class 1259 OID 9642260)
-- Name: city; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.city (
    id bigint NOT NULL,
    name character varying(255),
    state_id bigint
);


ALTER TABLE public.city OWNER TO ygmfurlunobgmc;

--
-- TOC entry 214 (class 1259 OID 9642324)
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
-- TOC entry 204 (class 1259 OID 9642265)
-- Name: skill; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.skill (
    id bigint NOT NULL,
    description character varying(255),
    name character varying(255)
);


ALTER TABLE public.skill OWNER TO ygmfurlunobgmc;

--
-- TOC entry 215 (class 1259 OID 9642326)
-- Name: skill_sequence; Type: SEQUENCE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE SEQUENCE public.skill_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.skill_sequence OWNER TO ygmfurlunobgmc;

--
-- TOC entry 205 (class 1259 OID 9642273)
-- Name: state; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.state (
    id bigint NOT NULL,
    name character varying(255),
    uf_code character varying(2)
);


ALTER TABLE public.state OWNER TO ygmfurlunobgmc;

--
-- TOC entry 216 (class 1259 OID 9642328)
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
-- TOC entry 206 (class 1259 OID 9642278)
-- Name: user_address; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.user_address (
    id bigint NOT NULL,
    address_number character varying(255) NOT NULL,
    cep character varying(255) NOT NULL,
    complement character varying(255),
    district character varying(255),
    street_name character varying(255),
    city_id bigint NOT NULL
);


ALTER TABLE public.user_address OWNER TO ygmfurlunobgmc;

--
-- TOC entry 217 (class 1259 OID 9642330)
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
-- TOC entry 207 (class 1259 OID 9642286)
-- Name: user_contributor; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.user_contributor (
    id bigint NOT NULL,
    password character varying(255),
    username character varying(255),
    allows_be_contacted_by_organization boolean DEFAULT false NOT NULL,
    contributor_name character varying(255),
    description character varying(255),
    email character varying(255),
    has_cnh boolean DEFAULT false NOT NULL,
    phone character varying(255),
    profile_picture oid,
    user_address_id bigint
);


ALTER TABLE public.user_contributor OWNER TO ygmfurlunobgmc;

--
-- TOC entry 208 (class 1259 OID 9642296)
-- Name: user_contributor_causes_that_support; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.user_contributor_causes_that_support (
    user_contributor_domain_id bigint NOT NULL,
    causes_that_support_id bigint NOT NULL
);


ALTER TABLE public.user_contributor_causes_that_support OWNER TO ygmfurlunobgmc;

--
-- TOC entry 209 (class 1259 OID 9642299)
-- Name: user_contributor_user_skills; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.user_contributor_user_skills (
    user_contributor_domain_id bigint NOT NULL,
    user_skills_id bigint NOT NULL
);


ALTER TABLE public.user_contributor_user_skills OWNER TO ygmfurlunobgmc;

--
-- TOC entry 210 (class 1259 OID 9642302)
-- Name: user_group; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.user_group (
    id bigint NOT NULL,
    password character varying(255),
    username character varying(255),
    description character varying(255),
    email character varying(255),
    group_mission character varying(255),
    group_name character varying(255),
    phone character varying(255),
    profile_picture oid,
    user_address_id bigint
);


ALTER TABLE public.user_group OWNER TO ygmfurlunobgmc;

--
-- TOC entry 211 (class 1259 OID 9642310)
-- Name: user_group_causes_that_support; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.user_group_causes_that_support (
    user_group_domain_id bigint NOT NULL,
    causes_that_support_id bigint NOT NULL
);


ALTER TABLE public.user_group_causes_that_support OWNER TO ygmfurlunobgmc;

--
-- TOC entry 212 (class 1259 OID 9642313)
-- Name: user_organization; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.user_organization (
    id bigint NOT NULL,
    password character varying(255),
    username character varying(255),
    cnpj character varying(255),
    description character varying(255),
    email character varying(255),
    organization_mission character varying(255),
    organization_name character varying(255),
    phone character varying(255),
    profile_picture oid,
    user_address_id bigint
);


ALTER TABLE public.user_organization OWNER TO ygmfurlunobgmc;

--
-- TOC entry 213 (class 1259 OID 9642321)
-- Name: user_organization_causes_that_support; Type: TABLE; Schema: public; Owner: ygmfurlunobgmc
--

CREATE TABLE public.user_organization_causes_that_support (
    user_organization_domain_id bigint NOT NULL,
    causes_that_support_id bigint NOT NULL
);


ALTER TABLE public.user_organization_causes_that_support OWNER TO ygmfurlunobgmc;

--
-- TOC entry 218 (class 1259 OID 9642332)
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
-- TOC entry 3917 (class 0 OID 9642255)
-- Dependencies: 202
-- Data for Name: causes; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.causes (id, description) FROM stdin;
\.


--
-- TOC entry 3918 (class 0 OID 9642260)
-- Dependencies: 203
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.city (id, name, state_id) FROM stdin;
\.


--
-- TOC entry 3919 (class 0 OID 9642265)
-- Dependencies: 204
-- Data for Name: skill; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.skill (id, description, name) FROM stdin;
\.


--
-- TOC entry 3920 (class 0 OID 9642273)
-- Dependencies: 205
-- Data for Name: state; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.state (id, name, uf_code) FROM stdin;
\.


--
-- TOC entry 3921 (class 0 OID 9642278)
-- Dependencies: 206
-- Data for Name: user_address; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.user_address (id, address_number, cep, complement, district, street_name, city_id) FROM stdin;
\.


--
-- TOC entry 3922 (class 0 OID 9642286)
-- Dependencies: 207
-- Data for Name: user_contributor; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.user_contributor (id, password, username, allows_be_contacted_by_organization, contributor_name, description, email, has_cnh, phone, profile_picture, user_address_id) FROM stdin;
\.


--
-- TOC entry 3923 (class 0 OID 9642296)
-- Dependencies: 208
-- Data for Name: user_contributor_causes_that_support; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.user_contributor_causes_that_support (user_contributor_domain_id, causes_that_support_id) FROM stdin;
\.


--
-- TOC entry 3924 (class 0 OID 9642299)
-- Dependencies: 209
-- Data for Name: user_contributor_user_skills; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.user_contributor_user_skills (user_contributor_domain_id, user_skills_id) FROM stdin;
\.


--
-- TOC entry 3925 (class 0 OID 9642302)
-- Dependencies: 210
-- Data for Name: user_group; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.user_group (id, password, username, description, email, group_mission, group_name, phone, profile_picture, user_address_id) FROM stdin;
\.


--
-- TOC entry 3926 (class 0 OID 9642310)
-- Dependencies: 211
-- Data for Name: user_group_causes_that_support; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.user_group_causes_that_support (user_group_domain_id, causes_that_support_id) FROM stdin;
\.


--
-- TOC entry 3927 (class 0 OID 9642313)
-- Dependencies: 212
-- Data for Name: user_organization; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.user_organization (id, password, username, cnpj, description, email, organization_mission, organization_name, phone, profile_picture, user_address_id) FROM stdin;
\.


--
-- TOC entry 3928 (class 0 OID 9642321)
-- Dependencies: 213
-- Data for Name: user_organization_causes_that_support; Type: TABLE DATA; Schema: public; Owner: ygmfurlunobgmc
--

COPY public.user_organization_causes_that_support (user_organization_domain_id, causes_that_support_id) FROM stdin;
\.


--
-- TOC entry 3940 (class 0 OID 0)
-- Dependencies: 214
-- Name: city_sequence; Type: SEQUENCE SET; Schema: public; Owner: ygmfurlunobgmc
--

SELECT pg_catalog.setval('public.city_sequence', 1, false);


--
-- TOC entry 3941 (class 0 OID 0)
-- Dependencies: 215
-- Name: skill_sequence; Type: SEQUENCE SET; Schema: public; Owner: ygmfurlunobgmc
--

SELECT pg_catalog.setval('public.skill_sequence', 1, false);


--
-- TOC entry 3942 (class 0 OID 0)
-- Dependencies: 216
-- Name: state_sequence; Type: SEQUENCE SET; Schema: public; Owner: ygmfurlunobgmc
--

SELECT pg_catalog.setval('public.state_sequence', 1, false);


--
-- TOC entry 3943 (class 0 OID 0)
-- Dependencies: 217
-- Name: user_address_sequence; Type: SEQUENCE SET; Schema: public; Owner: ygmfurlunobgmc
--

SELECT pg_catalog.setval('public.user_address_sequence', 1, false);


--
-- TOC entry 3944 (class 0 OID 0)
-- Dependencies: 218
-- Name: user_sequence; Type: SEQUENCE SET; Schema: public; Owner: ygmfurlunobgmc
--

SELECT pg_catalog.setval('public.user_sequence', 1, false);


--
-- TOC entry 3763 (class 2606 OID 9642259)
-- Name: causes causes_pkey; Type: CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.causes
    ADD CONSTRAINT causes_pkey PRIMARY KEY (id);


--
-- TOC entry 3765 (class 2606 OID 9642264)
-- Name: city city_pkey; Type: CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);


--
-- TOC entry 3767 (class 2606 OID 9642272)
-- Name: skill skill_pkey; Type: CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.skill
    ADD CONSTRAINT skill_pkey PRIMARY KEY (id);


--
-- TOC entry 3769 (class 2606 OID 9642277)
-- Name: state state_pkey; Type: CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.state
    ADD CONSTRAINT state_pkey PRIMARY KEY (id);


--
-- TOC entry 3771 (class 2606 OID 9642285)
-- Name: user_address user_address_pkey; Type: CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_address
    ADD CONSTRAINT user_address_pkey PRIMARY KEY (id);


--
-- TOC entry 3773 (class 2606 OID 9642295)
-- Name: user_contributor user_contributor_pkey; Type: CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_contributor
    ADD CONSTRAINT user_contributor_pkey PRIMARY KEY (id);


--
-- TOC entry 3775 (class 2606 OID 9642309)
-- Name: user_group user_group_pkey; Type: CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT user_group_pkey PRIMARY KEY (id);


--
-- TOC entry 3777 (class 2606 OID 9642320)
-- Name: user_organization user_organization_pkey; Type: CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_organization
    ADD CONSTRAINT user_organization_pkey PRIMARY KEY (id);


--
-- TOC entry 3784 (class 2606 OID 9642364)
-- Name: user_contributor_user_skills FK51f1xnxhnuadxa6iopl2ap0dh; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_contributor_user_skills
    ADD CONSTRAINT "FK51f1xnxhnuadxa6iopl2ap0dh" FOREIGN KEY (user_contributor_domain_id) REFERENCES public.user_contributor(id);


--
-- TOC entry 3786 (class 2606 OID 9642374)
-- Name: user_group_causes_that_support FK65441mn7yvfwwnl3yo9grupjp; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_group_causes_that_support
    ADD CONSTRAINT "FK65441mn7yvfwwnl3yo9grupjp" FOREIGN KEY (causes_that_support_id) REFERENCES public.causes(id);


--
-- TOC entry 3789 (class 2606 OID 9642389)
-- Name: user_organization_causes_that_support FKbfi3q03fpi6oe2w338gfkvvkk; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_organization_causes_that_support
    ADD CONSTRAINT "FKbfi3q03fpi6oe2w338gfkvvkk" FOREIGN KEY (causes_that_support_id) REFERENCES public.causes(id);


--
-- TOC entry 3787 (class 2606 OID 9642379)
-- Name: user_group_causes_that_support FKee6x4au2cjrlwk4s57xyy1pt4; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_group_causes_that_support
    ADD CONSTRAINT "FKee6x4au2cjrlwk4s57xyy1pt4" FOREIGN KEY (user_group_domain_id) REFERENCES public.user_group(id);


--
-- TOC entry 3788 (class 2606 OID 9642384)
-- Name: user_organization FKf8fbdtu5svef9syiol19rux2k; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_organization
    ADD CONSTRAINT "FKf8fbdtu5svef9syiol19rux2k" FOREIGN KEY (user_address_id) REFERENCES public.user_address(id);


--
-- TOC entry 3780 (class 2606 OID 9642344)
-- Name: user_contributor FKfb3pe8jwmqinri0044aqvnh11; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_contributor
    ADD CONSTRAINT "FKfb3pe8jwmqinri0044aqvnh11" FOREIGN KEY (user_address_id) REFERENCES public.user_address(id);


--
-- TOC entry 3790 (class 2606 OID 9642394)
-- Name: user_organization_causes_that_support FKfg0l9kdud12uj4b5eyevbt10c; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_organization_causes_that_support
    ADD CONSTRAINT "FKfg0l9kdud12uj4b5eyevbt10c" FOREIGN KEY (user_organization_domain_id) REFERENCES public.user_organization(id);


--
-- TOC entry 3779 (class 2606 OID 9642339)
-- Name: user_address FKfr03lndxyp40y5s18mb11qh20; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_address
    ADD CONSTRAINT "FKfr03lndxyp40y5s18mb11qh20" FOREIGN KEY (city_id) REFERENCES public.city(id);


--
-- TOC entry 3778 (class 2606 OID 9642334)
-- Name: city FKihijj5d7rtf9249lma9rr241y; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT "FKihijj5d7rtf9249lma9rr241y" FOREIGN KEY (state_id) REFERENCES public.state(id);


--
-- TOC entry 3781 (class 2606 OID 9642349)
-- Name: user_contributor_causes_that_support FKihvimj0ebfqqh68eei7wvpq87; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_contributor_causes_that_support
    ADD CONSTRAINT "FKihvimj0ebfqqh68eei7wvpq87" FOREIGN KEY (causes_that_support_id) REFERENCES public.causes(id);


--
-- TOC entry 3782 (class 2606 OID 9642354)
-- Name: user_contributor_causes_that_support FKkebfvp3byc0ln4yrfttqnai16; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_contributor_causes_that_support
    ADD CONSTRAINT "FKkebfvp3byc0ln4yrfttqnai16" FOREIGN KEY (user_contributor_domain_id) REFERENCES public.user_contributor(id);


--
-- TOC entry 3785 (class 2606 OID 9642369)
-- Name: user_group FKlt4l63qeetrbxnfu4hw2a9xyp; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT "FKlt4l63qeetrbxnfu4hw2a9xyp" FOREIGN KEY (user_address_id) REFERENCES public.user_address(id);


--
-- TOC entry 3783 (class 2606 OID 9642359)
-- Name: user_contributor_user_skills FKr8d08h114f02l7x6iltmc4p0v; Type: FK CONSTRAINT; Schema: public; Owner: ygmfurlunobgmc
--

ALTER TABLE ONLY public.user_contributor_user_skills
    ADD CONSTRAINT "FKr8d08h114f02l7x6iltmc4p0v" FOREIGN KEY (user_skills_id) REFERENCES public.skill(id);


--
-- TOC entry 3939 (class 0 OID 0)
-- Dependencies: 683
-- Name: LANGUAGE plpgsql; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON LANGUAGE plpgsql TO ygmfurlunobgmc;


-- Completed on 2020-04-05 17:45:17

--
-- PostgreSQL database dump complete
--

