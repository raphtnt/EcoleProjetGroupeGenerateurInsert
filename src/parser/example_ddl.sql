CREATE TABLE users (
                       id INT PRIMARY KEY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       email VARCHAR(255) UNIQUE,
                       created_at TIMESTAMP
);

CREATE TABLE posts (
                       id INT PRIMARY KEY,
                       user_id INT,
                       title VARCHAR(255),
                       content TEXT,
                       FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE comments (
                          id INT PRIMARY KEY,
                          post_id INT,
                          user_id INT,
                          comment TEXT,
                          FOREIGN KEY (post_id) REFERENCES posts(id),
                          FOREIGN KEY (user_id) REFERENCES users(id)
);
