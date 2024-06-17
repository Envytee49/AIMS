use aims;



CREATE TRIGGER media_create 
BEFORE INSERT ON `media` 
FOR EACH ROW 
SET NEW.created_at = NOW(), 
NEW.updated_at = NOW();

CREATE TRIGGER user_create 
BEFORE INSERT ON `users` 
FOR EACH ROW 
SET NEW.created_at = NOW(), 
NEW.updated_at = NOW();

INSERT INTO media (title, category, value, price, quantity, imageURL, rushOrderAvailable, weight) VALUES

-- BOOK

('Remi', 'Book', 100, 205000, 7, '/com/example/aims/assets/book/1.jpg', 1, 0.4),

('Harry Potter', 'Book', 100, 260000, 8, '/com/example/aims/assets/book/2.jpg', 0, 0.6),

('The Hitchhiker', 'Book', 100, 320000, 6, '/com/example/aims/assets/book/3.jpg', 1, 0.8),

('The Witches', 'Book', 100, 185000, 9, '/com/example/aims/assets/book/4.jpg', 1, 0.5),

('The Great Indian Novel', 'Book', 100, 400000, 5, '/com/example/aims/assets/book/5.jpg', 0, 0.7),

('The promise', 'Book', 100, 150000, 10, '/com/example/aims/assets/book/6.jpg', 1, 0.9),

('Like Brothers', 'Book', 100, 275000, 7, '/com/example/aims/assets/book/7.jpg', 0, 0.3),

('Big Book', 'Book', 100, 310000, 8, '/com/example/aims/assets/book/8.jpg', 1, 0.6),

('Fear and Loathing', 'Book', 100, 195000, 6, '/com/example/aims/assets/book/9.jpg', 1, 0.4),

('Misery', 'Book', 100, 225000, 9, '/com/example/aims/assets/book/10.jpg', 1, 0.8),

('1984', 'Book', 120, 420000, 5, '/com/example/aims/assets/book/11.jpg', 1, 0.7),

('To Kill a Mockingbird', 'Book', 110, 450000, 8, '/com/example/aims/assets/book/12.jpg', 1, 0.5),

('Pride and Prejudice', 'Book', 130, 370000, 6, '/com/example/aims/assets/book/13.jpg', 0, 0.9),

('The Great Gatsby', 'Book', 150, 400000, 3, '/com/example/aims/assets/book/14.jpg', 1, 0.2),

('Moby Dick', 'Book', 170, 380000, 7, '/com/example/aims/assets/book/15.jpg', 0, 0.3),

('The Catcher in the Rye', 'Book', 180, 420000, 2, '/com/example/aims/assets/book/16.jpg', 1, 0.4),

('Jane Eyre', 'Book', 190, 430000, 4, '/com/example/aims/assets/book/17.jpg', 0, 0.6),

('War and Peace', 'Book', 200, 440000, 9, '/com/example/aims/assets/book/18.jpg', 1, 0.7),

('Wuthering Heights', 'Book', 210, 460000, 1, '/com/example/aims/assets/book/19.jpg', 0, 0.5),

('Crime and Punishment', 'Book', 220, 470000, 5, '/com/example/aims/assets/book/20.jpg', 1, 0.8),

('The Odyssey', 'Book', 230, 490000, 6, '/com/example/aims/assets/book/21.jpg', 0, 0.9),

('Brave New World', 'Book', 240, 410000, 4, '/com/example/aims/assets/book/22.jpg', 1, 0.3),

('The Brothers Karamazov', 'Book', 250, 420000, 3, '/com/example/aims/assets/book/23.jpg', 0, 0.6),

('Anna Karenina', 'Book', 260, 480000, 8, '/com/example/aims/assets/book/24.jpg', 1, 0.4),

('Heart of Darkness', 'Book', 270, 430000, 7, '/com/example/aims/assets/book/25.jpg', 0, 0.5),

('The Grapes of Wrath', 'Book', 280, 460000, 2, '/com/example/aims/assets/book/26.jpg', 1, 0.6),

('Madame Bovary', 'Book', 290, 440000, 1, '/com/example/aims/assets/book/27.jpg', 0, 0.7),

('Ulysses', 'Book', 300, 450000, 10, '/com/example/aims/assets/book/28.jpg', 1, 0.8),

