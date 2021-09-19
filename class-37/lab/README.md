# S3 Uploads

## Overview

Today, your app will allow users to upload files related to tasks, like screenshots or log files.

## Setup

Continue working in your `taskmaster` repository.

## Resources

* [Amplify Getting Started](https://aws-amplify.github.io/docs/){:_target="blank"}
* [Android File Picker](https://developer.android.com/guide/topics/providers/document-provider){:_target="blank"}
* [Amplify S3](https://aws-amplify.github.io/docs/android/storage){:_target="blank"}

## Feature Tasks

### Uploads

On the "Add a Task" activity, allow users to optionally select a file to attach to that task. If a user attaches a file to a task, that file should be uploaded to S3, and associated with that task.

### Displaying Files

On the Task detail activity, if there is a file that is an image associated with a particular Task, that image should be displayed within that activity. (If the file is any other type, you should display a link to it.)

## Documentation

Update your daily change log with today's changes.

## Submission Instructions

* Continue working in your `taskmaster` repo.
* Work on a non-master branch and make commits appropriately.
* Update your README with your changes for today and screenshot of your work.
* Create a pull request to your master branch with your work for this lab.
* Submit the link to that pull request on Canvas. Add a comment with the amount of time you spent on this assignment.

## Grading Rubric

* 2 pts  Model updates to include file with task
* 1 pt   Allow selection with Android file picker
* 1 pt   Upload file after selection
* 1 pt   Display images on Task detail activity
* 1 pt   README with description, screenshots, and daily change log
