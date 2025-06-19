CREATE TABLE IF NOT EXISTS APP_ROLE (
    Id BIGINT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS APP_USER (
    Id BIGINT PRIMARY KEY AUTO_INCREMENT,
    Email VARCHAR(64),
    Password VARCHAR(64),
    Full_Name VARCHAR(128),  
    Registration_Date TIMESTAMP, 
    Last_Activity_Date TIMESTAMP, 
    Specialization VARCHAR(255),
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

INSERT INTO APP_USER (Email, Password, Full_Name, Registration_Date, Last_Activity_Date, Specialization, approved) VALUES
('mohg.silva@gmail.com', 's3nh4@S', 'Mohg Silva', NOW(), NOW(), null, true),
('helmuth@yahoo.com', 's3nh4@S', 'Helmuth Voss', NOW(), NOW(), 'Forensic Pathology Support', true),
('emil@yahoo.com', 's3nh4@S', 'Emil Sebe', NOW(), NOW(), 'Anatomical Pathology', false),
('jakubfarobek@yahoo.com', 's3nh4@S', 'Jakub Farobek', NOW(), NOW(), null, true),
('bayle@yahoo.com', 's3nh4@S', 'Bayle The Dread', NOW(), NOW(), null, false)
;

INSERT INTO ROLE_APP_USER (App_User_Id, Role_App_Id) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 3),
(5, 3)
;

INSERT INTO CREMATION_ENTRY (Entered_Date, Necrotomist_Id) VALUES
(NOW(), 2),
(NOW(), 3),
(NOW(), 2),
(NOW(), 2),
(NOW(), 3),
(NOW(), 2),
(NOW(), 3),
(NOW(), 2),
(NOW(), 2),
(NOW(), 3),
(NOW(), 3),
(NOW(), 2),
(NOW(), 3),
(NOW(), 2),
(NOW(), 3),
(NOW(), 2),
(NOW(), 3),
(NOW(), 2);
;

-- 3. Inserir túmulos com IDs explícitos
INSERT INTO GRAVE (Id, Location) VALUES
(1, 'Maceio'),
(2, 'Ibura'),
(3, 'Loja renner'),
(4, 'Cemitério São João'),
(5, 'Memorial Paz Eterna'),
(6, 'Jardim da Saudade')
;

-- 4. Inserir falecidos com certificado de óbito em PDF (BLOB)
INSERT INTO DECEASED (
    Full_Name, Birth_Date, Death_Date, Cause_Of_Death, Death_Certificate,
    Father_Name, Mother_Name, Status,
    Cremation_Entered_Date, Cremation_Entry_Id, Grave_Id
) VALUES
('Carlos Alberto', '1950-05-15', '2023-01-10', 'Doença cardiovascular',
 X'255044462D312E0D0A25B5B5B5B50D0A312030206F626A0D0A3C3C2F547970652F436174616C6F672F50616765732032203020520D0A2F4F75746C696E65732033203020520D0A2F4D657461646174612034203020520D0A3E3E0D0A656E646F626A0D0A',
 'José Alberto', 'Maria Silva', 'GRAVED', '2023-01-10 08:30:00', 1, 1),

('Ana Paula', '1975-08-22', '2023-02-15', 'Acidente automobilístico',
 X'255044462D312E0D0A25B5B5B5B50D0A312030206F626A0D0A3C3C2F547970652F436174616C6F672F50616765732032203020520D0A2F4F75746C696E65732033203020520D0A2F4D657461646174612034203020520D0A3E3E0D0A656E646F626A0D0A', -- PDF simples
 'Pedro Henrique', 'Claudia Santos', 'WAITING_CREMATION', NOW(), 2, 2),

('Roberto Nascimento', '1962-11-30', '2023-03-20', 'COVID-19',
 X'255044462D312E0D0A25B5B5B5B50D0A312030206F626A0D0A3C3C2F547970652F436174616C6F672F50616765732032203020520D0A2F4F75746C696E65732033203020520D0A2F4D657461646174612034203020520D0A3E3E0D0A656E646F626A0D0A', -- PDF simples
 'Antônio Nascimento', 'Beatriz Oliveira', 'CREMATED', '2023-03-21 10:00:00', 3, 3),

('Mariana Costa', '1988-07-14', '2023-04-05', 'Câncer',
 X'255044462D312E0D0A25B5B5B5B50D0A312030206F626A0D0A3C3C2F547970652F436174616C6F672F50616765732032203020520D0A2F4F75746C696E65732033203020520D0A2F4D657461646174612034203020520D0A3E3E0D0A656E646F626A0D0A', -- PDF simples
 'Fernando Costa', 'Isabela Rodrigues', 'GRAVED', '2023-04-05 14:15:00', 1, 4),

('Belal Mohamend', '1992-12-25', '2023-05-18', 'Acidente de trabalho',
 X'255044462D312E0D0A25B5B5B5B50D0A312030206F626A0D0A3C3C2F547970652F436174616C6F672F50616765732032203020520D0A2F4F75746C696E65732033203020520D0A2F4D657461646174612034203020520D0A3E3E0D0A656E646F626A0D0A', -- PDF simples
 'Ricardo Oliveira', 'Patrícia Souza', 'WAITING_CREMATION', NOW(), 2, 5),

('Della Maddalena', '1973-09-30', '2023-06-22', 'Insuficiência renal',
 X'255044462D312E0D0A25B5B5B5B50D0A312030206F626A0D0A3C3C2F547970652F436174616C6F672F50616765732032203020520D0A2F4F75746C696E65732033203020520D0A2F4D657461646174612034203020520D0A3E3E0D0A656E646F626A0D0A', -- PDF simples
 'Carlos Silva', 'Ana Maria', 'CREMATED', '2023-06-23 14:30:00', 3, 6),
('Eduardo Reis', '1951-12-30', '2023-07-10', 'AVC',
 X'25504446', 'João Reis', 'Marisa Reis', 'CREMATED', NOW(), 4, 1),

('Luciana Prado', '1982-06-11', '2023-07-15', 'Pneumonia',
 X'25504446', 'Carlos Prado', 'Fernanda Prado', 'GRAVED', NOW(), 5, 2),

('Guilherme Rocha', '1970-01-01', '2023-07-20', 'Câncer',
 X'25504446', 'Roberto Rocha', 'Adriana Rocha', 'CREMATED', NOW(), 6, 3),

('Patrícia Alves', '1995-09-09', '2023-07-25', 'Infecção generalizada',
 X'25504446', 'Bruno Alves', 'Sara Alves', 'WAITING_CREMATION', NOW(), 7, 4),

('Renan Oliveira', '1966-03-21', '2023-08-01', 'Diabetes',
 X'25504446', 'Lucas Oliveira', 'Verônica Oliveira', 'CREMATED', NOW(), 8, 5),

('Tiago Cunha', '1980-04-12', '2023-08-05', 'Acidente doméstico',
 X'25504446', 'Ricardo Cunha', 'Luiza Cunha', 'GRAVED', NOW(), 9, 6),

('Elisa Martins', '2000-02-18', '2023-08-10', 'Suicídio',
 X'25504446', 'Fernando Martins', 'Juliana Martins', 'WAITING_CREMATION', NOW(), 10, 1),

('Rafael Souza', '1977-07-07', '2023-08-15', 'COVID-19',
 X'25504446', 'Paulo Souza', 'Marta Souza', 'CREMATED', NOW(), 11, 2),

('Juliana Pires', '1963-11-29', '2023-08-22', 'AVC',
 X'25504446', 'Eduardo Pires', 'Cecília Pires', 'GRAVED', NOW(), 12, 3),

('Gabriel Barros', '1999-05-05', '2023-08-30', 'Afogamento',
 X'25504446', 'Sérgio Barros', 'Nina Barros', 'WAITING_CREMATION', NOW(), 13, 4),

('Amanda Ferreira', '1984-10-10', '2023-09-01', 'Câncer',
 X'25504446', 'Marcos Ferreira', 'Denise Ferreira', 'CREMATED', NOW(), 14, 5),

('Douglas Lima', '1958-08-16', '2023-09-05', 'Embolia pulmonar',
 X'25504446', 'Geraldo Lima', 'Iracema Lima', 'GRAVED', NOW(), 15, 6),

('Fernanda Dias', '1993-06-23', '2023-09-10', 'Acidente de moto',
 X'25504446', 'Otávio Dias', 'Milena Dias', 'WAITING_CREMATION', NOW(), 4, 1),

('João Pedro Nunes', '2001-01-15', '2023-09-14', 'Homicídio',
 X'25504446', 'Leandro Nunes', 'Bianca Nunes', 'CREMATED', NOW(), 5, 2),

('Marta Gomes', '1974-03-03', '2023-09-18', 'Doença pulmonar',
 X'25504446', 'César Gomes', 'Tereza Gomes', 'GRAVED', NOW(), 6, 3),

('Marcelo Bastos', '1961-12-01', '2023-09-22', 'Câncer de próstata',
 X'25504446', 'Arnaldo Bastos', 'Norma Bastos', 'CREMATED', NOW(), 7, 4),

('Isabela Fontes', '1987-07-27', '2023-09-26', 'Leucemia',
 X'25504446', 'Vicente Fontes', 'Patrícia Fontes', 'GRAVED', NOW(), 8, 5),

('Jonas Castro', '1949-04-09', '2023-09-30', 'Falência múltipla dos órgãos',
 X'25504446', 'Rubens Castro', 'Rita Castro', 'CREMATED', NOW(), 9, 6),

('Tatiane Lima', '1996-12-19', '2023-10-02', 'Suicídio',
 X'25504446', 'Alex Lima', 'Cristina Lima', 'WAITING_CREMATION', NOW(), 10, 1),

('Nelson Faria', '1955-10-30', '2023-10-06', 'Acidente vascular isquêmico',
 X'25504446', 'Ivan Faria', 'Neide Faria', 'CREMATED', NOW(), 11, 2) 
;

 