('The Divine Comedy', 'Book', 310, 470000, 5, '/com/example/aims/assets/book/29.jpg', 0, 0.9),

('Les Misérables', 'Book', 320, 490000, 9, '/com/example/aims/assets/book/30.jpg', 1, 0.4),

-- DVD

('Hotel Transylvaia', 'DVD', 100, 300000, 7, '/com/example/aims/assets/dvd/1.jpg', 0, 0.7),

('Train your dragon', 'DVD', 100, 280000, 8, '/com/example/aims/assets/dvd/2.jpg', 1, 0.4),

('Spritual KungFu', 'DVD', 100, 450000, 6, '/com/example/aims/assets/dvd/3.jpg', 1, 0.5),

('Hunger Games', 'DVD', 100, 210000, 9, '/com/example/aims/assets/dvd/4.jpg', 1, 0.6),

('Dracula', 'DVD', 100, 370000, 5, '/com/example/aims/assets/dvd/5.jpg', 0, 0.8),

('Dark Water', 'DVD', 100, 320000, 10, '/com/example/aims/assets/dvd/6.jpg', 1, 0.7),

('Dead Still', 'DVD', 100, 275000, 7, '/com/example/aims/assets/dvd/7.jpg', 1, 0.4),

('Maleficent', 'DVD', 100, 360000, 8, '/com/example/aims/assets/dvd/8.jpg', 1, 0.9),

('Mortal', 'DVD', 100, 480000, 6, '/com/example/aims/assets/dvd/9.jpg', 1, 0.5),

('The Godfather', 'DVD', 140, 410000, 6, '/com/example/aims/assets/dvd/10.jpg', 1, 0.3),

('The Shawshank Redemption', 'DVD', 150, 430000, 4, '/com/example/aims/assets/dvd/11.jpg', 0, 0.6),

('Schindler\'s List', 'DVD', 160, 420000, 5, '/com/example/aims/assets/dvd/12.jpg', 1, 0.8),

('Raging Bull', 'DVD', 170, 450000, 7, '/com/example/aims/assets/dvd/13.jpg', 0, 0.7),

('Casablanca', 'DVD', 180, 380000, 3, '/com/example/aims/assets/dvd/14.jpg', 1, 0.5),

('Citizen Kane', 'DVD', 190, 440000, 8, '/com/example/aims/assets/dvd/15.jpg', 1, 0.4),

('Gone with the Wind', 'DVD', 200, 470000, 2, '/com/example/aims/assets/dvd/16.jpg', 0, 0.6),

('The Wizard of Oz', 'DVD', 210, 490000, 1, '/com/example/aims/assets/dvd/17.jpg', 1, 0.8),

('One Flew Over the Cuckoo\'s Nest', 'DVD', 220, 460000, 4, '/com/example/aims/assets/dvd/18.jpg', 0, 0.7),

('Lawrence of Arabia', 'DVD', 230, 420000, 5, '/com/example/aims/assets/dvd/19.jpg', 1, 0.3),

('Vertigo', 'DVD', 240, 450000, 3, '/com/example/aims/assets/dvd/20.jpg', 0, 0.5),

('Psycho', 'DVD', 250, 470000, 6, '/com/example/aims/assets/dvd/21.jpg', 1, 0.9),

('The Godfather Part II', 'DVD', 260, 440000, 7, '/com/example/aims/assets/dvd/22.jpg', 1, 0.4),

('On the Waterfront', 'DVD', 270, 460000, 2, '/com/example/aims/assets/dvd/23.jpg', 0, 0.8),

('Sunset Boulevard', 'DVD', 280, 410000, 5, '/com/example/aims/assets/dvd/24.jpg', 1, 0.5),

('Forrest Gump', 'DVD', 290, 420000, 4, '/com/example/aims/assets/dvd/25.jpg', 0, 0.6),

('The Sound of Music', 'DVD', 300, 430000, 3, '/com/example/aims/assets/dvd/26.jpg', 1, 0.7),

('12 Angry Men', 'DVD', 310, 480000, 8, '/com/example/aims/assets/dvd/27.jpg', 1, 0.4),

('West Side Story', 'DVD', 320, 490000, 2, '/com/example/aims/assets/dvd/28.jpg', 0, 0.6),

('Star Wars: Episode IV - A New Hope', 'DVD', 330, 460000, 7, '/com/example/aims/assets/dvd/29.jpg', 1, 0.8),

