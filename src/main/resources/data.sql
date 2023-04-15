-- novel
INSERT INTO novel (id, author, description, genre, title) VALUES
                                                              (1, 'George Orwell', 'A dystopian novel set in a totalitarian society controlled by the Party and its leader, Big Brother.', 'Dystopian Fiction', '1984'),
                                                              (2, 'Harper Lee', 'A powerful story of racial injustice, childhood innocence, and the moral growth of a young girl named Scout.', 'Southern Gothic Fiction', 'To Kill a Mockingbird'),
                                                              (3, 'Jane Austen', 'A romantic novel following the life of Elizabeth Bennet as she navigates love, marriage, and society in 19th-century England.', 'Romantic Fiction', 'Pride and Prejudice');

-- user
INSERT INTO user (id, name, point) VALUES
                                       (1, 'Alice Johnson', 500),
                                       (2, 'Bob Smith', 300),
                                       (3, 'Cynthia Williams', 1200);

-- volume
INSERT INTO volume (id, file_size, number_of_pages, point_for_purchase, series_number, title, version, written_date_time, novel_id) VALUES
                                                                                                                                        (1, 900000, 326, 200, 1, '1984 - Part 1', 1, '1949-06-08 00:00:00.000000', 1),
                                                                                                                                        (2, 850000, 303, 200, 2, '1984 - Part 2', 1, '1949-06-08 00:00:00.000000', 1),
                                                                                                                                        (3, 800000, 290, 200, 3, '1984 - Part 3', 1, '1949-06-08 00:00:00.000000', 1),
                                                                                                                                        (4, 1200000, 281, 250, 1, 'To Kill a Mockingbird - Part 1', 1, '1960-07-11 00:00:00.000000', 2),
                                                                                                                                        (5, 1100000, 265, 250, 2, 'To Kill a Mockingbird - Part 2', 1, '1960-07-11 00:00:00.000000', 2),
                                                                                                                                        (6, 1000000, 243, 200, 1, 'Pride and Prejudice - Volume 1', 1, '1813-01-28 00:00:00.000000', 3),
                                                                                                                                        (7, 950000, 229, 200, 2, 'Pride and Prejudice - Volume 2', 1, '1813-01-28 00:00:00.000000', 3),
                                                                                                                                        (8, 900000, 222, 200, 3, 'Pride and Prejudice - Volume 3', 1, '1813-01-28 00:00:00.000000', 3);