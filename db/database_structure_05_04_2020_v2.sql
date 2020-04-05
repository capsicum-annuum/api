PGDMP     
    ;                x            d3a9nc2vv077kl     12.2 (Ubuntu 12.2-2.pgdg16.04+1)    12.2 =    `           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            a           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            b           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            c           1262    9092334    d3a9nc2vv077kl    DATABASE     �   CREATE DATABASE d3a9nc2vv077kl WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';
    DROP DATABASE d3a9nc2vv077kl;
                ygmfurlunobgmc    false            d           0    0    DATABASE d3a9nc2vv077kl    ACL     A   REVOKE CONNECT,TEMPORARY ON DATABASE d3a9nc2vv077kl FROM PUBLIC;
                   ygmfurlunobgmc    false    3939            e           0    0    LANGUAGE plpgsql    ACL     1   GRANT ALL ON LANGUAGE plpgsql TO ygmfurlunobgmc;
                   postgres    false    683            �            1259    9646515    causes    TABLE     _   CREATE TABLE public.causes (
    id bigint NOT NULL,
    description character varying(255)
);
    DROP TABLE public.causes;
       public         heap    ygmfurlunobgmc    false            �            1259    9646520    city    TABLE     k   CREATE TABLE public.city (
    id bigint NOT NULL,
    name character varying(255),
    state_id bigint
);
    DROP TABLE public.city;
       public         heap    ygmfurlunobgmc    false            �            1259    9646505    city_sequence    SEQUENCE     w   CREATE SEQUENCE public.city_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.city_sequence;
       public          ygmfurlunobgmc    false            �            1259    9646525    skill    TABLE        CREATE TABLE public.skill (
    id bigint NOT NULL,
    description character varying(255),
    name character varying(255)
);
    DROP TABLE public.skill;
       public         heap    ygmfurlunobgmc    false            �            1259    9646507    skill_sequence    SEQUENCE     x   CREATE SEQUENCE public.skill_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.skill_sequence;
       public          ygmfurlunobgmc    false            �            1259    9646533    state    TABLE     y   CREATE TABLE public.state (
    id bigint NOT NULL,
    name character varying(255),
    uf_code character varying(2)
);
    DROP TABLE public.state;
       public         heap    ygmfurlunobgmc    false            �            1259    9646509    state_sequence    SEQUENCE     x   CREATE SEQUENCE public.state_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.state_sequence;
       public          ygmfurlunobgmc    false            �            1259    9646538    user_address    TABLE     +  CREATE TABLE public.user_address (
    id bigint NOT NULL,
    address_number character varying(255) NOT NULL,
    cep character varying(255) NOT NULL,
    complement character varying(255),
    district character varying(255),
    street_name character varying(255),
    city_id bigint NOT NULL
);
     DROP TABLE public.user_address;
       public         heap    ygmfurlunobgmc    false            �            1259    9646511    user_address_sequence    SEQUENCE        CREATE SEQUENCE public.user_address_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.user_address_sequence;
       public          ygmfurlunobgmc    false            �            1259    9646546    user_contributor    TABLE       CREATE TABLE public.user_contributor (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
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
 $   DROP TABLE public.user_contributor;
       public         heap    ygmfurlunobgmc    false            �            1259    9646556 $   user_contributor_causes_that_support    TABLE     �   CREATE TABLE public.user_contributor_causes_that_support (
    user_contributor_domain_id bigint NOT NULL,
    causes_that_support_id bigint NOT NULL
);
 8   DROP TABLE public.user_contributor_causes_that_support;
       public         heap    ygmfurlunobgmc    false            �            1259    9646559    user_contributor_user_skills    TABLE     �   CREATE TABLE public.user_contributor_user_skills (
    user_contributor_domain_id bigint NOT NULL,
    user_skills_id bigint NOT NULL
);
 0   DROP TABLE public.user_contributor_user_skills;
       public         heap    ygmfurlunobgmc    false            �            1259    9646562 
   user_group    TABLE     �  CREATE TABLE public.user_group (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
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
    DROP TABLE public.user_group;
       public         heap    ygmfurlunobgmc    false            �            1259    9646570    user_group_causes_that_support    TABLE     �   CREATE TABLE public.user_group_causes_that_support (
    user_group_domain_id bigint NOT NULL,
    causes_that_support_id bigint NOT NULL
);
 2   DROP TABLE public.user_group_causes_that_support;
       public         heap    ygmfurlunobgmc    false            �            1259    9646573    user_organization    TABLE     �  CREATE TABLE public.user_organization (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
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
 %   DROP TABLE public.user_organization;
       public         heap    ygmfurlunobgmc    false            �            1259    9646581 %   user_organization_causes_that_support    TABLE     �   CREATE TABLE public.user_organization_causes_that_support (
    user_organization_domain_id bigint NOT NULL,
    causes_that_support_id bigint NOT NULL
);
 9   DROP TABLE public.user_organization_causes_that_support;
       public         heap    ygmfurlunobgmc    false            �            1259    9646513    user_sequence    SEQUENCE     w   CREATE SEQUENCE public.user_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.user_sequence;
       public          ygmfurlunobgmc    false            R          0    9646515    causes 
   TABLE DATA           1   COPY public.causes (id, description) FROM stdin;
    public          ygmfurlunobgmc    false    207   ~Q       S          0    9646520    city 
   TABLE DATA           2   COPY public.city (id, name, state_id) FROM stdin;
    public          ygmfurlunobgmc    false    208   �Q       T          0    9646525    skill 
   TABLE DATA           6   COPY public.skill (id, description, name) FROM stdin;
    public          ygmfurlunobgmc    false    209   �Q       U          0    9646533    state 
   TABLE DATA           2   COPY public.state (id, name, uf_code) FROM stdin;
    public          ygmfurlunobgmc    false    210   �Q       V          0    9646538    user_address 
   TABLE DATA           k   COPY public.user_address (id, address_number, cep, complement, district, street_name, city_id) FROM stdin;
    public          ygmfurlunobgmc    false    211   �Q       W          0    9646546    user_contributor 
   TABLE DATA           �   COPY public.user_contributor (id, created_at, password, username, allows_be_contacted_by_organization, contributor_name, description, email, has_cnh, phone, profile_picture, user_address_id) FROM stdin;
    public          ygmfurlunobgmc    false    212   R       X          0    9646556 $   user_contributor_causes_that_support 
   TABLE DATA           r   COPY public.user_contributor_causes_that_support (user_contributor_domain_id, causes_that_support_id) FROM stdin;
    public          ygmfurlunobgmc    false    213   ,R       Y          0    9646559    user_contributor_user_skills 
   TABLE DATA           b   COPY public.user_contributor_user_skills (user_contributor_domain_id, user_skills_id) FROM stdin;
    public          ygmfurlunobgmc    false    214   IR       Z          0    9646562 
   user_group 
   TABLE DATA           �   COPY public.user_group (id, created_at, password, username, description, email, group_mission, group_name, phone, profile_picture, user_address_id) FROM stdin;
    public          ygmfurlunobgmc    false    215   fR       [          0    9646570    user_group_causes_that_support 
   TABLE DATA           f   COPY public.user_group_causes_that_support (user_group_domain_id, causes_that_support_id) FROM stdin;
    public          ygmfurlunobgmc    false    216   �R       \          0    9646573    user_organization 
   TABLE DATA           �   COPY public.user_organization (id, created_at, password, username, cnpj, description, email, organization_mission, organization_name, phone, profile_picture, user_address_id) FROM stdin;
    public          ygmfurlunobgmc    false    217   �R       ]          0    9646581 %   user_organization_causes_that_support 
   TABLE DATA           t   COPY public.user_organization_causes_that_support (user_organization_domain_id, causes_that_support_id) FROM stdin;
    public          ygmfurlunobgmc    false    218   �R       f           0    0    city_sequence    SEQUENCE SET     <   SELECT pg_catalog.setval('public.city_sequence', 1, false);
          public          ygmfurlunobgmc    false    202            g           0    0    skill_sequence    SEQUENCE SET     =   SELECT pg_catalog.setval('public.skill_sequence', 1, false);
          public          ygmfurlunobgmc    false    203            h           0    0    state_sequence    SEQUENCE SET     =   SELECT pg_catalog.setval('public.state_sequence', 1, false);
          public          ygmfurlunobgmc    false    204            i           0    0    user_address_sequence    SEQUENCE SET     D   SELECT pg_catalog.setval('public.user_address_sequence', 1, false);
          public          ygmfurlunobgmc    false    205            j           0    0    user_sequence    SEQUENCE SET     <   SELECT pg_catalog.setval('public.user_sequence', 1, false);
          public          ygmfurlunobgmc    false    206            �           2606    9646519    causes causes_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.causes
    ADD CONSTRAINT causes_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.causes DROP CONSTRAINT causes_pkey;
       public            ygmfurlunobgmc    false    207            �           2606    9646524    city city_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.city DROP CONSTRAINT city_pkey;
       public            ygmfurlunobgmc    false    208            �           2606    9646532    skill skill_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.skill
    ADD CONSTRAINT skill_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.skill DROP CONSTRAINT skill_pkey;
       public            ygmfurlunobgmc    false    209            �           2606    9646537    state state_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.state
    ADD CONSTRAINT state_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.state DROP CONSTRAINT state_pkey;
       public            ygmfurlunobgmc    false    210            �           2606    9646545    user_address user_address_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.user_address
    ADD CONSTRAINT user_address_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.user_address DROP CONSTRAINT user_address_pkey;
       public            ygmfurlunobgmc    false    211            �           2606    9646555 &   user_contributor user_contributor_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.user_contributor
    ADD CONSTRAINT user_contributor_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.user_contributor DROP CONSTRAINT user_contributor_pkey;
       public            ygmfurlunobgmc    false    212            �           2606    9646569    user_group user_group_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT user_group_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.user_group DROP CONSTRAINT user_group_pkey;
       public            ygmfurlunobgmc    false    215            �           2606    9646580 (   user_organization user_organization_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.user_organization
    ADD CONSTRAINT user_organization_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.user_organization DROP CONSTRAINT user_organization_pkey;
       public            ygmfurlunobgmc    false    217            �           2606    9646614 8   user_contributor_user_skills FK51f1xnxhnuadxa6iopl2ap0dh    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_contributor_user_skills
    ADD CONSTRAINT "FK51f1xnxhnuadxa6iopl2ap0dh" FOREIGN KEY (user_contributor_domain_id) REFERENCES public.user_contributor(id);
 d   ALTER TABLE ONLY public.user_contributor_user_skills DROP CONSTRAINT "FK51f1xnxhnuadxa6iopl2ap0dh";
       public          ygmfurlunobgmc    false    212    214    3773            �           2606    9646624 :   user_group_causes_that_support FK65441mn7yvfwwnl3yo9grupjp    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_group_causes_that_support
    ADD CONSTRAINT "FK65441mn7yvfwwnl3yo9grupjp" FOREIGN KEY (causes_that_support_id) REFERENCES public.causes(id);
 f   ALTER TABLE ONLY public.user_group_causes_that_support DROP CONSTRAINT "FK65441mn7yvfwwnl3yo9grupjp";
       public          ygmfurlunobgmc    false    3763    216    207            �           2606    9646639 A   user_organization_causes_that_support FKbfi3q03fpi6oe2w338gfkvvkk    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_organization_causes_that_support
    ADD CONSTRAINT "FKbfi3q03fpi6oe2w338gfkvvkk" FOREIGN KEY (causes_that_support_id) REFERENCES public.causes(id);
 m   ALTER TABLE ONLY public.user_organization_causes_that_support DROP CONSTRAINT "FKbfi3q03fpi6oe2w338gfkvvkk";
       public          ygmfurlunobgmc    false    3763    207    218            �           2606    9646629 :   user_group_causes_that_support FKee6x4au2cjrlwk4s57xyy1pt4    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_group_causes_that_support
    ADD CONSTRAINT "FKee6x4au2cjrlwk4s57xyy1pt4" FOREIGN KEY (user_group_domain_id) REFERENCES public.user_group(id);
 f   ALTER TABLE ONLY public.user_group_causes_that_support DROP CONSTRAINT "FKee6x4au2cjrlwk4s57xyy1pt4";
       public          ygmfurlunobgmc    false    216    215    3775            �           2606    9646634 -   user_organization FKf8fbdtu5svef9syiol19rux2k    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_organization
    ADD CONSTRAINT "FKf8fbdtu5svef9syiol19rux2k" FOREIGN KEY (user_address_id) REFERENCES public.user_address(id);
 Y   ALTER TABLE ONLY public.user_organization DROP CONSTRAINT "FKf8fbdtu5svef9syiol19rux2k";
       public          ygmfurlunobgmc    false    217    3771    211            �           2606    9646594 ,   user_contributor FKfb3pe8jwmqinri0044aqvnh11    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_contributor
    ADD CONSTRAINT "FKfb3pe8jwmqinri0044aqvnh11" FOREIGN KEY (user_address_id) REFERENCES public.user_address(id);
 X   ALTER TABLE ONLY public.user_contributor DROP CONSTRAINT "FKfb3pe8jwmqinri0044aqvnh11";
       public          ygmfurlunobgmc    false    211    212    3771            �           2606    9646644 A   user_organization_causes_that_support FKfg0l9kdud12uj4b5eyevbt10c    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_organization_causes_that_support
    ADD CONSTRAINT "FKfg0l9kdud12uj4b5eyevbt10c" FOREIGN KEY (user_organization_domain_id) REFERENCES public.user_organization(id);
 m   ALTER TABLE ONLY public.user_organization_causes_that_support DROP CONSTRAINT "FKfg0l9kdud12uj4b5eyevbt10c";
       public          ygmfurlunobgmc    false    217    3777    218            �           2606    9646589 (   user_address FKfr03lndxyp40y5s18mb11qh20    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_address
    ADD CONSTRAINT "FKfr03lndxyp40y5s18mb11qh20" FOREIGN KEY (city_id) REFERENCES public.city(id);
 T   ALTER TABLE ONLY public.user_address DROP CONSTRAINT "FKfr03lndxyp40y5s18mb11qh20";
       public          ygmfurlunobgmc    false    3765    211    208            �           2606    9646584     city FKihijj5d7rtf9249lma9rr241y    FK CONSTRAINT     �   ALTER TABLE ONLY public.city
    ADD CONSTRAINT "FKihijj5d7rtf9249lma9rr241y" FOREIGN KEY (state_id) REFERENCES public.state(id);
 L   ALTER TABLE ONLY public.city DROP CONSTRAINT "FKihijj5d7rtf9249lma9rr241y";
       public          ygmfurlunobgmc    false    208    3769    210            �           2606    9646599 @   user_contributor_causes_that_support FKihvimj0ebfqqh68eei7wvpq87    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_contributor_causes_that_support
    ADD CONSTRAINT "FKihvimj0ebfqqh68eei7wvpq87" FOREIGN KEY (causes_that_support_id) REFERENCES public.causes(id);
 l   ALTER TABLE ONLY public.user_contributor_causes_that_support DROP CONSTRAINT "FKihvimj0ebfqqh68eei7wvpq87";
       public          ygmfurlunobgmc    false    213    207    3763            �           2606    9646604 @   user_contributor_causes_that_support FKkebfvp3byc0ln4yrfttqnai16    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_contributor_causes_that_support
    ADD CONSTRAINT "FKkebfvp3byc0ln4yrfttqnai16" FOREIGN KEY (user_contributor_domain_id) REFERENCES public.user_contributor(id);
 l   ALTER TABLE ONLY public.user_contributor_causes_that_support DROP CONSTRAINT "FKkebfvp3byc0ln4yrfttqnai16";
       public          ygmfurlunobgmc    false    213    3773    212            �           2606    9646619 &   user_group FKlt4l63qeetrbxnfu4hw2a9xyp    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT "FKlt4l63qeetrbxnfu4hw2a9xyp" FOREIGN KEY (user_address_id) REFERENCES public.user_address(id);
 R   ALTER TABLE ONLY public.user_group DROP CONSTRAINT "FKlt4l63qeetrbxnfu4hw2a9xyp";
       public          ygmfurlunobgmc    false    211    3771    215            �           2606    9646609 8   user_contributor_user_skills FKr8d08h114f02l7x6iltmc4p0v    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_contributor_user_skills
    ADD CONSTRAINT "FKr8d08h114f02l7x6iltmc4p0v" FOREIGN KEY (user_skills_id) REFERENCES public.skill(id);
 d   ALTER TABLE ONLY public.user_contributor_user_skills DROP CONSTRAINT "FKr8d08h114f02l7x6iltmc4p0v";
       public          ygmfurlunobgmc    false    209    214    3767            R      x������ � �      S      x������ � �      T      x������ � �      U      x������ � �      V      x������ � �      W      x������ � �      X      x������ � �      Y      x������ � �      Z      x������ � �      [      x������ � �      \      x������ � �      ]      x������ � �     