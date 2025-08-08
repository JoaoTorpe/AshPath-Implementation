CREATE TABLE IF NOT EXISTS APP_ROLE (
    Id BIGINT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS APP_USER (
    Id BIGINT PRIMARY KEY AUTO_INCREMENT,
    Email VARCHAR(254),
    Password VARCHAR(64),
    Full_Name VARCHAR(64),  
    Registration_Date TIMESTAMP, 
    Last_Activity_Date TIMESTAMP, 
    Specialization VARCHAR(64),
    approved BOOLEAN
);

CREATE TABLE IF NOT EXISTS ROLE_APP_USER (
    APP_USER_ID BIGINT NOT NULL,
    ROLE_APP_ID BIGINT NOT NULL,
    PRIMARY KEY (App_User_Id, Role_App_Id),
    FOREIGN KEY (App_User_Id) REFERENCES APP_USER(Id),
    FOREIGN KEY (Role_App_Id) REFERENCES APP_ROLE(Id)
);

-- more than one person can be cremated at a time
CREATE TABLE IF NOT EXISTS CREMATION_ENTRY (
    Id BIGINT PRIMARY KEY AUTO_INCREMENT,
    Entered_Date TIMESTAMP,  -- Padronizado
    Necrotomist_Id BIGINT,
    FOREIGN KEY (Necrotomist_Id) REFERENCES APP_USER(Id)
);

CREATE TABLE IF NOT EXISTS GRAVE (
    Id BIGINT PRIMARY KEY AUTO_INCREMENT,
    Location VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS DECEASED (
    Id BIGINT PRIMARY KEY AUTO_INCREMENT,
    Full_Name VARCHAR(128),  -- Padronizado
    Birth_Date DATE,
    Death_Date DATE,
    Cause_Of_Death VARCHAR(128),
    Death_Certificate LONGBLOB,  -- Padronizado
    Father_Name VARCHAR(128),  -- Padronizado
    Mother_Name VARCHAR(128),  -- Padronizado
    Status VARCHAR(50),
    Cremation_Entered_Date TIMESTAMP,
    Cremation_Entry_Id BIGINT,
    Grave_Id BIGINT,
    FOREIGN KEY (Cremation_Entry_Id) REFERENCES CREMATION_ENTRY(Id),
    FOREIGN KEY (Grave_Id) REFERENCES GRAVE(Id)
);

-- 2. INSERTs corrigidos para usar os nomes padronizados
INSERT INTO APP_ROLE (Name) VALUES
('ADMIN'),
('NECROTOMIST'),
('VIEWER');

-- Todos tem a mesma senha: 's3nh4@S' --
INSERT INTO APP_USER (Email, Password, Full_Name, Registration_Date, Last_Activity_Date, Specialization, approved) VALUES
('mohg.silva@gmail.com', '$2a$12$UeqET5NXiGIvHU3OkuHGTO.YsgvGwteii01Fs0YnYsDgrvlnndBXS', 'Mohg Silva', NOW(), NOW(), null, true),
('helmuth@yahoo.com', '$2a$12$UeqET5NXiGIvHU3OkuHGTO.YsgvGwteii01Fs0YnYsDgrvlnndBXS', 'Helmuth Voss', NOW(), NOW(), 'Forensic Pathology Support', true),
('emil@yahoo.com', '$2a$12$UeqET5NXiGIvHU3OkuHGTO.YsgvGwteii01Fs0YnYsDgrvlnndBXS', 'Emil Sebe', NOW(), NOW(), 'Anatomical Pathology', true),
('jakubfarobek@yahoo.com', '$2a$12$UeqET5NXiGIvHU3OkuHGTO.YsgvGwteii01Fs0YnYsDgrvlnndBXS', 'Jakub Farobek', NOW(), NOW(), null, true),
('bayle@yahoo.com', '$2a$12$UeqET5NXiGIvHU3OkuHGTO.YsgvGwteii01Fs0YnYsDgrvlnndBXS', 'Bayle The Dread', NOW(), NOW(), null, false),
('cantarella@gmail.com', '$2a$12$UeqET5NXiGIvHU3OkuHGTO.YsgvGwteii01Fs0YnYsDgrvlnndBXS', 'Cantarella', NOW(), NOW(), null, false),
('itsuki@yahoo.com', '$2a$12$UeqET5NXiGIvHU3OkuHGTO.YsgvGwteii01Fs0YnYsDgrvlnndBXS', 'Nakano Itsuki', NOW(), NOW(), null, false),
('rafal@yahoo.com', '$2a$12$UeqET5NXiGIvHU3OkuHGTO.YsgvGwteii01Fs0YnYsDgrvlnndBXS', 'Rafal', NOW(), NOW(), null, false),
('nosramus@gmail.com', '$2a$12$UeqET5NXiGIvHU3OkuHGTO.YsgvGwteii01Fs0YnYsDgrvlnndBXS', 'Nosramus', NOW(), NOW(), null, false),
('Valteil@yahoo.com', '$2a$12$UeqET5NXiGIvHU3OkuHGTO.YsgvGwteii01Fs0YnYsDgrvlnndBXS', 'Valteil, The Enlightened One', NOW(), NOW(), null, false),
('wuwa@yahoo.com', '$2a$12$UeqET5NXiGIvHU3OkuHGTO.YsgvGwteii01Fs0YnYsDgrvlnndBXS', 'Wuwa of Astora', NOW(), NOW(), null, false)
;

INSERT INTO ROLE_APP_USER (App_User_Id, Role_App_Id) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 3),
(5, 3),
(6, 2),
(7, 3),
(8, 2),
(9, 3),
(10, 2),
(11, 1)
;

INSERT INTO CREMATION_ENTRY (Entered_Date, Necrotomist_Id) VALUES
('2023-01-10 08:00:00', 2),
('2023-02-15 09:30:00', 3),
('2023-03-21 10:00:00', 2),
('2023-04-05 14:00:00', 2),
('2023-05-18 11:45:00', 3),
('2023-06-23 14:30:00', 2),
('2023-07-10 08:15:00', 3),
('2023-07-15 09:00:00', 2),
('2023-07-20 10:30:00', 2),
('2023-07-25 11:00:00', 3),
('2023-08-01 12:00:00', 3),
('2023-08-05 13:00:00', 2),
('2023-08-10 14:00:00', 3),
('2023-08-15 15:00:00', 2),
('2023-08-22 16:00:00', 3),
('2023-08-30 17:00:00', 2),
('2023-09-01 18:00:00', 3),
('2023-09-05 19:00:00', 2);

-- 3. Inserir túmulos
INSERT INTO GRAVE (Location) VALUES
('Maceio'),
('Ibura'),
('Loja renner'),
('Cemitério São João'),
('Memorial Paz Eterna'),
('Jardim da Saudade');

-- 4. Inserir falecidos sem certificado de óbito em PDF (BLOB)
INSERT INTO DECEASED (
    Full_Name, Birth_Date, Death_Date, Cause_Of_Death,
    Father_Name, Mother_Name, Status,
    Cremation_Entered_Date, Cremation_Entry_Id, Grave_Id
) VALUES
('Carlos Alberto', '1950-05-15', '2017-01-10', 'Doença cardiovascular',
 'José Alberto', 'Maria Silva', 'GRAVED', '2023-01-10 08:30:00', 1, 1),

('Ana Paula', '1975-08-22', '2020-02-15', 'Acidente automobilístico',
 'Pedro Henrique', 'Claudia Santos', 'WAITING_CREMATION', NOW(), 2, 2),

('Roberto Nascimento', '1962-11-30', '2015-03-20', 'COVID-19',
 'Antônio Nascimento', 'Beatriz Oliveira', 'CREMATED', '2023-03-21 10:00:00', 3, 3),

('Mariana Costa', '1988-07-14', '2022-04-05', 'Câncer',
 'Fernando Costa', 'Isabela Rodrigues', 'GRAVED', '2023-04-05 14:15:00', 1, 4),

('Belal Mohamend', '1992-12-25', '2019-05-18', 'Acidente de trabalho',
 'Ricardo Oliveira', 'Patrícia Souza', 'WAITING_CREMATION', NOW(), 2, 5),

('Della Maddalena', '1973-09-30', '2010-06-22', 'Insuficiência renal',
 'Carlos Silva', 'Ana Maria', 'CREMATED', '2023-06-23 14:30:00', 3, 6),

('Eduardo Reis', '1951-12-30', '2023-07-10', 'AVC',
 'João Reis', 'Marisa Reis', 'CREMATED', NOW(), 4, 1),

('Luciana Prado', '1982-06-11', '2014-07-15', 'Pneumonia',
 'Carlos Prado', 'Fernanda Prado', 'GRAVED', NOW(), 5, 2),

('Guilherme Rocha', '1970-01-01', '2021-07-20', 'Câncer',
 'Roberto Rocha', 'Adriana Rocha', 'CREMATED', NOW(), 6, 3),

('Patrícia Alves', '1995-09-09', '2010-07-25', 'Infecção generalizada',
 'Bruno Alves', 'Sara Alves', 'WAITING_CREMATION', NOW(), 7, 4),

('Renan Oliveira', '1966-03-21', '2020-08-01', 'Diabetes',
 'Lucas Oliveira', 'Verônica Oliveira', 'CREMATED', NOW(), 8, 5),

('Tiago Cunha', '1980-04-12', '2020-08-05', 'Acidente doméstico',
 'Ricardo Cunha', 'Luiza Cunha', 'GRAVED', NOW(), 9, 6),

('Elisa Martins', '2000-02-18', '2023-08-10', 'Suicídio',
 'Fernando Martins', 'Juliana Martins', 'WAITING_CREMATION', NOW(), 10, 1),

('Rafael Souza', '1977-07-07', '2014-08-15', 'COVID-19',
 'Paulo Souza', 'Marta Souza', 'CREMATED', NOW(), 11, 2),

('Juliana Pires', '1963-11-29', '2017-08-22', 'AVC',
 'Eduardo Pires', 'Cecília Pires', 'GRAVED', NOW(), 12, 3),

('Gabriel Barros', '1999-05-05', '2016-08-30', 'Afogamento',
 'Sérgio Barros', 'Nina Barros', 'WAITING_CREMATION', NOW(), 13, 4),

('Amanda Ferreira', '1984-10-10', '2019-09-01', 'Câncer',
 'Marcos Ferreira', 'Denise Ferreira', 'CREMATED', NOW(), 14, 5),

('Douglas Lima', '1958-08-16', '2000-09-05', 'Embolia pulmonar',
 'Geraldo Lima', 'Iracema Lima', 'GRAVED', NOW(), 15, 6),

('Fernanda Dias', '1993-06-23', '2023-09-10', 'Acidente de moto',
 'Otávio Dias', 'Milena Dias', 'WAITING_CREMATION', NOW(), 4, 1),

('João Pedro Nunes', '2001-01-15', '2023-09-14', 'Homicídio',
 'Leandro Nunes', 'Bianca Nunes', 'CREMATED', NOW(), 5, 2),

('Marta Gomes', '1974-03-03', '2023-09-18', 'Doença pulmonar',
 'César Gomes', 'Tereza Gomes', 'GRAVED', NOW(), 6, 3),

('Marcelo Bastos', '1961-12-01', '2023-09-22', 'Câncer de próstata',
 'Arnaldo Bastos', 'Norma Bastos', 'CREMATED', NOW(), 7, 4),

('Isabela Fontes', '1987-07-27', '2023-09-26', 'Leucemia',
 'Vicente Fontes', 'Patrícia Fontes', 'GRAVED', NOW(), 8, 5),

('Jonas Castro', '1949-04-09', '2020-09-30', 'Falência múltipla dos órgãos',
 'Rubens Castro', 'Rita Castro', 'CREMATED', NOW(), 9, 6),

('Tatiane Lima', '1996-12-19', '2024-10-02', 'Suicídio',
 'Alex Lima', 'Cristina Lima', 'WAITING_CREMATION', NOW(), 10, 1),

('Nelson Faria', '1955-10-30', '2023-10-06', 'Acidente vascular isquêmico',
 'Ivan Faria', 'Neide Faria', 'CREMATED', NOW(), 11, 2);
