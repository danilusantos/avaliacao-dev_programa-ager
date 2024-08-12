CREATE TABLE exame (
    rowid BIGINT AUTO_INCREMENT PRIMARY KEY, 
    nm_exame VARCHAR(255)
);

CREATE TABLE funcionario (
    rowid BIGINT AUTO_INCREMENT PRIMARY KEY, 
    nm_funcionario VARCHAR(255)
);

CREATE TABLE agendamento (
    rowid BIGINT AUTO_INCREMENT PRIMARY KEY, 
    id_funcionario BIGINT NOT NULL,
    id_exame BIGINT NOT NULL, 
    dt_agendamento DATE NOT NULL
);

INSERT INTO exame (nm_exame) VALUES 
	('Avaliação Clínica'), 
	('Avaliação Psicossocial'), 
	('Acuidade Visual'), 
	('Hemograma Completo'), 
	('Glicemia em Jejum'), 
	('Parasitológico de Fezes - PPF'), 
	('Coprocultura'), 
	('Eletrocardiograma - ECG'), 
	('Eletroencefalograma - EEG'), 
	('Espirometria');

INSERT INTO funcionario (nm_funcionario) VALUES 
	('Danilo Santod'), 
	('Maria Vitória Garrucho');
	
ALTER TABLE agendamento 
	ADD CONSTRAINT fk_funcionario 
	FOREIGN KEY (id_funcionario) 
	REFERENCES funcionario(rowid) 
	ON DELETE CASCADE;

ALTER TABLE agendamento 
	ADD CONSTRAINT fk_exame 
	FOREIGN KEY (id_exame) 
	REFERENCES exame(rowid) 
	ON DELETE RESTRICT;

ALTER TABLE agendamento 
	ADD CONSTRAINT unique_agendamento 
	UNIQUE (id_funcionario, id_exame, dt_agendamento);

INSERT INTO agendamento (
    id_funcionario,
    id_exame, 
    dt_agendamento
) VALUES 
    (1, 1, '2024-08-25'),
    (1, 2, '2024-07-25'), 
    (1, 3, '2024-06-25'), 
    (1, 4, '2024-08-25'), 
    (2, 5, '2024-08-25'), 
    (2, 6, '2024-08-25'), 
    (2, 7, '2024-07-25'), 
    (2, 8, '2024-05-25'), 
    (2, 1, '2024-08-20');
