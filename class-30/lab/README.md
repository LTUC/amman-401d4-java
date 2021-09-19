# Requesting & Caching Data

## Overview

Today, you'll refactor your model layer again! Now, you'll be updating task information on a remote server, and using the data from that server to cache your data locally.

## Setup

Continue working in your `taskmaster` repository.

## Resources

* [OkHttp Overview](https://square.github.io/okhttp){:_target="blank"}

## Feature Tasks

### Task Model, Room, and The Internet

When your application is opened, make a request to the provided backend server URL to fetch Task data. Display that Task data in your RecyclerView.

### Add Task Form

Modify your Add Task form to post the entered task data to the server.

### Homepage

Ensure that your homepage refreshes the Tasks shown after a Task has been added.

## Documentation

Update your daily change log with today's changes.

## Testing

In a future lecture, we'll talk about how to test Android UI using Espresso. For now, ensure that you're writing good unit tests for anything unit-testable in your code.

## Stretch Goals

* Allow the user to specify on their settings page how many tasks should be shown on the homepage. Use this to dynamically display as many tasks as the user requests for display in the RecyclerView.
* Allow the user to specify on their settings page how the tasks should be sorted (by title, status, or creation time).
* Allow the user to delete a task from the task detail page. Delete that task both on the server and locally.

## Submission Instructions

* Continue working in your `taskmaster` repo.
* Work on a non-master branch and make commits appropriately.
* Update your README with your changes for today and screenshot of your work.
* Create a pull request to your master branch with your work for this lab.
* Submit the link to that pull request on Canvas. Add a comment with the amount of time you spent on this assignment.

## Grading Rubric

* 2 pts  Displaying tasks from the internet
* 2 pts  Saving tasks to the internet
* 1 pt   Appropriate refreshes of the "my tasks" view, without duplicating data
* 1 pt   README with description, screenshots, and daily change log
