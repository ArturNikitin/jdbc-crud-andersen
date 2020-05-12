CREATE TABLE IF NOT EXISTS Users (
	ID SERIAL PRIMARY KEY,
	Name VARCHAR(50) NOT NULL,
	astname VARCHAR(50) NOT NULL,
	Age Integer NOT NULL
);

CREATE UNIQUE INDEX name_lastname_idx
ON Users(name, lastname);

CREATE TABLE IF NOT EXISTS Roles (
	ID SERIAL PRIMARY KEY,
	NAME VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles (
	user_id Integer not null,
	role_id Integer not null,
	constraint users_roles_pkey PRIMARY KEY (user_id, role_id),
	constraint user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id),
	CONSTRAINT role_id_fkey FOREIGN KEY (role_id) REFERENCES roles(id)
);