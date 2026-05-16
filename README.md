# AP Project Fall 2021 - Social Media Desktop App

A Java Swing desktop application that implements the core flows of a small Instagram-like social network. The project was built as an Advanced Programming course project and uses MySQL for persistent storage.

Users can create an account, log in, search for other users, follow and unfollow profiles, publish image posts with captions, like posts, write comments, send private messages, view their followers/followings, and delete their account.

## Features

- User registration and login with basic validation
- Personalized feed from followed users
- User search by username
- Public profile pages with post, follower, and following counts
- Follow and unfollow actions
- Image post creation using a local file chooser
- Post detail page with image preview, caption, date, likes, and comments
- Like/unlike support
- Commenting on posts
- Private message sending and message history views
- Profile information screens in the organized `social media/java/User` source copy
- Account deletion that removes user data from related tables

## Tech Stack

- Java
- Swing / NetBeans GUI Builder forms
- JDBC
- MySQL
- MySQL Connector/J

## Project Structure

```text
.
+-- *.java                         # Main Java Swing source files in the default package
+-- *.form                         # NetBeans GUI Builder form files
+-- social media/
    +-- java/
    |   +-- Post/                  # Post, new-post, and my-post views
    |   +-- User/                  # Login, signup, profile, search, messages, info, follow views
    +-- Forms/User forms/          # Organized NetBeans form files
    +-- images/                    # UI image resources
```

The root source files contain the main runnable copy of the app. The `social media/java` folder contains an organized copy of the same project and includes additional profile-information screens.

## Main Classes

| Class | Purpose |
| --- | --- |
| `Login` | Application entry point, authentication, and current-user tracking |
| `SignUp` | Account creation and duplicate username checks |
| `ProfilePage` | Main feed and navigation after login |
| `SearchBox` | Username search and navigation to user pages |
| `User` | Public profile page, follow/unfollow, and post list |
| `AllMyPost` | Current user's post management |
| `NewPost` | Image upload and caption creation |
| `Post` | Post image, caption, date, likes, comments, and comment form |
| `FollowerPart` / `FollowingPart` | Follower and following lists |
| `SendMessage` / `Messages` | Private messaging flow |
| `DeleteAccount` | Deletes account-related data from multiple tables |
| `Information` / `EditInformation` | Profile info display and editing in `social media/java/User` |

## Database Setup

The code connects to a local MySQL database named `instagram` using:

```text
jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false
user: root
password: empty
```

Create the database and tables before running the app. The column name `messsage` is intentionally spelled with three `s` characters because the current Java code queries that exact column name.

```sql
CREATE DATABASE IF NOT EXISTS instagram;
USE instagram;

CREATE TABLE IF NOT EXISTS `user` (
  username VARCHAR(64) PRIMARY KEY,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS info (
  username VARCHAR(64) NOT NULL,
  phoneNumber VARCHAR(32),
  age VARCHAR(8),
  bio TEXT,
  fullName VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS post (
  username VARCHAR(64) NOT NULL,
  text TEXT,
  picture LONGBLOB,
  date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS follow (
  usernameno1 VARCHAR(64) NOT NULL,
  usernameno2 VARCHAR(64) NOT NULL,
  PRIMARY KEY (usernameno1, usernameno2)
);

CREATE TABLE IF NOT EXISTS likepost (
  usernameLiker VARCHAR(64) NOT NULL,
  usernameLiked VARCHAR(64) NOT NULL,
  post TEXT NOT NULL,
  date VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS comments (
  usernameCommenter VARCHAR(64) NOT NULL,
  usernamePoster VARCHAR(64) NOT NULL,
  post TEXT NOT NULL,
  date VARCHAR(64) NOT NULL,
  comment TEXT NOT NULL,
  dateComment TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS chat (
  usernameSender VARCHAR(64) NOT NULL,
  usernameReceiver VARCHAR(64) NOT NULL,
  messsage TEXT NOT NULL,
  date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## How to Run

1. Install a JDK.
2. Install and start MySQL.
3. Create the `instagram` database using the SQL above.
4. Add MySQL Connector/J to the classpath. The imports use `com.mysql.jdbc.*`, so Connector/J 5.1.x is the most compatible choice.
5. Run the `Login` class.

Example from the repository root on macOS/Linux:

```bash
javac -cp ".:/path/to/mysql-connector-java-5.1.x.jar" *.java
java -cp ".:social media:/path/to/mysql-connector-java-5.1.x.jar" Login
```

On Windows, use `;` instead of `:` in the classpath:

```bat
javac -cp ".;C:\path\mysql-connector-java-5.1.x.jar" *.java
java -cp ".;social media;C:\path\mysql-connector-java-5.1.x.jar" Login
```

You can also open the files in NetBeans, keep the `.form` files beside their matching `.java` files, add MySQL Connector/J as a library, and run `Login`.

## Notes

- There is no Maven or Gradle build file in the repository; the project is currently a plain Java/NetBeans Swing project.
- Image assets are loaded from `/images/...`, so the `social media` folder should be available on the runtime classpath.
- Passwords are stored as plain text in the current course-project implementation. A production version should hash passwords and move database credentials out of source code.
- Several database operations are implemented directly in Swing event handlers. A future refactor could move JDBC access into a small data-access layer.

## Possible Improvements

- Add a Maven or Gradle build file
- Add a database migration script or seed data
- Hash user passwords
- Replace repeated JDBC connection code with reusable helpers
- Add validation for empty image paths, empty comments, and unselected table rows
- Add screenshots of the main screens to the README
