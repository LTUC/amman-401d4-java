# Notifications

## Overview

Today, you will add the ability for push notifications to be delivered to your app from the cloud.

## Setup

Continue working in your `taskmaster` repository.

## Resources

* [Amplify Getting Started](https://aws-amplify.github.io/docs/){:_target="blank"}

## Feature Tasks

### Notifications on Task Creation

When a new task is created within a team, alert all users who are a part of that team about that new task.

There are several steps involved in allowing this to work:

* Store which team a user is part of in the cloud
* Add a Lambda trigger on task creation
* Use SNS to send a notification as part of that Lambda

## Documentation

Update your daily change log with today's changes.

## Submission Instructions

* Continue working in your `taskmaster` repo.
* Work on a non-master branch and make commits appropriately.
* Update your README with your changes for today and screenshot of your work.
* Create a pull request to your master branch with your work for this lab.
* Submit the link to that pull request on Canvas. Add a comment with the amount of time you spent on this assignment.

## Grading Rubric

* 2 pts  Model updates and device registration
* 2 pts  Lambda code for sending notifications
* 1 pt   Screenshot of a successful notification
* 1 pt   README with description, screenshots, and daily change log
