-- Insert a novel
SET @novel_id = UUID();
INSERT INTO novel (id, title, author, description, genre)
VALUES (@novel_id, 'A Song of Ice and Fire', 'George R. R. Martin', 'An epic series of fantasy novels set in the fictional continents of Westeros and Essos.', 'Fantasy');

-- Insert 10 volumes for the novel
INSERT INTO volume (id, title, written_datetime, series_number, number_of_pages, file_size, is_paid, novel_id)
VALUES
    (UUID(), 'A Game of Thrones', '1996-08-01 00:00:00', 1, 694, 3500000, 1, @novel_id),
    (UUID(), 'A Clash of Kings', '1998-11-16 00:00:00', 2, 768, 4000000, 1, @novel_id),
    (UUID(), 'A Storm of Swords', '2000-08-08 00:00:00', 3, 973, 5000000, 1, @novel_id),
    (UUID(), 'A Feast for Crows', '2005-10-17 00:00:00', 4, 753, 3800000, 1, @novel_id),
    (UUID(), 'A Dance with Dragons', '2011-07-12 00:00:00', 5, 1016, 5200000, 1, @novel_id),
    (UUID(), 'The Winds of Winter', '2023-06-27 00:00:00', 6, 900, 4500000, 1, @novel_id),
    (UUID(), 'A Dream of Spring', '2025-04-15 00:00:00', 7, 850, 4300000, 1, @novel_id),
    (UUID(), 'The Tales of Dunk and Egg', '2022-10-25 00:00:00', 8, 1072, 6000000, 1, @novel_id),
    (UUID(), 'Fire and Blood', '2018-11-20 00:00:00', 9, 736, 3700000, 1, @novel_id),
    (UUID(), 'The World of Ice and Fire', '2014-10-28 00:00:00', 10, 326, 1800000, 1, @novel_id);
