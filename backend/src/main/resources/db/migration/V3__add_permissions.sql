INSERT INTO permissions
VALUES  (1, 'READ'),
        (2, 'UPDATE'),
        (3, 'CREATE'),
        (4, 'DELETE'),
        (5, 'GRANT') on conflict do nothing;

INSERT INTO rolespermissions
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 5) on conflict do nothing;
