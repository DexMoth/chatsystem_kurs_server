PGDMP  3        
            }         	   education    15.5    17.4 #                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            !           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            "           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            #           1262    91310 	   education    DATABASE     }   CREATE DATABASE education WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE education;
                     postgres    false            K           1247    91312    status_task    TYPE     Y   CREATE TYPE public.status_task AS ENUM (
    'OPENED',
    'COMPLETED',
    'OVERDUE'
);
    DROP TYPE public.status_task;
       public               postgres    false            �            1259    91331    chat    TABLE     _   CREATE TABLE public.chat (
    id integer NOT NULL,
    name character varying(50) NOT NULL
);
    DROP TABLE public.chat;
       public         heap r       postgres    false            �            1259    91330    chat_id_seq    SEQUENCE     �   CREATE SEQUENCE public.chat_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.chat_id_seq;
       public               postgres    false    217            $           0    0    chat_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.chat_id_seq OWNED BY public.chat.id;
          public               postgres    false    216            �            1259    91338    message    TABLE     �   CREATE TABLE public.message (
    id integer NOT NULL,
    chat_id integer NOT NULL,
    user_id integer NOT NULL,
    text character varying(4096) NOT NULL,
    is_favorite boolean
);
    DROP TABLE public.message;
       public         heap r       postgres    false            �            1259    91337    message_id_seq    SEQUENCE     �   CREATE SEQUENCE public.message_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.message_id_seq;
       public               postgres    false    219            %           0    0    message_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.message_id_seq OWNED BY public.message.id;
          public               postgres    false    218            �            1259    91397 	   user_chat    TABLE     ^   CREATE TABLE public.user_chat (
    user_id integer NOT NULL,
    chat_id integer NOT NULL
);
    DROP TABLE public.user_chat;
       public         heap r       postgres    false            �            1259    91320    users    TABLE     �   CREATE TABLE public.users (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    password character varying(50) NOT NULL,
    phone character varying(11),
    report_card_number character varying(8),
    login character varying(50)
);
    DROP TABLE public.users;
       public         heap r       postgres    false            �            1259    91319    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public               postgres    false    215            &           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public               postgres    false    214            w           2604    91334    chat id    DEFAULT     b   ALTER TABLE ONLY public.chat ALTER COLUMN id SET DEFAULT nextval('public.chat_id_seq'::regclass);
 6   ALTER TABLE public.chat ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    217    216    217            x           2604    91341 
   message id    DEFAULT     h   ALTER TABLE ONLY public.message ALTER COLUMN id SET DEFAULT nextval('public.message_id_seq'::regclass);
 9   ALTER TABLE public.message ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    218    219    219            v           2604    91323    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    214    215    215                      0    91331    chat 
   TABLE DATA           (   COPY public.chat (id, name) FROM stdin;
    public               postgres    false    217   �&                 0    91338    message 
   TABLE DATA           J   COPY public.message (id, chat_id, user_id, text, is_favorite) FROM stdin;
    public               postgres    false    219   '                 0    91397 	   user_chat 
   TABLE DATA           5   COPY public.user_chat (user_id, chat_id) FROM stdin;
    public               postgres    false    220   ;'                 0    91320    users 
   TABLE DATA           U   COPY public.users (id, name, password, phone, report_card_number, login) FROM stdin;
    public               postgres    false    215   `'       '           0    0    chat_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.chat_id_seq', 1, true);
          public               postgres    false    216            (           0    0    message_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.message_id_seq', 7, true);
          public               postgres    false    218            )           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 21, true);
          public               postgres    false    214            �           2606    91336    chat chat_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.chat
    ADD CONSTRAINT chat_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.chat DROP CONSTRAINT chat_pkey;
       public                 postgres    false    217            �           2606    91345    message message_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.message DROP CONSTRAINT message_pkey;
       public                 postgres    false    219            �           2606    91401    user_chat user_chat_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.user_chat
    ADD CONSTRAINT user_chat_pkey PRIMARY KEY (user_id, chat_id);
 B   ALTER TABLE ONLY public.user_chat DROP CONSTRAINT user_chat_pkey;
       public                 postgres    false    220    220            z           2606    91327    users users_phone_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_phone_key UNIQUE (phone);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_phone_key;
       public                 postgres    false    215            |           2606    91325    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public                 postgres    false    215            ~           2606    91329 "   users users_report_card_number_key 
   CONSTRAINT     k   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_report_card_number_key UNIQUE (report_card_number);
 L   ALTER TABLE ONLY public.users DROP CONSTRAINT users_report_card_number_key;
       public                 postgres    false    215            �           2606    91346    message message_chat_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_chat_id_fkey FOREIGN KEY (chat_id) REFERENCES public.chat(id) ON DELETE CASCADE;
 F   ALTER TABLE ONLY public.message DROP CONSTRAINT message_chat_id_fkey;
       public               postgres    false    219    3200    217            �           2606    91351    message message_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;
 F   ALTER TABLE ONLY public.message DROP CONSTRAINT message_user_id_fkey;
       public               postgres    false    219    215    3196            �           2606    91407     user_chat user_chat_chat_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_chat
    ADD CONSTRAINT user_chat_chat_id_fkey FOREIGN KEY (chat_id) REFERENCES public.chat(id) ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.user_chat DROP CONSTRAINT user_chat_chat_id_fkey;
       public               postgres    false    217    220    3200            �           2606    91402     user_chat user_chat_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_chat
    ADD CONSTRAINT user_chat_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.user_chat DROP CONSTRAINT user_chat_user_id_fkey;
       public               postgres    false    3196    220    215               f   x����@�*� ��r(	KH�h0/桅َ|8�Վf�]�޻������7�/�Q]�Q5�=��F�:�Zb�����L�~<}/j����u�A            x�3�4�44�,.)��K�L����� 4��            x�34�4�241z\\\ $�         m   x�34�,.)��K�Gq���W�p��q&��)�Kj�o~I��I��24�LJ��OIMW@�9	�\��%؁��p���2��L�/��+K�)MU�����Dbs��qqq o�     