('E.T. the Extra-Terrestrial', 'DVD', 340, 470000, 6, '/com/example/aims/assets/dvd/30.jpg', 0, 0.5),

-- CD

('The Collector', 'CD', 100, 150000, 9, '/com/example/aims/assets/cd/1.jpg', 1, 0.4),

('The Walking Dead', 'CD', 100, 200000, 8, '/com/example/aims/assets/cd/2.jpg', 0, 0.6),

('Dead Famous People', 'CD', 100, 380000, 7, '/com/example/aims/assets/cd/3.jpg', 1, 0.7),

('Thriller', 'CD', 100, 400000, 5, '/com/example/aims/assets/cd/4.jpg', 1, 0.8),

('Back in Black', 'CD', 110, 450000, 6, '/com/example/aims/assets/cd/5.jpg', 0, 0.5),

('The Dark Side of the Moon', 'CD', 120, 420000, 7, '/com/example/aims/assets/cd/6.jpg', 1, 0.9),

('The Bodyguard', 'CD', 130, 480000, 4, '/com/example/aims/assets/cd/7.jpg', 0, 0.3),

('Rumours', 'CD', 140, 410000, 3, '/com/example/aims/assets/cd/8.jpg', 1, 0.6),

('Saturday Night Fever', 'CD', 150, 430000, 8, '/com/example/aims/assets/cd/9.jpg', 1, 0.4),

('Come On Over', 'CD', 160, 470000, 9, '/com/example/aims/assets/cd/10.jpg', 0, 0.7),

('Abbey Road', 'CD', 170, 490000, 1, '/com/example/aims/assets/cd/11.jpg', 1, 0.5),

('Hotel California', 'CD', 180, 450000, 2, '/com/example/aims/assets/cd/12.jpg', 0, 0.8),

('Havana', 'CD', 190, 470000, 5, '/com/example/aims/assets/cd/13.jpg', 1, 0.6),

('1989', 'CD', 200, 410000, 4, '/com/example/aims/assets/cd/14.jpg', 0, 0.9),

('Led Zeppelin IV', 'CD', 210, 440000, 3, '/com/example/aims/assets/cd/15.jpg', 1, 0.4),

('Bad', 'CD', 220, 420000, 7, '/com/example/aims/assets/cd/16.jpg', 1, 0.5),

('Greatest Hits', 'CD', 230, 460000, 6, '/com/example/aims/assets/cd/17.jpg', 0, 0.8),

('Their Greatest Hits', 'CD', 240, 450000, 8, '/com/example/aims/assets/cd/18.jpg', 1, 0.6),

('Goodbye Yellow Brick Road', 'CD', 250, 480000, 9, '/com/example/aims/assets/cd/19.jpg', 0, 0.4),

('Legend', 'CD', 260, 470000, 2, '/com/example/aims/assets/cd/20.jpg', 1, 0.7),

('The Wall', 'CD', 270, 430000, 5, '/com/example/aims/assets/cd/21.jpg', 0, 0.9),

('Divide', 'CD', 280, 420000, 4, '/com/example/aims/assets/cd/22.jpg', 1, 0.5),

('Born in the U.S.A.', 'CD', 290, 410000, 7, '/com/example/aims/assets/cd/23.jpg', 1, 0.8),

('Greatest Hits II', 'CD', 300, 440000, 6, '/com/example/aims/assets/cd/24.jpg', 0, 0.4),

('BELIEVE', 'CD', 310, 470000, 8, '/com/example/aims/assets/cd/25.jpg', 1, 0.6),

('Purple Rain', 'CD', 320, 490000, 1, '/com/example/aims/assets/cd/26.jpg', 1, 0.5),

('Yours Truly', 'CD', 330, 460000, 3, '/com/example/aims/assets/cd/27.jpg', 0, 0.7),

('Back to Black', 'CD', 340, 420000, 5, '/com/example/aims/assets/cd/28.jpg', 1, 0.9),

('Nicky', 'CD', 350, 410000, 4, '/com/example/aims/assets/cd/29.jpg', 0, 0.3),

('A Night at the Opera', 'CD', 360, 430000, 2, '/com/example/aims/assets/cd/30.jpg', 1, 0.4);


