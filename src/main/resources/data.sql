-- Address data
insert into address (id, address_line1, address_line2, city, postal_code)
values (1, 'xx', 'yy', 'city', '62-030');

-- Doctor data
INSERT INTO doctor (id, first_name, last_name, email, telephone_number, doctor_number, specialization) VALUES
(1, 'John', 'Doe', 'john.doe@example.com', '123456789', 'D123', 'Cardiology'),
(2, 'Jane', 'Smith', 'jane.smith@example.com', '987654321', 'D456', 'Neurology');

-- Patient data
INSERT INTO patient (id, first_name, last_name, email, telephone_number, patient_number, age, date_of_birth) VALUES
(1, 'Alice', 'Johnson', 'alice.johnson@example.com', '111222333', 'P123', 30, '1993-01-01'),
(2, 'Bob', 'Williams', 'bob.williams@example.com', '444555666', 'P456', 40, '1983-02-02'),
(3, 'Charlie', 'Brown', 'charlie.brown@example.com', '333444555', 'P125', 45, '1978-09-25'),
(4, 'David', 'Wilson', 'david.wilson@example.com', '444555666', 'P126', 33, '1990-12-10'),
(5, 'Eve', 'Jackson', 'eve.jackson@example.com', '555666777', 'P789', 28, '1995-03-15');

-- Visit data
INSERT INTO visit (id, description, time, doctor_id, patient_id) VALUES
(1, 'Routine check-up', '2023-01-15 10:00:00', 1, 1),
(2, 'Follow-up visit', '2023-01-20 11:00:00', 2, 2),
(3, 'Special consultation', '2023-01-25 12:00:00', 1, 2),
(4, 'Annual check-up', '2023-02-10 09:30:00', 1, 3),
(5, 'Dental check-up', '2023-02-20 10:00:00', 2, 4),
(6, 'Eye examination', '2023-03-15 14:00:00', 1, 5),
(7, 'Blood test', '2023-03-25 08:00:00', 2, 1),
(8, 'MRI scan', '2023-04-10 15:00:00', 2, 3),
(9, 'Consultation', '2023-04-20 13:00:00', 1, 4),
(10, 'Routine check-up', '2023-05-05 10:00:00', 1, 2);
