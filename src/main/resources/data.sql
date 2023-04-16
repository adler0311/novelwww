-- novel
INSERT INTO novel (id, author, description, genre, title, purchase_count, total_pages, create_dt, update_dt) VALUES
                                                                                                                 (1, 'George Orwell', 'A dystopian novel set in a totalitarian society controlled by the Party and its leader, Big Brother.', 'Dystopian Fiction', '1984', 55, 999, '2023-01-01 00:00:00.000000', '2023-01-01 00:00:00.000000'),
                                                                                                                 (2, 'Harper Lee', 'A powerful story of racial injustice, childhood innocence, and the moral growth of a young girl named Scout.', 'Southern Gothic Fiction', 'To Kill a Mockingbird', 131, 999, '2023-01-02 00:00:00.000000', '2023-01-02 00:00:00.000000'),
                                                                                                                 (3, 'Jane Austen', 'A romantic novel following the life of Elizabeth Bennet as she navigates love, marriage, and society in 19th-century England.', 'Romantic Fiction', 'Pride and Prejudice', 664444, 999, '2023-01-03 00:00:00.000000', '2023-01-03 00:00:00.000000');

-- user
INSERT INTO novel_user (id, name, point, create_dt, update_dt) VALUES
                                                             (1, 'Alice Johnson', 500, '2023-01-01 00:00:00.000000', '2023-01-01 00:00:00.000000'),
                                                             (2, 'Bob Smith', 300, '2023-01-01 00:00:00.000000', '2023-01-01 00:00:00.000000'),
                                                             (3, 'Cynthia Williams', 1200, '2023-01-01 00:00:00.000000', '2023-01-01 00:00:00.000000');

-- episode
INSERT INTO episode (id, file_size, number_of_pages, point_for_purchase, series_number, title, version, written_date_time, novel_id, create_dt, update_dt) VALUES
                                                                                                                                                              (1, 900000, 326, 200, 1, '1984 - Part 1', 1, '1949-06-08 00:00:00.000000', 1, '2023-01-01 00:00:00.000000', '2023-01-01 00:00:00.000000'),
                                                                                                                                                              (2, 850000, 303, 200, 2, '1984 - Part 2', 1, '1949-06-08 00:00:00.000000', 1, '2023-01-01 00:00:00.000000', '2023-01-01 00:00:00.000000'),
                                                                                                                                                              (3, 800000, 290, 200, 3, '1984 - Part 3', 1, '1949-06-08 00:00:00.000000', 1, '2023-01-01 00:00:00.000000', '2023-01-01 00:00:00.000000'),
                                                                                                                                                              (4, 1200000, 281, 250, 1, 'To Kill a Mockingbird - Part 1', 1, '1960-07-11 00:00:00.000000', 2, '2023-01-01 00:00:00.000000', '2023-01-01 00:00:00.000000'),
                                                                                                                                                              (5, 1100000, 265, 250, 2, 'To Kill a Mockingbird - Part 2', 1, '1960-07-11 00:00:00.000000', 2, '2023-01-01 00:00:00.000000', '2023-01-01 00:00:00.000000'),
                                                                                                                                                              (6, 1000000, 243, 200, 1, 'Pride and Prejudice - Volume 1', 1, '1813-01-28 00:00:00.000000', 3, '2023-01-01 00:00:00.000000', '2023-01-01 00:00:00.000000'),
                                                                                                                                                              (7, 950000, 229, 200, 2, 'Pride and Prejudice - Volume 2', 1, '1813-01-28 00:00:00.000000', 3, '2023-01-01 00:00:00.000000', '2023-01-01 00:00:00.000000'),
                                                                                                                                                              (8, 900000, 222, 200, 3, 'Pride and Prejudice - Volume 3', 1, '1813-01-28 00:00:00.000000', 3, '2023-01-01 00:00:00.000000', '2023-01-01 00:00:00.000000');

-- favorite novel
INSERT INTO favorite_novel (id, user_id, novel_id, read_page, create_dt, update_dt) VALUES
                                                                                        (1, 1, 1, 3, '2023-01-01 00:00:00.000000', '2023-01-01 00:00:00.000000'),
                                                                                        (2, 1, 2, 10, '2023-01-02 00:00:00.000000', '2023-01-02 00:00:00.000000'),
                                                                                        (3, 1, 2, 20, '2023-01-03 00:00:00.000000', '2023-01-03 00:00:00.000000'),
                                                                                        (4, 2, 3, 20, '2023-01-04 00:00:00.000000', '2023-01-04 00:00:00.000000');


-- episode purchase
INSERT INTO episode_purchase (id, user_id, episode_id, create_dt, update_dt) VALUES
                                                                                        (1, 1, 1, '2023-01-01 00:00:00.000000', '2023-01-01 00:00:00.000000'),
                                                                                        (2, 1, 5, '2023-01-02 00:00:00.000000', '2023-01-02 00:00:00.000000'),
                                                                                        (3, 1, 6, '2023-01-03 00:00:00.000000', '2023-01-03 00:00:00.000000'),
                                                                                        (4, 2, 7, '2023-01-04 00:00:00.000000', '2023-01-04 00:00:00.000000');
