insert into exercise (id, category, level, name, description, audio_url, points) values (1, 0, 0, 'Chromatic - Exercise 1', 'Chromatic basics', 'url', 20);

insert into song (artist, genre, name, id) values ('Metallica', 'Metal', 'Orion', 1);
insert into song (artist, genre, name, id) values ('Nirvana', 'Grunge', 'Polly', 2);
insert into song (artist, genre, name, id) values ('Metallica', 'metal', 'One', 3);

insert into tab (id, song_id, level, tab_type, guitar_type, tuning, rate, rate_number, url) values (1, 1, 2, 1, 0, 0, 0, 0, 'https://example.com/tab1');
insert into tab (id, song_id, level, tab_type, guitar_type, tuning, rate, rate_number, url) values (2, 2, 0, 1, 0, 0, 0, 0, 'https://example.com/tab1');
insert into tab (id, song_id, level, tab_type, guitar_type, tuning, rate, rate_number, url) values (3, 3, 2, 1, 0, 0, 0, 0, 'https://example.com/tab1');


