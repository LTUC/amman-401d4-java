# CodeFellowship Profiles with Login

## Overview
Today, you'll add login to the CodeFellowship app, and allow users to create posts.

## Setup
Continue working in your `codefellowship` repo.

## Resources
- [Spring Auth cheat sheet](https://github.com/codefellows/seattle-java-401d2/blob/master/SpringAuthCheatSheet.md)

## Feature Tasks
Allow users to log in to `CodeFellowship` and create posts.
- Using the above cheat sheet, add the ability for users to log in to your app.
    - Upon logging in, users should be taken to a `/myprofile` route that displays their information.
- Ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.
- Ensure that user registration also logs users into your app automatically.
- Add a `Post` entity to your app.
    - A `Post` has a `body` and a `createdAt` timestamp.
    - A logged-in user should be able to create a `Post`, and a post should belong to the user that created it.
        - hint: this is a relationship between two pieces of data
- A user's posts should be visible on their profile page.
- When a user is logged in, the app should display the user's username on every page (probably in the heading).

## Stretch Goals
- A logged-in user should be able to edit their profile. (Double stretch: allow them to change their passwords if they enter their current password.)
- An "admin" user should be able to edit anyone's profile.

## Submission Instructions
* Continue working in your repository for this project.
* Work on a non-master branch and make commits appropriately.
* Ensure that you can run a single Gradle command to launch your server. Include that command, as well as a description of the project and the state of each of the feature tasks, in a README.md file.
* Create a pull request to your master branch with your work for this lab.
* Submit the link to that pull request on Canvas. Add a comment with the amount of time you spent on this assignment.

## Grading Rubric
- 1pt / Ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.
- 1pt / Upon signing up or logging in, users should be taken to a `/myprofile` route that displays their information. Their profile page should display their posts.
- 1pt / Add a Post entity to your app with a body and a createdAt timestamp.
- 1pt / A logged-in user should be able to create a Post, and a post should belong to the user that created it.
- 1pt When a user is logged in, the app should display the user’s username on every page (probably in the heading).
- 1pt README