-- Insert into the Book table
INSERT INTO books (media_id, author, coverType, publisher, publishDate, numOfPages, language, genre) VALUES
(1, 'J.K. Rowling', 'Hardcover', 'Bloomsbury', '2020-01-15', 350, 'English', 'Fantasy'),
(2, 'Douglas Adams', 'Paperback', 'Penguin Books', '1997-06-26', 320, 'English', 'Adventure'),
(3, 'Roald Dahl', 'Hardcover', 'Random House', '2001-05-12', 280, 'English', 'Science Fiction'),
(4, 'Shashi Tharoor', 'Paperback', 'HarperCollins', '1983-10-15', 240, 'English', 'Horror'),
(5, 'Philip Roth', 'Hardcover', 'Simon & Schuster', '1989-03-29', 500, 'English', 'Historical Fiction'),
(6, 'Ta-Nehisi Coates', 'Paperback', 'Spiegel & Grau', '2015-07-14', 300, 'English', 'Biography'),
(7, 'J.R.R. Tolkien', 'Hardcover', 'Houghton Mifflin', '1954-07-29', 450, 'English', 'Fantasy'),
(8, 'George Orwell', 'Paperback', 'Secker & Warburg', '1949-06-08', 328, 'English', 'Dystopian'),
(9, 'Hunter S. Thompson', 'Hardcover', 'Random House', '1971-11-11', 200, 'English', 'Gonzo Journalism'),
(10, 'Stephen King', 'Paperback', 'Viking Press', '1987-06-08', 350, 'English', 'Thriller'),
(11, 'George Orwell', 'Hardcover', 'Secker & Warburg', '1949-06-08', 328, 'English', 'Dystopian'),
(12, 'Harper Lee', 'Paperback', 'J.B. Lippincott & Co.', '1960-07-11', 281, 'English', 'Fiction'),
(13, 'Jane Austen', 'Hardcover', 'T. Egerton', '1813-01-28', 279, 'English', 'Romance'),
(14, 'F. Scott Fitzgerald', 'Paperback', 'Charles Scribner\'s Sons', '1925-04-10', 180, 'English', 'Fiction'),
(15, 'Herman Melville', 'Hardcover', 'Harper & Brothers', '1851-10-18', 635, 'English', 'Adventure'),
(16, 'J.D. Salinger', 'Paperback', 'Little, Brown and Company', '1951-07-16', 214, 'English', 'Fiction'),
(17, 'Charlotte Brontë', 'Hardcover', 'Smith, Elder & Co.', '1847-10-16', 500, 'English', 'Romance'),
(18, 'Leo Tolstoy', 'Paperback', 'The Russian Messenger', '1869-01-01', 1225, 'Russian', 'Historical Fiction'),
(19, 'Emily Brontë', 'Hardcover', 'Thomas Cautley Newby', '1847-12-01', 342, 'English', 'Gothic Fiction'),
(20, 'Fyodor Dostoevsky', 'Paperback', 'The Russian Messenger', '1866-01-01', 430, 'Russian', 'Philosophical Fiction'),
(21, 'Homer', 'Hardcover', 'Unknown', '1945-01-01', 500, 'Greek', 'Epic Poetry'),
(22, 'Aldous Huxley', 'Paperback', 'Chatto & Windus', '1932-01-01', 311, 'English', 'Dystopian'),
(23, 'Fyodor Dostoevsky', 'Hardcover', 'The Russian Messenger', '1880-01-01', 796, 'Russian', 'Philosophical Fiction'),
(24, 'Leo Tolstoy', 'Paperback', 'The Russian Messenger', '1877-01-01', 864, 'Russian', 'Romance'),
(25, 'Joseph Conrad', 'Hardcover', 'Blackwood\'s Magazine', '1899-01-01', 122, 'English', 'Novella'),
(26, 'John Steinbeck', 'Paperback', 'The Viking Press-James Lloyd', '1939-04-14', 464, 'English', 'Historical Fiction'),
(27, 'Gustave Flaubert', 'Hardcover', 'Revue de Paris', '1856-01-01', 329, 'French', 'Literary Realism'),
(28, 'James Joyce', 'Paperback', 'Sylvia Beach', '1922-02-02', 730, 'English', 'Modernist Novel'),
(29, 'Dante Alighieri', 'Hardcover', 'John Murray', '1320-01-01', 798, 'Italian', 'Epic Poetry'),
(30, 'Victor Hugo', 'Paperback', 'A. Lacroix, Verboeckhoven & Cie', '1862-01-01', 1463, 'French', 'Historical Fiction');
-- Insert into the DVD table
INSERT INTO dvds (media_id, discType, director, runtime, studio, subtitles, releasedDate, language, genre) VALUES
(31, 'Blu-ray', 'Frank Darabont', 142, 'Columbia Pictures', 'English, Spanish', '1994-09-22', 'English', 'Drama'),
(32, 'DVD', 'Francis Ford Coppola', 175, 'Paramount Pictures', 'English, Italian', '1972-03-24', 'English', 'Crime'),
(33, 'Blu-ray', 'Christopher Nolan', 152, 'Warner Bros.', 'English, French', '2008-07-18', 'English', 'Action'),
(34, 'DVD', 'Quentin Tarantino', 154, 'Miramax Films', 'English, Spanish', '1994-10-14', 'English', 'Crime'),
(35, 'Blu-ray', 'David Fincher', 139, '20th Century Fox', 'English, French', '1999-10-15', 'English', 'Drama'),
(36, 'DVD', 'Christopher Nolan', 148, 'Warner Bros.', 'English, Japanese', '2010-07-16', 'English', 'Science Fiction'),
(37, 'Blu-ray', 'Robert Zemeckis', 142, 'Paramount Pictures', 'English, German', '1994-07-06', 'English', 'Drama'),
(38, 'DVD', 'Lana Wachowski, Lilly Wachowski', 136, 'Warner Bros.', 'English, Spanish', '1999-03-31', 'English', 'Science Fiction'),
(39, 'Blu-ray', 'Peter Jackson', 178, 'New Line Cinema', 'English, Elvish', '2001-12-19', 'English', 'Fantasy'),
(40, 'DVD', 'Stanley Kubrick', 148, 'Warner Bros.', 'English, French', '1968-04-02', 'English', 'Science Fiction'),
(41, 'Blu-ray', 'Ridley Scott', 117, 'Warner Bros.', 'English, Spanish', '1982-06-25', 'English', 'Science Fiction'),
(42, 'DVD', 'Francis Ford Coppola', 200, 'Paramount Pictures', 'English, Italian', '1974-12-20', 'English', 'Crime'),
(43, 'Blu-ray', 'Elia Kazan', 108, 'Columbia Pictures', 'English, French', '1954-06-24', 'English', 'Drama'),
(44, 'DVD', 'Billy Wilder', 110, 'Paramount Pictures', 'English, Spanish', '1950-08-10', 'Drama', 'English'),
(45, 'Blu-ray', 'Robert Zemeckis', 142, 'Paramount Pictures', 'English, German', '1994-07-06', 'English', 'Drama'),
(46, 'DVD', 'Robert Wise', 174, '20th Century Fox', 'English, Spanish', '1965-03-02', 'English', 'Musical'),
(47, 'Blu-ray', 'Sidney Lumet', 96, 'United Artists', 'English, Spanish', '1957-04-10', 'English', 'Drama'),
(48, 'DVD', 'Robert Wise', 152, 'United Artists', 'English, Spanish','1961-10-18',  'Musical', 'English'),
(49, 'Blu-ray', 'George Lucas', 121, '20th Century Fox', 'English, Spanish', '1977-05-25', 'English', 'Science Fiction'),
(50, 'DVD', 'Steven Spielberg', 115, 'Universal Pictures', 'English, Spanish', '1982-06-11', 'English', 'Science Fiction'),
(51, 'Blu-ray', 'Alfred Hitchcock', 109, 'Paramount Pictures', 'English, French', '1960-09-08', 'English', 'Horror'),
(52, 'DVD', 'Victor Fleming', 238, 'Loew\'s Inc.', 'English, Spanish', '1939-12-15', 'English', 'Epic Romance'),
(53, 'Blu-ray', 'Orson Welles', 119, 'RKO Radio Pictures', 'English, French', '1941-09-05', 'English', 'Mystery'),
(54, 'DVD', 'Francis Ford Coppola', 162, 'Paramount Pictures', 'English, Italian', '1974-12-12', 'English', 'Crime'),
(55, 'Blu-ray', 'Michael Curtiz', 102, 'Warner Bros.', 'English, French', '1942-11-26', 'English', 'Romance'),
(56, 'DVD', 'Elia Kazan', 108, 'Columbia Pictures', 'English, Spanish', '1954-03-30', 'English', 'Drama'),
(57, 'Blu-ray', 'David Lean', 227, 'Columbia Pictures', 'English, Arabic', '1962-12-11', 'English', 'Historical Drama'),
(58, 'DVD', 'Milos Forman', 133, 'United Artists', 'English, Spanish', '1975-11-19', 'English', 'Drama'),
(59, 'Blu-ray', 'Alfred Hitchcock', 128, 'Paramount Pictures', 'English, French', '1958-05-09', 'English', 'Thriller'),
(60, 'DVD', 'Robert Wise', 172, '20th Century Fox', 'English, Spanish', '1956-06-23', 'English', 'Musical');


-- Insert into the CD table
INSERT INTO cds (media_id, artist, recordLabel, musicType, releasedDate) VALUES
(61, 'The Beatles', 'Apple Records', 'Rock', '2019-09-27'),
(62, 'Michael Jackson', 'Epic Records', 'Pop', '1982-11-30'),
(63, 'AC/DC', 'Atlantic Records', 'Rock', '1980-07-25'),
(64, 'Pink Floyd', 'Harvest Records', 'Progressive Rock', '1973-03-01'),
(65, 'Whitney Houston', 'Arista Records', 'R&B/Soul', '1992-11-17'),
(66, 'Fleetwood Mac', 'Warner Bros. Records', 'Rock', '1977-02-04'),
(67, 'Bee Gees', 'RSO Records', 'Disco', '1977-11-15'),
(68, 'Shania Twain', 'Mercury Nashville', 'Country', '1997-11-04'),
(69, 'The Beatles', 'Apple Records', 'Rock', '1969-09-26'),
(70, 'The Eagles', 'Asylum Records', 'Rock', '1976-12-08'),
(71, 'Adele', 'XL Recordings', 'Pop', '2011-01-24'),
(72, 'The Beatles', 'Apple Records', 'Rock', '2000-11-13'),
(73, 'Led Zeppelin', 'Atlantic Records', 'Rock', '1971-11-08'),
(74, 'Michael Jackson', 'Epic Records', 'Pop', '1987-08-31'),
(75, 'Queen', 'EMI Records', 'Rock', '1981-10-26'),
(76, 'The Eagles', 'Asylum Records', 'Rock', '1976-02-17'),
(77, 'Elton John', 'MCA Records', 'Rock', '1973-10-05'),
(78, 'Bob Marley and the Wailers', 'Island Records', 'Reggae', '1984-05-08'),
(79, 'Pink Floyd', 'Columbia Records', 'Progressive Rock', '1979-11-30'),
(80, 'The Eagles', 'Asylum Records', 'Rock', '2017-11-24'),
(81, 'Bruce Springsteen', 'Columbia Records', 'Rock', '1984-06-04'),
(82, 'Queen', 'Parlophone Records', 'Rock', '1991-10-28'),
(83, 'The Beatles', 'Apple Records', 'Rock', '2019-09-27'),
(84, 'Prince', 'Warner Bros. Records', 'Pop', '1984-06-25'),
(85, 'Nirvana', 'DGC Records', 'Grunge', '1991-09-24'),
(86, 'Amy Winehouse', 'Island Records', 'Soul', '2006-10-27'),
(87, 'Michael Jackson', 'Epic Records', 'Pop', '2008-02-08'),
(88, 'Queen', 'EMI Records', 'Rock', '1975-11-21'),
(89, 'Bruce Springsteen', 'Columbia Records', 'Rock', '1984-06-04'),
(90, 'Queen', 'Parlophone Records', 'Rock', '1991-10-28');


INSERT INTO users (name, email, address, phone, username, password, role, user_status) VALUES
('admin', 'admine@example.com', 'hanoi', '0123456789', 'admin', '1', 'ADMIN', 'BLOCKED'),
('pm', 'pm@example.com', 'hanoi', '0123456789', 'pm', '1', 'PRODUCT_MANAGER', 'BLOCKED');

-- Insert records into administrators table
INSERT INTO administrators (id) VALUES
(1);

-- Insert records into product_managers table
INSERT INTO product_managers (id) VALUES
(2);




